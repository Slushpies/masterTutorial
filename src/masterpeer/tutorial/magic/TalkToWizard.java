package masterpeer.tutorial.magic;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.WIZARD_HOUSE;

public class TalkToWizard extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("follow the path to the wizard's house")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("your spells are listed here. talk to the instructor")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("speak with the magic instructor")) != null
                &&  WIZARD_HOUSE.distance() <= 7;
    }

    @Override
    public int execute() {
        Npc magicInstructor = Npcs.getNearest("Magic Instructor");
        magicInstructor.interact("Talk-to");
        return Random.nextInt(50, 5000);
    }
}
