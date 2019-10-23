package masterpeer.tutorial.mining;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class TalkToMiningGuide extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("next let's get you a weapon")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("speak to the mining instructor")) != null
                && Interfaces.firstByText((text) -> text.toLowerCase().contains("speak to the mining instructor for a recap")) == null;
    }

    @Override
    public int execute() {
        Npc miningGuide = Npcs.getNearest("Mining Instructor");
        if(miningGuide != null){
            miningGuide.interact("Talk-to");
            Time.sleepUntil(()-> Dialog.canContinue(), Random.nextInt(5000, 15000));
        }
        return 0;
    }
}
