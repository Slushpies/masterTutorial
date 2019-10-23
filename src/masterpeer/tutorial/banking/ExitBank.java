package masterpeer.tutorial.banking;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.CHAPEL;

public class ExitBank extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("continue through the next door.")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("follow the path to the chapel and enter it")) != null
                && CHAPEL.distance() > 8;
    }

    @Override
    public int execute() {
        Movement.walkTo(CHAPEL);
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
        return Random.nextInt(500, 5000);
    }
}
