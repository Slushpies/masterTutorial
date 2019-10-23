package masterpeer.tutorial.kitchen;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class MixDough extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text)->text.toLowerCase().contains("to make dough you must mix flour with water")) != null;
    }

    @Override
    public int execute() {
        if(!Inventory.isItemSelected()){
            Inventory.getFirst("Pot of flour").interact("Use");
            Time.sleep(500, 5000);
        }
        Inventory.getFirst("Bucket of water").interact("Use");
        return Random.nextInt(500, 5000);
    }
}
