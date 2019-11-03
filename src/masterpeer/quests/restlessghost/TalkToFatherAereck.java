package masterpeer.quests.restlessghost;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.RESTLESSGHOST_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToFatherAereck extends Task {
    Position FATHERAERECK_POSITION = new Position(3239, 3210);

    @Override
    public boolean validate() {
        return Varps.get(RESTLESSGHOST_VARP) == 0;
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(FATHERAERECK_POSITION, "Father Aereck");
        return 0;
    }
}
