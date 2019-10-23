package masterpeer.tutorial.woodcuttingAndFiremaking;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class LightLogs extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("it's time to light a fire")) != null
                || (Interfaces.firstByText((text) -> text.toLowerCase().contains("now it's time to get cooking")) != null
                && SceneObjects.getNearest("Fire") == null
                && Inventory.contains("Logs"))
                && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public int execute() {
        Position ourPosition = Players.getLocal().getPosition();
        if (SceneObjects.newQuery().names("Fire").on(ourPosition).results().size() > 0) {
            Position newPosition = ourPosition.randomize(1);
            while (!newPosition.isPositionWalkable()) {
                newPosition = ourPosition.randomize(1);
            }
            Movement.walkTo(newPosition);
            return Random.nextInt(1500, 5000);
        }
        if (!Inventory.isItemSelected()) {
            if (Inventory.getFirst("Tinderbox").interact("Use")) {
                Time.sleep(500, 5000);
            }
        } else {
            if (Inventory.getFirst("Logs").interact("Use")) {
                Time.sleepUntil(() -> Interfaces.firstByText((text) -> text.toLowerCase().contains("now it's time to get cooking")) != null, Random.nextInt(10000, 30000));
            }
        }
        return Random.nextInt(500, 5000);
    }
}
