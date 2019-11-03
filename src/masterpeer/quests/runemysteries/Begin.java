package masterpeer.quests.runemysteries;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class Begin extends Task {
    Position DUKE_POSITION = new Position(3209, 3221, 1);

    @Override
    public boolean validate() {
        return Varps.get(63) == 0
                && !Dialog.isOpen();
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(DUKE_POSITION, "Duke Horacio");
        return 0;
    }
}
