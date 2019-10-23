package masterpeer.tutorial.banking;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.Constants.BANK_TILE;

public class OpenBank extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the indicated booth")) != null;
    }

    @Override
    public int execute() {
        Time.sleep(500, 5000);
        if (BANK_TILE.distance() > 6) {
            Log.info("Walking to bank tile");
            Movement.walkTo(BANK_TILE);
            Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
            return Random.nextInt(50, 500);
        }
        SceneObject bankBooth = SceneObjects.getNearest("Bank booth");
        Log.info("Opening bank");
        bankBooth.interact("Use");
        Time.sleepUntil(Bank::isOpen, Random.nextInt(10000, 30000));
        return Random.nextInt(50, 5000);
    }
}
