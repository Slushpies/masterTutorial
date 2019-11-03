package masterpeer.mining;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

import java.util.Arrays;
import java.util.List;

import static masterpeer.Constants.JUNK;

public class DepositLoot extends Task {
    String[] lootAndJunk;

    public DepositLoot(String[] loot) {
        List<String> lootList = Arrays.asList(loot);
        List<String> lootAndJunkList = Arrays.asList(JUNK);
        lootAndJunkList.addAll(lootList);
        this.lootAndJunk = (String[]) lootAndJunkList.toArray();
    }

    @Override
    public boolean validate() {
        return Inventory.isFull() && Inventory.contains(lootAndJunk);
    }

    @Override
    public int execute() {
        if (!Bank.isOpen()) {
            Bank.open(BankLocation.getNearest());
            return Random.nextInt(500, 5000);
        }
        Bank.depositAll(lootAndJunk);
        return 0;
    }
}
