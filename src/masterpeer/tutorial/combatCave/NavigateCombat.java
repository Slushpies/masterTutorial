package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;

public class NavigateCombat extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the flashing crossed swords")) != null;
    }

    @Override
    public int execute() {
        Tabs.open(Tab.COMBAT);
        return Random.nextInt(50, 5000);
    }
}
