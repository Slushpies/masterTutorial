package masterpeer.quests.romeoandjuliet;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.PredefinedPath;
import org.rspeer.runetek.api.movement.pathfinding.executor.PathExecutor;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static masterpeer.Constants.ROMEOANDJULIET_VARP;
import static masterpeer.Constants.XMARKSTHESPOT_VARP;

public class GetCadavaBerries extends Task {
    Position CADAVA_BUSH_POSITION = new Position(3279, 3373);
    Position[] romeoToBerriesPositions = {
            new Position(3212, 3428),
            new Position(3217, 3428),
            new Position(3225, 3428),
            new Position(3234, 3429),
            new Position(3241, 3429),
            new Position(3247, 3428),
            new Position(3254, 3428),
            new Position(3260, 3428),
            new Position(3267, 3428),
            new Position(3271, 3428),
            new Position(3277, 3426),
            new Position(3288, 3407),
            new Position(3290, 3388),
            new Position(3287, 3374),
            new Position(3278, 3374)
    };
    Position[] berriesToRomeoPositions;
    PredefinedPath romeoToBerriesPath;
    PredefinedPath berriesToRomeoPath;

    //Position[] romeoToBerriesPositions = (Position[]) ROMEOTOBERRIES_POSITIONS.toArray();
    //Position[] berriesToRomeoPositions = (Position[]) ROMEOTOBERRIES_POSITIONS.toArray();
    public GetCadavaBerries() {
        berriesToRomeoPositions = romeoToBerriesPositions.clone();
        Collections.reverse(Arrays.asList(berriesToRomeoPositions));
    }


    @Override
    public boolean validate() {
        return Varps.get(ROMEOANDJULIET_VARP) <= 40
                && !Inventory.contains("Cadava berries");
    }

    @Override
    public int execute() {
        if (romeoToBerriesPath == null) {
            Log.info("Romeo to berries path is null, building");
            romeoToBerriesPath = PredefinedPath.build(romeoToBerriesPositions);
        }
        SceneObject cadavaBush = SceneObjects.getNearest("Cadava bush");
        if (cadavaBush == null || CADAVA_BUSH_POSITION.distance() > 15) {
            if (romeoToBerriesPath.walk()) {
                Log.info("Walk path from romeo to berries");
                Time.sleepUntil(() -> Movement.getDestinationDistance() < Random.nextInt(1, 10), Random.nextInt(5000, 25000));
            } else {
                Log.info("Couldn't walk predefined path, wait a bit then try to rebuild");
                Time.sleep(Random.nextInt(10000, 20000));
                romeoToBerriesPath = PredefinedPath.build(romeoToBerriesPositions);
                if (romeoToBerriesPath.walk()) {
                    Log.info("Rebuilt path successfully, walk path from romeo to berries");
                    Time.sleepUntil(() -> Movement.getDestinationDistance() < Random.nextInt(1, 10), Random.nextInt(5000, 25000));
                } else {
                    Log.info("Still couldn't walk after rebuilding path, walk to Romeo's location");
                    Movement.walkTo(romeoToBerriesPositions[0]);
                }
            }
        } else if (cadavaBush != null && cadavaBush.isPositionInteractable()) {
            Log.info("Pick cadava berries");
            if (cadavaBush.interact("Pick-from")) {
                Time.sleepUntil(() -> Inventory.contains("Cadava berries"), Random.nextInt(15000, 30000));
                if (Inventory.contains("Cadava berries")) {
                    Log.info("Got cadava berries, heading back to romeo");
                    berriesToRomeoPath = PredefinedPath.build(berriesToRomeoPositions);
                    while (romeoToBerriesPositions[0].distance() > 10) {
                        if (berriesToRomeoPath.walk()) {
                            Log.info("Walk path from berries to romeo");
                            Time.sleepUntil(() -> Movement.getDestinationDistance() < Random.nextInt(1, 10), Random.nextInt(5000, 25000));/**/
                        } else {
                            Log.info("Couldn't walk predefined path, rebuilding");
                            berriesToRomeoPath = PredefinedPath.build(berriesToRomeoPositions);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
