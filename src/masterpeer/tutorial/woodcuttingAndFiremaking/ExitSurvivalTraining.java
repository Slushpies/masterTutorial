package masterpeer.tutorial.woodcuttingAndFiremaking;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class ExitSurvivalTraining extends Task {
    Position INSIDE_KITCHEN = new Position(3078, 3084);
    Path path = null;

    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("well done, you've just cooked your first meal")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("follow the path until you get to the door with the yellow arrow")) != null;
    }

    @Override
    public int execute() {
        Movement.walkTo(INSIDE_KITCHEN);
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
        return Random.nextInt(500, 5000);
    }
}
