package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.RAT_PEN;

public class EnterRatPen extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("monsters are weak to specific attack styles")) != null;
    }

    @Override
    public int execute() {
        Movement.walkTo(RAT_PEN);
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
        return Random.nextInt(500, 5000);
    }
}
