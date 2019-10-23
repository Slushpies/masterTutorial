package masterpeer.tutorial.kitchen;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class TalkToKitchenGuide extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text)->text.toLowerCase().contains("talk to the chef indicated")) != null;
    }

    @Override
    public int execute() {
        Npc chef = Npcs.getNearest("Master Chef");
        if(chef != null){
            chef.interact("Talk-to");
        }
        return Random.nextInt(500, 5000);
    }
}
