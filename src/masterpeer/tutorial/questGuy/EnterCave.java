package masterpeer.tutorial.questGuy;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class EnterCave extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("it's time to enter some caves")) != null;
    }

    @Override
    public int execute() {
        SceneObjects.getNearest("Ladder").interact("Climb-down");
        Time.sleepUntil(()->Interfaces.firstByText((text) -> text.toLowerCase().contains("next let's get you a weapon")) != null, Random.nextInt(10000, 30000));
        return Random.nextInt(50, 5000);
    }
}
