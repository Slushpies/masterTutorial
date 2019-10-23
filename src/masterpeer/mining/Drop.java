package masterpeer.mining;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class Drop extends Task {
    int COPPER_ORE = 436;
    int IRON_ORE = 440;
    int[] ORE_IDS = {COPPER_ORE, IRON_ORE};
    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public int execute() {
        Time.sleep(gaussian(250, 3000, 1500, 1000));
        while(Inventory.contains(ORE_IDS)){
            Inventory.getFirst(ORE_IDS).interact("Drop");
            Time.sleep(gaussian(5, 200, 40, 25));
        }
        return gaussian(100, 1000, 300, 100);
    }
}
