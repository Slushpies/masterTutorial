package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

import java.awt.*;

public class NavigateOptions extends Task {

    private static final Rectangle RESIZABLE_BOUNDS = new Rectangle(729, 336, 6, 6);
    private static final Rectangle FIXED_BOUNDS = new Rectangle(706, 373, 6, 6);

    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("please click on the flashing spanner icon")) != null;
    }

    @Override
    public int execute() {
        if(Tabs.getOpen().equals(Tab.OPTIONS)){
            Tabs.open(Tab.LOGOUT);
            return Random.nextInt(500, 5000);
        }
        Tabs.open(Tab.OPTIONS);
        Time.sleep(500, 5000);
        if(Interfaces.getComponent(261, 34, 8).getBounds().equals(RESIZABLE_BOUNDS)) {
            Interfaces.getComponent(261, 33).interact(ActionOpcodes.INTERFACE_ACTION);//Set screen to fixed
        }
        return Random.nextInt(500, 5000);
    }
}
