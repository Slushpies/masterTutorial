package masterpeer.quests;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class CloseQuestCompleteInterface extends Task {
    InterfaceComponent closeCompletedQuest;

    @Override
    public boolean validate() {
        closeCompletedQuest = Interfaces.getComponent(277, 16, 0);
        return closeCompletedQuest != null && closeCompletedQuest.isVisible();
    }

    @Override
    public int execute() {
        closeCompletedQuest.interact(ActionOpcodes.BUTTON_CLOSE);
        Time.sleep(gaussian(50, 15000, 5000, 4000));
        Game.logout();
        return 0;
    }
}
