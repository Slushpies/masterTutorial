package masterpeer.tutorial.woodcuttingAndFiremaking;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class CutTree extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("give it a go by clicking on one of the trees in the area")) != null
                || (Interfaces.firstByText((text) -> text.toLowerCase().contains("now it's time to get cooking")) != null
                && SceneObjects.getNearest("Fire") == null
                && !Inventory.contains("Logs"))
                && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public int execute() {
        SceneObject tree = SceneObjects.getNearest("Tree");
        if(tree != null){
            tree.interact("Chop down");
            Time.sleepUntil(()->Interfaces.firstByText((text)->text.toLowerCase().contains("give it a go by clicking on one of the trees")) == null, Random.nextInt(10000, 25000));
        }
        return Random.nextInt(500, 5000);
    }
}
