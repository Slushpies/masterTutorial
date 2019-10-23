package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class TalkToSurvivalGuide extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("talk to the survival expert to continue")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("speak to the survival expert to continue")) != null;
    }

    @Override
    public int execute() {
        Npc survivalExpert = Npcs.getNearest("Survival Expert");
        if (survivalExpert.interact("Talk-to")) {
            return Random.nextInt(500, 10000);
        }
        return 0;
    }
}
