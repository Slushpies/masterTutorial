package masterpeer.quests.cooksassistant;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.COOKSASSISTANT_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndInteractWithObject;

public class CollectMilk extends Task {
    Position LUMBRIDGECOWS_POSITION = new Position(3256, 3273);

    @Override
    public boolean validate() {
        return Varps.get(COOKSASSISTANT_VARP) < 2
                && Inventory.contains("Pot of flour")
                && Inventory.contains("Bucket")
                && !Inventory.contains("Bucket of milk");
    }

    @Override
    public int execute() {
        walkToPositionAndInteractWithObject(LUMBRIDGECOWS_POSITION, "Dairy cow", "Milk");
        return 0;
    }
}
