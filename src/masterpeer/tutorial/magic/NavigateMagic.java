package masterpeer.tutorial.magic;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;

public class NavigateMagic extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("open up the magic interface")) != null;
    }

    @Override
    public int execute() {
        Tabs.open(Tab.MAGIC);
        return Random.nextInt(50, 5000);
    }
}
