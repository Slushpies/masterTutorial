package masterpeer.quests.piratestreasure;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.PIRATESTREASURE_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToRedbeard extends Task {
    Position REDBEARD_POSITION = new Position(3053, 3253);

    @Override
    public boolean validate() {
        return Varps.get(PIRATESTREASURE_VARP) == 0;
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(REDBEARD_POSITION, "Redbeard Frank");
        return 0;
    }
}
