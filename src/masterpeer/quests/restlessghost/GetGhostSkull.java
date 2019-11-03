package masterpeer.quests.restlessghost;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.RESTLESSGHOST_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndInteractWithObject;

public class GetGhostSkull extends Task {
    Position ALTAR_LOCATION = new Position(3116, 9565);

    @Override
    public boolean validate() {
        return Varps.get(RESTLESSGHOST_VARP) == 3 && !Inventory.contains("Ghost's skull");
    }

    @Override
    public int execute() {
        walkToPositionAndInteractWithObject(ALTAR_LOCATION, "Altar", "Search");
        return 0;
    }
}
