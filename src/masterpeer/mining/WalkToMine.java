package masterpeer.mining;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.Constants.ALKHARID_MINE;

public class WalkToMine extends Task {

    Path path = null;
    int runThreshold = Random.nextInt(1, 100);
    Position minePosition;

    public WalkToMine(Position minePosition){
        this.minePosition = minePosition;
    }

    @Override
    public boolean validate() {
        return minePosition.distance() > 15;
    }

    @Override
    public int execute() {
        Log.info("Mine distance" + minePosition.distance());
        if (Movement.getRunEnergy() > runThreshold) {
            Movement.toggleRun(true);
            runThreshold = Random.nextInt(1, 100);
        }
        if (path == null) {
            Log.info("Path is null, building");
            path = Movement.buildPath(minePosition);
        }
        if (!path.walk()) {
            Log.info("path walk failed, rebuilding");
            path = Movement.buildPath(minePosition);
        }
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 10, Random.nextInt(1500, 15000));
        return 0;
    }
}
