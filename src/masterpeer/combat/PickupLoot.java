package masterpeer.combat;

import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class PickupLoot extends Task {
    Pickable loot;
    String[] lootNames;

    public PickupLoot(String[] lootNames) {
        this.lootNames = lootNames;
    }

    @Override
    public boolean validate() {
        if (Inventory.isFull()){
            return false;
        }
        if(Players.getLocal().isAnimating() || Players.getLocal().getTargetIndex() != -1){
            return false;
        }
        loot = Pickables.getNearest(lootNames);
        return loot != null && loot.distance() < 20;
    }

    @Override
    public int execute() {
        if (loot.isPositionInteractable()) {
            loot.interact("Take");
        } else {
            Movement.walkTo(loot.getPosition().randomize(1));
        }
        return Random.nextInt(500, 5000);
    }
}
