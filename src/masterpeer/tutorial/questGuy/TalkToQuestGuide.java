package masterpeer.tutorial.questGuy;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class TalkToQuestGuide extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("it's time to learn about quests")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("talk to the quest guide")) != null;
    }

    @Override
    public int execute() {
        Npc questGuide = Npcs.getNearest("Quest Guide");
        if(questGuide != null){
            questGuide.interact("Talk-to");
            Time.sleepUntil(()-> Dialog.canContinue(), Random.nextInt(5000, 15000));
        }
        return Random.nextInt(50, 5000);
    }
}
