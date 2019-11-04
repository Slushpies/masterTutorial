package masterpeer;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class ClickLevelUpContinue extends Task {
    @Override
    public boolean validate() {
        return Dialog.canContinue() && Dialog.getContinue().isVisible();
    }

    @Override
    public int execute() {
        Time.sleep(gaussian(50, 25000, 5000, 4500));
        //Sometimes don't click continue, to allow another task to interrupt the dialog
        if(Random.nextInt(0, 3) == 2){
            return 0;
        }
        if(Dialog.canContinue()) Dialog.processContinue();
        return Random.nextInt(50, 5000);
    }
}
