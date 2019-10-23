package masterpeer.thieving;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class PickTask extends Task {
    private final Position MARKET_TILE = new Position(3081, 3250, 0);

    @Override
    public boolean validate() {
        if (!Inventory.contains("Salmon")) return false;
        if (!Inventory.contains("Dodgy necklace") && !Equipment.contains("Dodgy necklace")) return false;
        return !Inventory.isFull();
    }

    @Override
    public int execute() {
        Npc farmer = Npcs.getNearest("Master Farmer");
        if (farmer != null) {
            farmer.interact("Pickpocket");
        } else {
            //Log.info("Walk to market");
            Position marketTile = MARKET_TILE.randomize(1);
            Movement.walkTo(marketTile);
            Time.sleepUntil(() -> Players.getLocal().distance(marketTile) < 3, 250, 10000);
        }
        return gaussian(10, 1000, 200, 100);
    }
}
