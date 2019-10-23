package masterpeer.tutorial.mining;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

public class SmithDagger extends Task {
    int SMITHING_COMPONENT = 312;
    int DAGGER_COMPONENT = 9;

    @Override
    public boolean validate() {
         return Interfaces.getComponent(312, 9) != null;
    }

    @Override
    public int execute() {
        Interfaces.getComponent(312, 9).interact(ActionOpcodes.INTERFACE_ACTION);
        Time.sleepUntil(()->Interfaces.firstByText((text) -> text.toLowerCase().contains("congratulations, you've made your first weapon")) != null, Random.nextInt(10000, 30000));
        return Random.nextInt(50, 5000);
    }
}
