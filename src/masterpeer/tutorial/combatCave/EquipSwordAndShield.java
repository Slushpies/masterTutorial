package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class EquipSwordAndShield extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("swapping your dagger for the sword and shield")) != null;
    }

    @Override
    public int execute() {
        if (Inventory.contains("Bronze sword")) {
            Inventory.getFirst("Bronze sword").interact("Wield");
            Time.sleep(500, 5000);
        }
        if (Inventory.contains("Wooden shield")) {
            Inventory.getFirst("Wooden shield").interact("Wield");
            Time.sleep(500, 5000);
        }
        return 0;
    }
}
