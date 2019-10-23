package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

public class CheckEquipped extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("this is your worn inventory")) != null
                && Interfaces.getComponent(387, 17) != null;
    }

    @Override
    public int execute() {
        Interfaces.getComponent(387, 17).interact(ActionOpcodes.INTERFACE_ACTION);
        return Random.nextInt(50, 5000);
    }
}
