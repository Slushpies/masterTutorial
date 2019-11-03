package masterpeer.quests.romeoandjuliet;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.ROMEOANDJULIET_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToApothecary extends Task {
    Position APOTHECARY_POSITION = new Position(3196, 3403);

    @Override
    public boolean validate() {
        return !Dialog.isOpen() && Varps.get(ROMEOANDJULIET_VARP) == 40;
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(APOTHECARY_POSITION, "Apothecary");
        return 0;
    }
}
