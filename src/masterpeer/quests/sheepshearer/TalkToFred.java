package masterpeer.quests.sheepshearer;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.SHEEPSHEARER_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToFred extends Task {
    Position FRED_POSITION = new Position(3190, 3272);

    @Override
    public boolean validate() {
        return Varps.get(SHEEPSHEARER_VARP) == 0
                || (Varps.get(SHEEPSHEARER_VARP) == 1 && Inventory.getCount("Ball of wool") >= 20);
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(FRED_POSITION, "Fred the Farmer");
        return 0;
    }
}
