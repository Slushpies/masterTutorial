package masterpeer.quests.cooksassistant;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.COOKSASSISTANT_VARP;
import static masterpeer.Constants.LUMBRIDGEKITCHEN_POSITION;
import static masterpeer.quests.QuestUtils.walkToPositionAndTakeItem;

public class CollectItems extends Task {
    Position LUMBRIDGECELLAR_POSITION = new Position(3210, 9621);
    Position CHICKENCOOP_POSITION = new Position(3229, 3297);

    @Override
    public boolean validate() {
        return Varps.get(COOKSASSISTANT_VARP) < 2
        && (!Inventory.contains("Pot") && !Inventory.contains("Pot of flour"))
                || (!Inventory.contains("Bucket") && !Inventory.contains("Bucket of milk"))
                || !Inventory.contains("Egg");
    }

    @Override
    public int execute() {
        if((!Inventory.contains("Pot") && !Inventory.contains("Pot of flour"))){
            walkToPositionAndTakeItem(LUMBRIDGEKITCHEN_POSITION, "Pot");
        } else if(!Inventory.contains("Bucket") && !Inventory.contains("Bucket of milk")){
            walkToPositionAndTakeItem(LUMBRIDGECELLAR_POSITION, "Bucket");
        } else if(!Inventory.contains("Egg")){
            walkToPositionAndTakeItem(CHICKENCOOP_POSITION, "Egg");
        }
        return 0;
    }
}
