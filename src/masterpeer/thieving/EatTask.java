package masterpeer.thieving;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class EatTask extends Task {
    int FOOD_HEALS = 9;
    int eatAt;

    EatTask(){
        eatAt = Random.nextInt(10, Skills.getLevel(Skill.HITPOINTS) - FOOD_HEALS);
    }

    @Override
    public boolean validate() {
        //Log.info(eatAt * 100 / Skills.getLevel(Skill.HITPOINTS));
        //Log.info(Players.getLocal().getHealthPercent());
        if (!Inventory.contains("Salmon")) return false;
        return Health.getCurrent() <= eatAt;
    }

    @Override
    public int execute() {
        //Log.info("Eating");
        int health = Health.getCurrent();
        if(Inventory.getFirst("Salmon").interact("Eat")) {
            Time.sleepUntil(()->Health.getCurrent() > health, 250, 10000);
            eatAt = Random.nextInt(10, Skills.getLevel(Skill.HITPOINTS) - FOOD_HEALS);
        }
        return gaussian(100, 1000, 300, 100);
    }
}
