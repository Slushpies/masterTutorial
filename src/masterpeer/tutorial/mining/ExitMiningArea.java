package masterpeer.tutorial.mining;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class ExitMiningArea extends Task {
    Position OUTSIDE_MINING_AREA = new Position(3095, 9502);

    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("congratulations, you've made your first weapon")) != null;
    }

    @Override
    public int execute() {
        System.out.println("Exit mining area");
        Movement.walkTo(OUTSIDE_MINING_AREA);
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
        return Random.nextInt(500, 5000);
    }
}
