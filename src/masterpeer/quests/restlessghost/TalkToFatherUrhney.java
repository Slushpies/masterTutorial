package masterpeer.quests.restlessghost;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.RESTLESSGHOST_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToFatherUrhney extends Task {
    Position FATHERURHNEY_POSITION = new Position(3147, 3174);

    @Override
    public boolean validate() {
        return Varps.get(RESTLESSGHOST_VARP) == 1;
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(FATHERURHNEY_POSITION, "Father Urhney");
        return 0;
    }
}
