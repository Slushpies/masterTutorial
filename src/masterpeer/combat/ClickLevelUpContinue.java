package masterpeer.combat;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class ClickLevelUpContinue extends Task {
    @Override
    public boolean validate() {
        return Dialog.canContinue() && Dialog.getContinue().isVisible();
    }

    @Override
    public int execute() {
        Time.sleep(Random.nextInt(500, 5000));
        //Sometimes don't click continue, to allow another task to interrupt the dialogue
        if(Random.nextInt(0, 3) == 2){
            return 0;
        }
        Dialog.processContinue();
        return Random.nextInt(50, 5000);
    }
}
