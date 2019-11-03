package masterpeer.quests.romeoandjuliet;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.ROMEOANDJULIET_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndTalkToNpc;

public class TalkToRomeo extends Task {
    Position ROMEO_POSITION = new Position(3208, 3426);
    //Quest close button : 277, 16, 0
    @Override
    public boolean validate() {
        return Varps.get(ROMEOANDJULIET_VARP) == 0 || (Varps.get(ROMEOANDJULIET_VARP) == 20 && Inventory.contains("Cadava berries"))
                || Varps.get(ROMEOANDJULIET_VARP) == 60;
    }

    @Override
    public int execute() {
        walkToPositionAndTalkToNpc(ROMEO_POSITION, "Romeo");
        return 0;
    }
}
