package masterpeer.combat;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.DepositBox;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class DepositLoot extends Task {
    private final String[] loot;

    public DepositLoot(String[] loot){
        this.loot = loot;
    }
    @Override
    public boolean validate() {
        return Inventory.isFull() && Inventory.contains(loot);
    }

    @Override
    public int execute() {
        if(!DepositBox.isOpen()){
            DepositBox.open();
            return Random.nextInt(500, 5000);
        }
        DepositBox.depositAll(loot);
        /*if(!Bank.isOpen()){
            Bank.open();
            return Random.nextInt(500, 5000);
        }
        Bank.depositAll(loot);*/
        return Random.nextInt(50, 5000);
    }
}
