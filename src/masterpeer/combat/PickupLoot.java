package masterpeer.combat;

import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.query.results.PositionableQueryResults;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class PickupLoot extends Task {
    Pickable loot;
    String[] lootNames;
    private final int lootDistance;
    private final int lootThresholdMin;
    private final int lootThresholdMax;
    private int lootThreshold; //Number of lootable items to wait for on the ground before looting (set to 1 to loot whenever loot is available)
    boolean doLoot = false;
    PositionableQueryResults<Pickable> bonesQuery;

    public PickupLoot(String[] lootNames, int lootDistance, int lootThresholdMin, int lootThresholdMax) {
        this.lootNames = lootNames;
        this.lootDistance = lootDistance;
        this.lootThresholdMin = lootThresholdMin;
        this.lootThresholdMax = lootThresholdMax;
        this.lootThreshold = Random.nextInt(lootThresholdMin, lootThresholdMax);
        Log.info("New loot threshold is " + lootThreshold);
    }

    @Override
    public boolean validate() {
        if (Inventory.isFull()){
            return false;
        }
        if(Players.getLocal().isAnimating() || Players.getLocal().getTargetIndex() != -1){
            return false;
        }
        bonesQuery = Pickables.newQuery().names(lootNames).within(lootDistance).reachable().results();
        if(bonesQuery.size() >= lootThreshold){
            Log.info("Set doLoot to true");
            doLoot = true;
        } else if(bonesQuery.size() == 0){
            Log.info("Set doLoot to false");
            doLoot = false;
            lootThreshold = Random.nextInt(lootThresholdMin, lootThresholdMax);
            Log.info("New loot threshold is " + lootThreshold);
        }
        return doLoot;
    }

    @Override
    public int execute() {
        loot = bonesQuery.nearest();
        if (loot.isPositionInteractable()) {
            int lootCount = Inventory.getCount(lootNames);
            if(loot.interact("Take")){
                Time.sleepUntil(()->Inventory.getCount(lootNames) > lootCount, Random.nextInt(5000, 25000));
            }
        } else {
            Movement.walkTo(loot.getPosition().randomize(1));
        }
        return gaussian(50, 5000, 500, 450);
    }
}
