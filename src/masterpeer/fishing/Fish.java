package masterpeer.fishing;

import masterpeer.Constants;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class Fish extends Task {
    private String fishingMethod;
private boolean cookFish;

    public Fish(String fishingMethod, boolean cookFish) {
        this.fishingMethod = fishingMethod;
        this.cookFish = cookFish;
    }

    @Override
    public boolean validate() {
        if(!cookFish && Inventory.isFull()){
            return false;
        }
        //Leave a free space open for logs if we are cooking our fish
        if(cookFish && Inventory.getFreeSlots() <= 1){
            return false;
        }
        return !Inventory.isFull()
                && Players.getLocal().getTargetIndex() == -1
                && Npcs.newQuery().names("Fishing spot").actions(fishingMethod).results().nearest() != null;
    }

    @Override
    public int execute() {
        Npc fishingSpot = Npcs.newQuery().names("Fishing spot").actions(fishingMethod).results().nearest();
        fishingSpot.interact(fishingMethod);
        return gaussian(50, 30000, 5000, 4500);
    }
}
