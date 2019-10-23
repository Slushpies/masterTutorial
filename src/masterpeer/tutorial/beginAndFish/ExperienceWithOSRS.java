package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

public class ExperienceWithOSRS extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text)->text.toLowerCase().contains("i am brand new")) != null;
    }

    @Override
    public int execute() {
        Dialog.process(1);
        return Random.nextInt(500, 5000);
    }
}
