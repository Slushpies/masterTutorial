package masterpeer.quests.sheepshearer;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class DepositItemsToFreeSpace extends Task {
    @Override
    public boolean validate() {
        return Inventory.getFreeSlots() + Inventory.getCount("Wool") + Inventory.getCount("Ball of wool") < (21 - (Inventory.contains("Shears") ? 1 : 0));
    }

    @Override
    public int execute() {
        if (Bank.isOpen()) {
            Bank.depositAllExcept("Shears");
            return gaussian(50, 15000, 2500, 2000);
        }
        BankLocation bestBank = BankLocation.getNearest();
        if (bestBank.getPosition().distance() > 10 || !bestBank.getPosition().isPositionInteractable()) {
            Log.info("Walking to bank pos, distance: " + bestBank.getPosition().distance() + " , isPositionInteractable? " + bestBank.getPosition().isPositionInteractable());
            Movement.walkToRandomized(bestBank.getPosition().randomize(1));
            return 0;
        }
        if (Bank.open(bestBank)) {
            Time.sleepUntil(Bank::isOpen, gaussian(50, 15000, 8000, 7000));
        }
        return 0;
    }
}
