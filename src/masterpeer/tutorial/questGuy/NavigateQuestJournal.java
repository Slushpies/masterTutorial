package masterpeer.tutorial.questGuy;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.script.task.Task;

public class NavigateQuestJournal extends Task {
    @Override
    public boolean validate() {
        return  Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the flashing icon to the left of your inventory")) != null;
    }

    @Override
    public int execute() {
        Tabs.open(Tab.QUEST_LIST);
        return Random.nextInt(50, 5000);
    }
}
