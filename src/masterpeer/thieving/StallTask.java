package masterpeer.thieving;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class StallTask extends Task {
    @Override
    public boolean validate() {
        return !Inventory.isFull() && SceneObjects.getNearest(635) != null;
    }

    @Override
    public int execute() {
        SceneObject stall = SceneObjects.getNearest(635);
        if(stall != null){
            stall.interact("Steal-from");
            Time.sleep(gaussian(250, 6000, 4000, 1000));
        }
        return 0;
    }
}
