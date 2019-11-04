package masterpeer.combat;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;
import static masterpeer.combat.CombatUtil.monsterPosition;

public class Fight extends Task {
    private String targetMonster;
    private Position monsterPos;
    private int runThreshold = Random.nextInt(1, 100);
    String[] lootNames;
    int lootDistance;
    int lootThreshold; //How many pieces of loot to wait for to be on the ground before looting
    boolean doLoot;

    public Fight(String targetMonster, String[] lootNames) {
        this.targetMonster = targetMonster;
        this.monsterPos = monsterPosition(targetMonster);
        this.lootNames = lootNames;
        this.lootDistance = lootDistance;
    }

    @Override
    public boolean validate() {
        //Return false if loot is nearby
        if (Inventory.isFull() && Inventory.contains(lootNames)) {
            return false;
        }
        /*Pickable loot = Pickables.getNearest(lootNames);
        if (loot != null && loot.distance() < lootDistance) {
            return false;
        }*/
        return !Players.getLocal().isAnimating() && Players.getLocal().getTargetIndex() == -1 && monsterPos.distance() <= 15;
    }

    @Override
    public int execute() {
        //Usually wait before attacking
        if (Random.nextInt(0, 3) > 0) {
            Time.sleep(gaussian(50, 30000, 1000, 950));
        }
        //Sometimes return to give loot or other tasks a chance to execute
        if (Random.nextInt(0, 3) == 0) return 0;
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() >= runThreshold) {
            Movement.toggleRun(true);
            runThreshold = Random.nextInt(1, 101);
        }
        //If we're already under attack, attack the NPC attacking us, even if it isn't one of the monsters we want to fight
        Npc target = Npcs.newQuery().targeting(Players.getLocal()).results().nearest();
        if(target == null){
            target = Npcs.newQuery().names(targetMonster).health(1).targetless().results().nearest();
        }
        if (target != null) {
            if (target.isPositionInteractable()) {
                if(target.interact("Attack")){
                    Time.sleepUntil(()->Players.getLocal().getTargetIndex() != -1, Random.nextInt(50, 15000));
                }
            } else {
                Movement.walkTo(target);
                Log.info("Walk to target on position not interactable");
            }
        }
        return gaussian(50, 30000, 1000, 950);
    }
}
