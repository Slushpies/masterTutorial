package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class EquipDagger extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("click your dagger to equip it")) != null;
    }

    @Override
    public int execute() {
        Inventory.getFirst("Bronze dagger").interact("Wield");
        return Random.nextInt(50, 5000);
    }
}
