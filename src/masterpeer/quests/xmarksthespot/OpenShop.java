package masterpeer.quests.xmarksthespot;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.quests.QuestUtils.STARTER_JUNK;

public class OpenShop extends Task {
    Position LUMBRIDGE_GENERALSTORE = new Position(3212, 3246);

    @Override
    public boolean validate() {
        return !Inventory.contains("Spade")
                && Inventory.getCount(true,"Coins") >= 3 || Inventory.contains(STARTER_JUNK)
                && !Shop.isOpen();
    }

    @Override
    public int execute() {
        Npc shopKeeper = Npcs.getNearest("Shop keeper");
        if (shopKeeper != null && shopKeeper.isPositionInteractable()) {
            shopKeeper.interact("Trade");
            Time.sleepUntil(() -> Shop.isOpen(), Random.nextInt(1500, 15000));
            return 0;
        }
        //Shop keeper is loaded but not interactable, walk towards them (might be behind a door etc.)
        if (shopKeeper != null && !shopKeeper.isPositionInteractable()) {
            Movement.walkToRandomized(shopKeeper);
            Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
            return 0;
        }
        //Shop keeper is null and not interactable, walk towards lumbridge store
        Movement.walkToRandomized(LUMBRIDGE_GENERALSTORE.randomize(1));
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 10, Random.nextInt(1500, 15000));
        return 0;
    }
}
