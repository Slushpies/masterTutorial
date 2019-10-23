package masterpeer.tutorial.prayer;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.WIZARD_HOUSE;

public class ExitChapel extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("you're almost finished on tutorial island. pass through the door")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("follow the path to the wizard's house")) != null
                && WIZARD_HOUSE.distance() > 7;
    }

    @Override
    public int execute() {
        Movement.walkTo(WIZARD_HOUSE);
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
        return Random.nextInt(500, 5000);
    }
}
