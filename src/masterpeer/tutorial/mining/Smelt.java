package masterpeer.tutorial.mining;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class Smelt extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("you can smelt these into a bronze bar")) != null;
    }

    @Override
    public int execute() {
        if (!Inventory.isItemSelected()) {
            Inventory.getFirst("Tin ore").interact("Use");
            Time.sleep(500, 5000);
        }
        SceneObject furnace = SceneObjects.getNearest("Furnace");
        if (furnace != null) {
            furnace.interact("Use");
            Time.sleepUntil(Dialog::canContinue, Random.nextInt(10000, 30000));
        }
        return 0;
    }
}
