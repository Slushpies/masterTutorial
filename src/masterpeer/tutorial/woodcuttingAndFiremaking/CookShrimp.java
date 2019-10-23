package masterpeer.tutorial.woodcuttingAndFiremaking;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class CookShrimp extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("now it's time to get cooking")) != null
                && Inventory.contains("Raw shrimps")
                && !Inventory.contains("Shrimps")
                && SceneObjects.getNearest("Fire") != null
                && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public int execute() {
        if(!Inventory.isItemSelected()){
            if(Inventory.getFirst("Raw shrimps").interact("Use")){
                Time.sleep(500, 5000);
            }
        }
        SceneObject fire = SceneObjects.getNearest("Fire");
        if(fire != null){
            if(fire.interact("Use")){
                Time.sleepUntil(()->Inventory.contains("Shrimps"), Random.nextInt(15000, 30000));
            }
        }
        return 0;
    }
}
