package masterpeer.tutorial.mining;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class MineTin extends Task {
    int TIN_ROCK = 10080;
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("try mining some tin")) != null;
    }

    @Override
    public int execute() {
        SceneObject rock = SceneObjects.getNearest(TIN_ROCK);
        if(rock != null){
            rock.interact("Mine");
            Time.sleepUntil(Dialog::canContinue, Random.nextInt(10000, 30000));
        }
        return 0;
    }
}
