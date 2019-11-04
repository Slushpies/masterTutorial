package masterpeer.quests.cooksassistant;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.COOKSASSISTANT_VARP;
import static masterpeer.quests.QuestUtils.walkToPositionAndInteractWithObject;

public class MakeFlour extends Task {
    Position WHEATFIELD_POSITION = new Position(3162, 3291);
    Position MILLUPSTAIRS_POSITION = new Position(3165, 3307, 2);
    Position MILLDOWNSTAIRS_POSITION = new Position(3165, 3306);
    Boolean grainWaiting = false; //Set to true if grain is waiting to be processed in the hopper
    Boolean flourWaiting = false; //Set to true if flour is waiting to be collected from the hopper
    int OPERATING_HOPPER_ANIMATION = 3571;

    @Override
    public boolean validate() {
        return Varps.get(COOKSASSISTANT_VARP) < 2
                && Inventory.contains("Egg")
                && (Inventory.contains("Bucket") || Inventory.contains("Bucket of milk"))
                && Inventory.contains("Pot")
                && !Inventory.contains("Pot of flour");
    }

    @Override
    public int execute() {
        if (!Inventory.contains("Grain") && !flourWaiting && !grainWaiting) {
            walkToPositionAndInteractWithObject(WHEATFIELD_POSITION, "Wheat", "Pick");
        } else if (Inventory.contains("Grain") && !grainWaiting && !flourWaiting) {
            int grainCount = Inventory.getCount("Grain");
            walkToPositionAndInteractWithObject(MILLUPSTAIRS_POSITION, "Hopper", "Fill");
            if (Inventory.getCount("Grain") < grainCount) {
                grainWaiting = true;
            }
        } else if (grainWaiting && !flourWaiting) {
            SceneObject hopperControls = SceneObjects.getNearest("Hopper controls");
            if (hopperControls != null && hopperControls.interact("Operate")) {
                Time.sleepUntil(() -> Players.getLocal().getAnimation() == OPERATING_HOPPER_ANIMATION, Random.nextInt(20000, 40000));
                flourWaiting = true;
            }
        } else if (flourWaiting == true) {
            walkToPositionAndInteractWithObject(MILLDOWNSTAIRS_POSITION, "Flour bin", "Empty");
        }
        return 0;
    }
}
