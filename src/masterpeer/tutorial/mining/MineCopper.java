package masterpeer.tutorial.mining;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class MineCopper extends Task {
    int COPPER_ROCK = 10079;
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("you just need some copper")) != null;
    }

    @Override
    public int execute() {
        SceneObject rock = SceneObjects.getNearest(COPPER_ROCK);
        if(rock != null){
            rock.interact("Mine");
            Time.sleepUntil(Dialog::canContinue, Random.nextInt(10000, 30000));
        }
        return 0;
    }
}
