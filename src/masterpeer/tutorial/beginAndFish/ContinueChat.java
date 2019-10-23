package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class ContinueChat extends Task {
    @Override
    public boolean validate() {
        return Dialog.canContinue();
    }

    @Override
    public int execute() {
        //Log.info("Clicking continue");
        Time.sleep(250, 2500);
        Dialog.processContinue();
        return Random.nextInt(250, 2500);
    }
}
