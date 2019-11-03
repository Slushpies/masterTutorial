package masterpeer.quests.sheepshearer;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.SHEEPSHEARER_VARP;

public class SpinWool extends Task {
    @Override
    public boolean validate() {
        return Varps.get(SHEEPSHEARER_VARP) == 1
                && Inventory.contains("Wool")
                && Inventory.getCount("Ball of wool") < 20
                && Interfaces.getComponent(270, 14) != null
                && Interfaces.getComponent(270, 14).isVisible();
    }

    @Override
    public int execute() {
        if(Interfaces.getComponent(270, 14).interact(ActionOpcodes.INTERFACE_ACTION)){
            Time.sleepUntil(()->(!Inventory.contains("Wool") || Inventory.getCount("Ball of wool") >= 20), Random.nextInt(30000, 50000));
        }
        return 0;
    }
}
