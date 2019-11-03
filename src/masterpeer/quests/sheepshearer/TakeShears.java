package masterpeer.quests.sheepshearer;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.quests.QuestUtils.walkToPositionAndPickupItem;

public class TakeShears extends Task {
    Position FRED_POSITION = new Position(3190, 3272);

    @Override
    public boolean validate() {
        return !Inventory.contains("Shears");
    }

    @Override
    public int execute() {
        walkToPositionAndPickupItem(FRED_POSITION, "Shears");
        return 0;
    }
}
