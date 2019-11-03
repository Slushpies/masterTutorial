package masterpeer.quests.restlessghost;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;
import static masterpeer.Constants.RESTLESSGHOST_VARP;

public class ReturnSkullToCoffin extends Task {
    Position COFFIN_POSITION = new Position(3248, 3193);

    @Override
    public boolean validate() {
        return Varps.get(RESTLESSGHOST_VARP) == 4;
    }

    @Override
    public int execute() {
        //Interact with object if it is interactable (must be within a distance of 7, so we don't pick a wrong object with the same name
        SceneObject coffin = SceneObjects.getNearest("Coffin");
        if (coffin != null && coffin.isPositionInteractable() && coffin.getPosition().distance(COFFIN_POSITION) < 7) {
            if(coffin.containsAction("Open")){
                coffin.interact("Open");
            } else {
                Item skull = Inventory.getFirst("Ghost's skull");
                Inventory.use((item)->item.getName().equals("Ghost's skull"), coffin);
            }
            Time.sleep(gaussian(50, 10000, 5000, 3000));
            return 0;
        }
        //Object is loaded but not interactable, walk towards it (might be behind a door etc.)
        if (coffin != null && !coffin.isPositionInteractable()) {
            Log.info("Object is loaded but not interactable, walk towards them (might be behind a door etc.)");
            Movement.walkToRandomized(coffin);
            Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
            return 0;
        }
        //Object is null and not interactable, walk towards position
        Log.info("Object is null and not interactable, walk towards position");
        Movement.walkToRandomized(COFFIN_POSITION.randomize(1));
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
        return 0;
    }
}
