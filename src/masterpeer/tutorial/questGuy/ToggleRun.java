package masterpeer.tutorial.questGuy;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.task.Task;

public class ToggleRun extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("running is faster")) != null;
    }

    @Override
    public int execute() {
        if (Movement.isRunEnabled()) {
            Movement.toggleRun(false);
        } else {
            Movement.toggleRun(true);
        }
        return Random.nextInt(500, 5000);
    }
}
