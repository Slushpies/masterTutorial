package masterpeer.quests.romeoandjuliet;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.XMARKSTHESPOT_VARP;

public class GetCadavaBerries extends Task {
    Position CADAVA_BUSH_POSITION = new Position(3279, 3373);

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        SceneObject cadavaBush = SceneObjects.getNearest("Cadava bush");
        if(cadavaBush != null && cadavaBush.isPositionInteractable()){
            cadavaBush.interact("Pick-from");
            Time.sleepUntil(() -> Inventory.contains("Cadava berries"), Random.nextInt(50, 15000));
        }
        return 0;
    }
}
