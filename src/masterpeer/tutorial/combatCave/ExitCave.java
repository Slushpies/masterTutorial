package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class ExitCave extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("you have completed the tasks here")) != null;
    }

    @Override
    public int execute() {
        SceneObjects.getNearest("Ladder").interact("Climb-up");
        Time.sleepUntil(()->Interfaces.firstByText((text) -> text.toLowerCase().contains("this is the bank of gielinor")) != null, Random.nextInt(10000, 30000));
        return Random.nextInt(50, 5000);
    }
}
