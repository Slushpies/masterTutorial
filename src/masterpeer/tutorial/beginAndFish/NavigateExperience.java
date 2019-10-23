package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;

public class NavigateExperience extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text)->text.toLowerCase().contains("click on the flashing bar")) != null;
    }

    @Override
    public int execute() {
        Tabs.open(Tab.SKILLS);
        return Random.nextInt(500, 5000);
    }
}
