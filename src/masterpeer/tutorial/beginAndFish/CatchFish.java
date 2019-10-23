package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class CatchFish extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the flashing backpack icon")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("use it to catch some shrimp")) != null;
    }

    @Override
    public int execute() {
        if (!Tabs.getOpen().equals(Tab.INVENTORY)) {
            Tabs.open(Tab.INVENTORY);
            Time.sleep(500, 5000);
        }
        Npc fishingSpot = Npcs.getNearest("Fishing spot");
        if (fishingSpot != null) {
            fishingSpot.interact("Net");
        }
        return Random.nextInt(500, 5000);
    }
}
