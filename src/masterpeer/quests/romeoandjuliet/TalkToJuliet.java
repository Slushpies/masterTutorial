package masterpeer.quests.romeoandjuliet;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.ROMEOANDJULIET_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToJuliet extends Task {
    Position JULIET_POSITION = new Position(3158, 3426, 1);

    @Override
    public boolean validate() {
        return Varps.get(ROMEOANDJULIET_VARP) == 10 || Varps.get(ROMEOANDJULIET_VARP) == 50;
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(JULIET_POSITION, "Juliet");
        return 0;
    }
}
