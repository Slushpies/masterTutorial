package masterpeer.tutorial.banking;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class TalkToGuide extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("the guide here will tell you all about your account")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("talk to the account guide")) != null;
    }

    @Override
    public int execute() {
        Npcs.getNearest("Account Guide").interact("Talk-to");
        return Random.nextInt(50, 5000);
    }
}
