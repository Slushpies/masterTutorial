package masterpeer.tutorial.magic;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

public class GoToMainland extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("do you want to go to the mainland")) != null;
    }

    @Override
    public int execute() {
        Interfaces.getComponent(219, 1, 1).interact(ActionOpcodes.BUTTON_DIALOG);
        return Random.nextInt(50, 5000);
    }
}
