package masterpeer.quests.xmarksthespot;

import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.XMARKSTHESPOT_VARP;

public class SecondClue extends Task {
    final Position SECONDCLUE_POSITION = new Position(3203, 3212);

    @Override
    public boolean validate() {
        if(Movement.getDestination() != null && Movement.getDestination().equals(SECONDCLUE_POSITION)){
            return false;
        }
        return Varps.get(XMARKSTHESPOT_VARP) == 3;
    }

    @Override
    public int execute() {
        if (Players.getLocal().getPosition().equals(SECONDCLUE_POSITION)) {
            Inventory.getFirst("Spade").interact("Dig");
            Time.sleepUntil(() -> Varps.get(XMARKSTHESPOT_VARP) == 4, Random.nextInt(10000, 15000));
            return Random.nextInt(250, 5000);
        }
        Movement.walkTo(SECONDCLUE_POSITION);
        //Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
        return 0;
    }
}
