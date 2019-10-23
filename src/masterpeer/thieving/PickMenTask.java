package masterpeer.thieving;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class PickMenTask extends Task implements ChatMessageListener {
    Position LUMBY_TILE = new Position(3223, 3218);
    private boolean stunned;

    @Override
    public boolean validate() {
        if(Inventory.contains(22521)) return Inventory.getFirst(22521).getStackSize() != 28;
        return true;
    }

    @Override
    public int execute() {
        if(stunned == true){
            stunned = false;
            if(Random.nextInt(1, 100) > 80 ) Time.sleep(gaussian(2500, 5000, 4000, 1000));
        }
        Npc man = Npcs.getNearest("Man");
        if (man != null) {
            man.interact("Pickpocket");
        } else {
            Log.info("Man is null");
            Position marketTile = LUMBY_TILE.randomize(1);
            Movement.walkTo(marketTile);
            Time.sleepUntil(() -> Players.getLocal().distance(marketTile) < 3, 250, 10000);
        }
        return gaussian(50, 1500, 600, 400);
    }

    @Override
    public void notify(ChatMessageEvent chatMessageEvent) {
        if(chatMessageEvent.getMessage().contains("been stunned")) stunned = true;

    }
}
