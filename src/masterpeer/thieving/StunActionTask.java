package masterpeer.thieving;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class StunActionTask extends Task implements ChatMessageListener {
    private boolean stunned = false;
    int FOOD_HEALS = 9;
    private String[] trashTable = {"Cabbage seed", "Potato seed", "Marigold seed", "Asgarnian seed", "Jangerberry seed",
            "Dwellberry seed", "Cadavaberry seed", "Tomato seed", "Barley seed", "Onion seed", "Poison ivy seed",
            "Woad seed", "Yanillian seed", "Hammerstone seed", "Redberry seed", "Jute seed", "Strawberry seed", "Jug",
            "Jug of water", "Jug of wine", "Bottle of wine", "Nasturtium seed", "Redberry seed", "Sweetcorn seed", "Krandorian seed", "Whiteberry seed",
            "Guam seed", "Cactus seed", "Mushroom spore", "Potato cactus seed", "Irit seed", "Marrentill seed", "Tarromin seed", "Grapes", "Watermelon seed"};

    @Override
    public boolean validate() {
        return stunned;
    }

    @Override
    public int execute() {
        stunned = false;
        //Log.info("We're stunned");
        if (Inventory.contains(trashTable) && Random.nextInt(0, 100) > 60) {
            //Log.info("Stun dropping");
            Time.sleep(gaussian(500, 2000, 1000, 100));
            while (Inventory.contains(trashTable)) {
                Inventory.getFirst(trashTable).interact("Drop");
                Time.sleep(gaussian(10, 100, 25, 25));
            }
            Time.sleep(gaussian(100, 1000, 200, 100));
        } else if(Health.getCurrent() < (Skills.getLevel(Skill.HITPOINTS) - FOOD_HEALS)) {
            if(Random.nextInt(0, 100) > 60);
            //Log.info("Stun eating");
            Time.sleep(gaussian(500, 2000, 1000, 100));
            int health = Health.getCurrent();
            if(Inventory.getFirst("Salmon").interact("Eat")) {
                Time.sleepUntil(()->Health.getCurrent() > health, 250, 10000);
            }
            Time.sleep(gaussian(100, 1000, 200, 100));
        }

        return gaussian(25, 200, 100, 100);
    }

    @Override
    public void notify(ChatMessageEvent chatMessageEvent) {
        if(chatMessageEvent.getMessage().contains("been stunned")) stunned = true;
    }
}
