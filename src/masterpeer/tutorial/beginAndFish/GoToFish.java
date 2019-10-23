package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.Constants.ALKHARID_MINE;

public class GoToFish extends Task {
    private Position FISHING_SPOT = new Position(3102, 3096);
    Path path = null;

    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("time to meet your first instructor")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("follow the path to find the next instructor")) != null
                && FISHING_SPOT.distance() > 8;
    }

    @Override
    public int execute() {
        Movement.walkTo(FISHING_SPOT);
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
        return Random.nextInt(500, 5000);
    }
}
