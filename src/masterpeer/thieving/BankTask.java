package masterpeer.thieving;


import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class BankTask extends Task {
    private final Area BANK_AREA = Area.rectangular(3091, 3247, 3098, 3239);
    private String[] trashTable = {"Cabbage seed", "Potato seed", "Marigold seed", "Asgarnian seed", "Jangerberry seed",
            "Dwellberry seed", "Cadavaberry seed", "Tomato seed", "Barley seed", "Onion seed", "Poison ivy seed",
            "Woad seed", "Yanillian seed", "Hammerstone seed", "Redberry seed", "Jute seed", "Strawberry seed", "Jug",
            "Jug of water", "Jug of wine", "Bottle of wine", "Nasturtium seed", "Redberry seed", "Sweetcorn seed", "Krandorian seed", "Whiteberry seed",
            "Guam seed", "Cactus seed", "Mushroom spore", "Potato cactus seed", "Irit seed", "Marrentill seed", "Tarromin seed", "Grapes"};

    @Override
    public boolean validate() {
        if (!Inventory.contains("Salmon")) {
            //Log.info("No salmon");
            return true;
        }
        if (!Inventory.contains("Dodgy necklace")) {
            if (!Equipment.contains("Dodgy")) {
                //Log.info("no dodgy necklace equipped or in inventory");
                return true;
            }
        }
        return false;
    }

    @Override
    public int execute() {
        if(!BANK_AREA.contains(Players.getLocal())) {
            Position bankTile = BANK_AREA.getCenter().randomize(1);
            //Log.info("Walk to bank");
            Movement.walkTo(bankTile);
            Time.sleepUntil(() -> Players.getLocal().distance(bankTile) < 3, 250, 10000);
        } else {
            if(Bank.isClosed()){
                if(Bank.open()){
                    Time.sleepUntil(() -> Bank.isOpen(), 250, 10000);
                }
            } else {
                if(Inventory.getCount("Salmon") + Inventory.getCount("Dodgy necklace") < Inventory.getCount()){
                    Bank.depositAllExcept(0);
                } else if(Inventory.getCount("Salmon") < 20) {
                    //Log.info("Less than 20 salmon in inventory");
                    Bank.withdraw("Salmon", 20 - Inventory.getCount("Salmon"));
                    Time.sleepUntil(()->Inventory.getCount("Salmon") >= 20, 250, 3000);
                    if(Inventory.getCount("Dodgy necklace") < 3){
                        Bank.withdraw("Dodgy necklace", 3 - Inventory.getCount("Dodgy necklace"));
                        Time.sleepUntil(()->Inventory.getCount("Dodgy necklace") > 0, 250, 3000);
                    }
                } else if(Inventory.getCount("Dodgy necklace") < 3){
                    //Log.info("Less than 3 dodgy necklaces in inventory");
                    Bank.withdraw("Dodgy Necklace", 3 - Inventory.getCount("Dodgy necklace"));
                    Time.sleepUntil(()->Inventory.getCount("Dodgy necklace") >= 3, 250, 3000);
                }
            }
        }
        return gaussian(100, 500, 200, 100);
    }

    private int gaussian(int min, int max, int mean, int stdev){
        int result;
        double val;
        do {
            val = Random.nextGaussian() * stdev + mean;
            result = (int) Math.round(val);
        } while (result > max || result < min);
        return result;
    }
}
