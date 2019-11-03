package masterpeer.quests.sheepshearer;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.SHEEPSHEARER_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndInteractWithNpc;

public class ShearSheep extends Task {
    Position SHEEP_POSITION = new Position(3203, 3266);

    @Override
    public boolean validate() {
        return Varps.get(SHEEPSHEARER_VARP) == 1 &&
                (Inventory.getCount("Wool") + Inventory.getCount("Ball of wool")) < 20;
    }

    @Override
    public int execute() {
        //Interact with a sheep that does not have a talk-to option (as this is a different type of sheep used in the Cold War quest)
        walkToPositionAndInteractWithNpc(SHEEP_POSITION, "Sheep", "Shear", (sheep) -> !sheep.containsAction("Talk-to"));
        return 0;
    }
}
