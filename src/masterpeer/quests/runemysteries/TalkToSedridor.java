package masterpeer.quests.runemysteries;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.RUNEMYSTERIES_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToSedridor extends Task {
    Position SEDRIDOR_POSITION = new Position(3104, 9571);

    @Override
    public boolean validate() {
        return Varps.get(RUNEMYSTERIES_VARP) == 1 || Varps.get(RUNEMYSTERIES_VARP) == 5 && !Dialog.isOpen();
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(SEDRIDOR_POSITION, "Sedridor");
        return 0;
    }
}
