package masterpeer.tutorial.banking;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;

public class ExitPoll extends Task {
    Position ACCOUNT_MANAGEMENT_ROOM = new Position(3125, 3124);

    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("when you're ready, move on through the door indicated")) != null;
    }

    @Override
    public int execute() {
        if(Interfaces.getComponent(310, 2, 11) != null){
            Interfaces.getComponent(310, 2, 11).interact(ActionOpcodes.INTERFACE_ACTION);
            Time.sleep(50, 5000);
        }
        Movement.walkTo(ACCOUNT_MANAGEMENT_ROOM);
        return Random.nextInt(50, 5000);
    }
}
