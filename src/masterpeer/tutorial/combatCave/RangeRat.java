package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class RangeRat extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("now you have a bow and some arrows")) != null;
    }

    @Override
    public int execute() {
        if(Inventory.contains("Shortbow")){
            Inventory.getFirst("Shortbow").interact("Wield");
            Time.sleep(500, 5000);
        }
        if(Inventory.contains("Bronze arrow")){
            Inventory.getFirst("Bronze arrow").interact("Wield");
            Time.sleep(500, 5000);
        }
        Npcs.getNearest("Giant rat").interact("Attack");
        Time.sleepUntil(()->Interfaces.firstByText((text) -> text.toLowerCase().contains("you have completed the tasks here")) != null, Random.nextInt(15000, 30000));
        return 0;
    }
}
