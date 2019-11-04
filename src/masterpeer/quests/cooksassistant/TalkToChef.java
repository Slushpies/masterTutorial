package masterpeer.quests.cooksassistant;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.COOKSASSISTANT_VARP;
import static masterpeer.Constants.LUMBRIDGEKITCHEN_POSITION;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToChef extends Task {
    String[] ingredients = {"Pot of flour", "Egg", "Bucket of milk"};

    @Override
    public boolean validate() {
        return Varps.get(COOKSASSISTANT_VARP) < 2
                && Inventory.containsAll(ingredients);
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(LUMBRIDGEKITCHEN_POSITION, "Cook");
        return 0;
    }
}
