package masterpeer.quests.sheepshearer;

import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.quests.QuestUtils.walkToPositionAndInteractWithObject;

public class UseSpinningWheel extends Task {
    Position SPINNING_WHEEL_POSITION = new Position(3209, 3214, 1);

    @Override
    public boolean validate() {
        return Inventory.contains("Wool")
                && Inventory.getCount("Ball of wool") < 20
                && Inventory.getCount("Wool") + Inventory.getCount("Ball of wool") >= 20
                && Interfaces.getComponent(270, 14) == null;
    }

    @Override
    public int execute() {
        walkToPositionAndInteractWithObject(SPINNING_WHEEL_POSITION, "Spinning wheel", "Spin");
        return 0;
    }
}
