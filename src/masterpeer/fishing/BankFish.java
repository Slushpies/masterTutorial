package masterpeer.fishing;

import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;
import static masterpeer.Constants.RAWFISH_NAMES;

public class BankFish extends Task {

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public int execute() {
        Log.info("Banking");
        if (Bank.isOpen()) {
            Bank.depositAll(RAWFISH_NAMES);
            return gaussian(50, 15000, 250, 200);
        }
        Log.info("Going to the bank");
        Bank.open();
        Time.sleepUntil(() -> Movement.getDestinationDistance() < Random.nextInt(1, 10) || Bank.isOpen(), Random.nextInt(500, 25000));
        return 0;
    }
}
