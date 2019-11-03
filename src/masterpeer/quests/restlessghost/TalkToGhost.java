package masterpeer.quests.restlessghost;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.RESTLESSGHOST_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToGhost extends Task {
    Position GHOST_POSITION = new Position(3249, 3194);
    @Override
    public boolean validate() {
        return Varps.get(RESTLESSGHOST_VARP) == 2 && Npcs.getNearest("Restless ghost") != null;
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(GHOST_POSITION, "Restless ghost");
        return 0;
    }
}
