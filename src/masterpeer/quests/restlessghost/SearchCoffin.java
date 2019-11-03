package masterpeer.quests.restlessghost;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;
import static masterpeer.Constants.RESTLESSGHOST_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndInteractWithObject;

public class SearchCoffin extends Task {
    Position COFFIN_POSITION = new Position(3248, 3193);

    @Override
    public boolean validate() {
        return Varps.get(RESTLESSGHOST_VARP) == 2 && Npcs.getNearest("Restless ghost") == null;
    }

    @Override
    public int execute() {
      walkToPositionAndInteractWithObject(COFFIN_POSITION, "Coffin", "Search");
      return 0;
    }
}
