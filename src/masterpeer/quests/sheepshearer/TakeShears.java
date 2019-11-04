package masterpeer.quests.sheepshearer;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.quests.QuestUtils.walkToPositionAndTakeItem;

public class TakeShears extends Task {
    Position SHEARSADJACENT_POSITION = new Position(3191, 3272);

    @Override
    public boolean validate() {
        return !Inventory.contains("Shears");
    }

    @Override
    public int execute() {
        walkToPositionAndTakeItem(SHEARSADJACENT_POSITION, "Shears");
        return 0;
    }
}
