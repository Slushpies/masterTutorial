package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class TalkToGielinorGuide extends Task {
    @Override
    public boolean validate() {
        return (Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the gielinor guide")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("talk to the gielinor guide to continue")) != null)
                && Interfaces.firstByText((text) -> text.toLowerCase().contains("i am brand new")) == null;
    }

    @Override
    public int execute() {
        Npcs.getNearest("Gielinor Guide").interact("Talk-to");
        return Random.nextInt(500, 5000);
    }
}
