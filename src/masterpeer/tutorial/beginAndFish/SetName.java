package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.Keyboard;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.Script;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class SetName extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text)->text.toLowerCase().contains("great! this display name is")) != null;
    }

    @Override
    public int execute() {
        Interfaces.getComponent(558, 18).interact(ActionOpcodes.INTERFACE_ACTION);
        return Random.nextInt(500, 5000);
    }
}
