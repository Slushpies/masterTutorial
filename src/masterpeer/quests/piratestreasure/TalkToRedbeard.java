package masterpeer.quests.piratestreasure;

import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

public class TalkToRedbeard extends Task {
    Position REDBEARD_POSITION = new Position(3053, 3253);

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }
}
