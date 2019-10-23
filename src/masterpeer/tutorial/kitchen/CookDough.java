package masterpeer.tutorial.kitchen;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class CookDough extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text)->text.toLowerCase().contains("now you have made the dough")) != null;
    }

    @Override
    public int execute() {
        if(!Inventory.isItemSelected()){
            Inventory.getFirst("Bread dough").interact("Use");
            Time.sleep(500, 5000);
        }
        SceneObjects.getNearest("Range").interact("Use");
        Time.sleepUntil(()->Inventory.contains("Bread"), Random.nextInt(15000, 30000));
        return 0;
    }
}
