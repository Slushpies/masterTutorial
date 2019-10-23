package masterpeer.tutorial.prayer;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;

public class NavigatePrayer extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the flashing icon to open the prayer menu")) != null;
    }

    @Override
    public int execute() {
        Tabs.open(Tab.PRAYER);
        return Random.nextInt(50, 5000);
    }
}
