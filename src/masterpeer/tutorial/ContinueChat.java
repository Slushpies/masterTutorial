package masterpeer.tutorial;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class ContinueChat extends Task {
    @Override
    public boolean validate() {
        return Dialog.canContinue();
    }

    @Override
    public int execute() {
        //Log.info("Clicking continue");
        Time.sleep(gaussian(50, 15000, 500, 450));
        Dialog.processContinue();
        return gaussian(50, 15000, 500, 450);
    }
}
