package masterpeer.tutorial.kitchen;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.QUEST_HOUSE;

public class ExitKitchen extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("you've baked your first loaf of bread")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("follow the path to the next guide")) != null;
    }

    @Override
    public int execute() {
        Movement.walkTo(QUEST_HOUSE);
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
        return Random.nextInt(500, 5000);
    }
}
