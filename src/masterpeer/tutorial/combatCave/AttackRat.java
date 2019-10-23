package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class AttackRat extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("it's time to slay some rats")) != null;
    }

    @Override
    public int execute() {
        Npcs.getNearest("Giant rat").interact("Attack");
        Time.sleepUntil(()->Interfaces.firstByText((text) -> text.toLowerCase().contains("pass through the gate and talk to the combat instructor")) != null, Random.nextInt(10000, 30000));
        return Random.nextInt(50, 5000);
    }
}
