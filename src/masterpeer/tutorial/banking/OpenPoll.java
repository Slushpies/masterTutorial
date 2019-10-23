package masterpeer.tutorial.banking;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class OpenPoll extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the indicated poll booth")) != null;
    }

    @Override
    public int execute() {
        SceneObjects.getNearest("Poll booth").interact("Use");
        Time.sleepUntil(Dialog::canContinue, Random.nextInt(10000, 30000));
        return Random.nextInt(50, 5000);
    }
}
