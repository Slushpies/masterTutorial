package masterpeer.thieving;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.CommonUtils.Tools.gaussian;

public class DropTask extends Task {
    private String[] trashTable = {"Cabbage seed", "Potato seed", "Marigold seed", "Asgarnian seed", "Jangerberry seed",
            "Dwellberry seed", "Cadavaberry seed", "Tomato seed", "Barley seed", "Onion seed", "Poison ivy seed",
            "Woad seed", "Yanillian seed", "Hammerstone seed", "Redberry seed", "Jute seed", "Strawberry seed", "Jug",
            "Jug of water", "Jug of wine", "Bottle of wine", "Nasturtium seed", "Redberry seed", "Sweetcorn seed", "Krandorian seed", "Whiteberry seed",
            "Guam seed", "Cactus seed", "Mushroom spore", "Potato cactus seed", "Irit seed", "Marrentill seed", "Tarromin seed", "Grapes", "Watermelon seed", "Cup of tea"};


    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public int execute() {
        //Log.info("Dropping");
        Time.sleep(gaussian(250, 3000, 1500, 1000));
        while(Inventory.contains(trashTable)){
            Inventory.getFirst(trashTable).interact("Drop");
            Time.sleep(gaussian(10, 100, 25, 25));
        }
        return gaussian(100, 1000, 300, 100);
    }
}
