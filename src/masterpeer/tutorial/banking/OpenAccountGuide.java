package masterpeer.tutorial.banking;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;

public class OpenAccountGuide extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the flashing icon to open your account management")) != null;
    }

    @Override
    public int execute() {
        Tabs.open(Tab.ACCOUNT_MANAGEMENT);
        return Random.nextInt(50, 5000);
    }
}
