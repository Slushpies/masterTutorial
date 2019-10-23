package masterpeer.tutorial.prayer;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.CHAPEL;

public class TalkToPrayerGuide extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("once inside talk to the monk")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("speak with brother brace")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("talk with brother brace")) != null
                && CHAPEL.distance() <= 8;
    }

    @Override
    public int execute() {
        Npc monk = Npcs.getNearest("Brother Brace");
        monk.interact("Talk-to");
        return Random.nextInt(50, 5000);
    }
}
