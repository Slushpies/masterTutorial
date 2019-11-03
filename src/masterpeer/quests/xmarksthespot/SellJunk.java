package masterpeer.quests.xmarksthespot;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static masterpeer.quests.QuestUtils.STARTER_JUNK;

public class SellJunk extends Task {

    @Override
    public boolean validate() {
        return !Inventory.contains("Spade")
                && Inventory.getCount(true,"Coins") < 3
                && Inventory.contains(STARTER_JUNK)
                && Shop.isOpen();
    }

    @Override
    public int execute() {
            //Sell useless junk from tut island
                for (String item : STARTER_JUNK) {
                    if (Inventory.contains(item)) {
                        Shop.sellOne(item);
                        Time.sleep(Random.nextInt(250, 5000));
                    }
                }
            return Random.nextInt(500, 5000);
    }
}
