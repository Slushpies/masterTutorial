package masterpeer.tutorial.magic;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

public class NotIronman extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("no, i'm not planning to do that")) != null;
    }

    @Override
    public int execute() {
        Interfaces.getComponent(219, 1, 3).interact(ActionOpcodes.BUTTON_DIALOG);
        return Random.nextInt(50, 5000);
    }
}
