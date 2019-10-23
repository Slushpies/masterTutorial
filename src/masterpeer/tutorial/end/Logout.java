package masterpeer.tutorial.end;

import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.Script;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.LUMBRIDGE;

public class Logout extends Task {
    @Override
    public boolean validate() {
        return LUMBRIDGE.contains(Players.getLocal());
    }

    @Override
    public int execute() {

        Time.sleep(500, 15000);
        Game.logout();
        return 0;
    }
}
