package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

public class LookUpName extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("before you get started, you'll need a unique display name")) != null
                && !Interfaces.firstByText((text) -> text.toLowerCase().contains("please pick a unique display name (up to 12 characters)")).isVisible()
                && Interfaces.firstByText((text)->text.toLowerCase().contains("great! this display name is")) == null;
    }

    @Override
    public int execute() {
        Interfaces.getComponent(558, 17).interact(ActionOpcodes.INTERFACE_ACTION);
        return Random.nextInt(500, 5000);
    }
}
