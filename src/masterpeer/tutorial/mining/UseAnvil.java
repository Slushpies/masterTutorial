package masterpeer.tutorial.mining;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class UseAnvil extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("to smith you'll need a hammer")) != null;
    }

    @Override
    public int execute() {
        if (!Inventory.isItemSelected()) {
            Inventory.getFirst("Bronze Bar").interact("Use");
            Time.sleep(500, 5000);
        }
        SceneObject anvil = SceneObjects.getNearest("Anvil");
        if (anvil != null) {
            anvil.interact("Use");
            Time.sleepUntil(Dialog::canContinue, Random.nextInt(10000, 30000));
        }
        return 0;
    }
}
