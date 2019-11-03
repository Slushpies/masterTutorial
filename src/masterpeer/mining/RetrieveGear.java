package masterpeer.mining;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class RetrieveGear extends Task {
    Path path = null;

    @Override
    public boolean validate() {
        return !Inventory.contains((item) -> item.getName().contains("pick")) && !Equipment.contains((item) -> item.getName().contains("pick"));
    }

    @Override
    public int execute() {
        if (!Bank.isOpen()) {
            Log.info("We don't have a pick in our inventory or equipment, need to go to bank");
            BankLocation nearestBank = BankLocation.getNearest();
            Log.info("Nearest bank is: " + nearestBank.getName() + "with distance: " + nearestBank.getPosition().distance());
            if (BankLocation.getNearest().getPosition().isLoaded()) {
                Log.info("Bank position is loaded");
                if (nearestBank.getPosition().distance() > 8) {
                    if (!Bank.open()) {
                        Movement.walkToRandomized(nearestBank.getPosition());
                    }
                } else {
                    Bank.open();
                }
                return Random.nextInt(50, 8000);
            }
            //Bank position isn't loaded
            if (path == null) {
                Log.info("Path is null, building");
                path = Movement.buildPath(nearestBank.getPosition());
            }
            if (!path.walk()) {
                Log.info("path walk failed, rebuilding");
                path = Movement.buildPath(nearestBank.getPosition());
            }
            Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
        }
        return 0;
    }

}

