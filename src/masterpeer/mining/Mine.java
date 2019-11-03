package masterpeer.mining;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.Constants.ALKHARID_MINE;

public class Mine extends Task {
    final int COPPER_ROCK = 10943;
    final int[] IRON_ROCKS = {11364, 11365};
    SceneObject rock = null;
    private Position minePosition;

    public Mine(Position minePosition){
        this.minePosition = minePosition;
    }
    @Override
    public boolean validate() {
        return (Inventory.contains((item) -> item.getName().contains("pick")) || Equipment.contains((item) -> item.getName().contains("pick")))
                && !Players.getLocal().isAnimating() && !Inventory.isFull() && minePosition.distance() <= 15;
    }

    @Override
    public int execute() {
        Time.sleep(1, 3000);
        rock = SceneObjects.getNearest(COPPER_ROCK);
        if (rock != null) {
            if (rock.interact("Mine")) {
                Log.info("Mined rock - " + "inventory count: " + Inventory.getCount());
            }
        }
        return Random.nextInt(1500, 3000);
    }
}
