package masterpeer.quests.restlessghost;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class EquipGhostspeakAmulet extends Task {
    @Override
    public boolean validate() {
        return Inventory.contains("Ghostspeak amulet") && !Equipment.contains("Ghostspeak amulet");
    }

    @Override
    public int execute() {
        Inventory.getFirst("Ghostspeak amulet").interact("Wear");
        return gaussian(50, 15000, 1000, 900);
    }
}
