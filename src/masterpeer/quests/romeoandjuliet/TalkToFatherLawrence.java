package masterpeer.quests.romeoandjuliet;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.ROMEOANDJULIET_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToFatherLawrence extends Task {
    Position FATHERLAWRENCE_POSITION = new Position(3254, 3480);

    @Override
    public boolean validate() {
        return Varps.get(ROMEOANDJULIET_VARP) == 30;
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(FATHERLAWRENCE_POSITION, "Father Lawrence");
        return 0;
    }
}
