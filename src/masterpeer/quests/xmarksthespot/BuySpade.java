package masterpeer.quests.xmarksthespot;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class BuySpade extends Task {
    @Override
    public boolean validate() {
        return Shop.isOpen()
                && !Inventory.contains("Spade")
                && Inventory.getCount(true, "Coins") >= 3;
    }

    @Override
    public int execute() {
        Time.sleep(Random.nextInt(500, 5000));
        Shop.buyOne("Spade");
        Time.sleep(Random.nextInt(500, 5000));
        Shop.close();
        return Random.nextInt(500, 5000);
    }
}
