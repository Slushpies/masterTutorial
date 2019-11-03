package masterpeer.quests.xmarksthespot;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.XMARKSTHESPOT_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToVeos extends Task {
    Position VEOS_POSITION = new Position(3228, 3241);

    @Override
    public boolean validate() {
        return Varps.get(XMARKSTHESPOT_VARP) < 2 && Inventory.contains("Spade") && !Dialog.isOpen();
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(VEOS_POSITION, "Veos");
        return 0;
    }
}
