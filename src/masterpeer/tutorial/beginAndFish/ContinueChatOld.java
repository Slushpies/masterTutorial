package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

public class ContinueChatOld extends Task {
    Position OUTSIDE_RAT_PEN = new Position(3109, 9511);

    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("i can't reach that!")) != null
                && Interfaces.firstByText((text) -> text.toLowerCase().contains("i can't reach that!")).isVisible()
                && Interfaces.firstByText((text) -> text.toLowerCase().contains("you have completed the tasks here")) == null;
    }

    @Override
    public int execute() {
        Time.sleep(250, 2500);
        if (OUTSIDE_RAT_PEN.isPositionWalkable()) {
            Movement.walkTo(OUTSIDE_RAT_PEN.randomize(1));
        }
        //Keyboard.sendKey(' ');
        //Interfaces.getComponent(162, 58, 0).interact(ActionOpcodes.INTERFACE_ACTION);
        return Random.nextInt(250, 2500);
    }
}
