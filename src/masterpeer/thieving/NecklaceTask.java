package masterpeer.thieving;

import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class NecklaceTask extends Task {
    @Override
    public boolean validate() {
        return !Equipment.contains("Dodgy necklace") && Inventory.contains("Dodgy necklace");
    }

    @Override
    public int execute() {
        //Log.info("Equipping dodgy necklace");
        Inventory.getFirst("Dodgy necklace").interact("Wear");
        return gaussian(10, 1000, 200, 100);
    }
}
