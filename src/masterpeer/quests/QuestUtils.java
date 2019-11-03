package masterpeer.quests;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

import static masterpeer.CommonUtils.Tools.gaussian;

public class QuestUtils {
    public static String[] STARTER_JUNK = {"Bread", "Bronze Dagger"};

    public static void walkToPositionAndTalkToNpc(Position position, String npcName) {
        if (Dialog.isOpen()) {
            return;
        }
        //Talk to npc if they are interactable
        Npc npc = Npcs.getNearest(npcName);
        if (npc != null && npc.isPositionInteractable()) {
            npc.interact("Talk-to");
            Time.sleepUntil(Dialog::isOpen, Random.nextInt(1500, 15000));
            Time.sleep(Random.nextInt(50, 1000));
            return;
        }
        //Npc is loaded but not interactable, walk towards them (might be behind a door etc.)
        if (npc != null && !npc.isPositionInteractable()) {
            Log.info("Npc is loaded but not interactable, walk towards them (might be behind a door etc.)");
            Movement.walkToRandomized(npc);
            //Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
            return;
        }
        //Npc is null and not interactable, walk towards position
        Log.info("Npc is null and not interactable, walk towards position");
        Movement.walkToRandomized(position.randomize(1));
        //Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
    }

    public static void walkToPositionAndInteractWithNpc(Position position, String npcName, String action) {
        //Talk to npc if they are interactable
        Npc npc = Npcs.newQuery().names(npcName).actions(action).results().nearest();
        if (npc != null && npc.isPositionInteractable()) {
            npc.interact(action);
            Time.sleep(gaussian(50, 15000, 6000, 5000));
            return;
        }
        //Npc is loaded but not interactable, walk towards them (might be behind a door etc.)
        if (npc != null && !npc.isPositionInteractable()) {
            Log.info("Npc is loaded but not interactable, walk towards them (might be behind a door etc.)");
            Movement.walkToRandomized(npc);
            return;
        }
        //Npc is null and not interactable, walk towards position
        Log.info("Npc is null and not interactable, walk towards position");
        Movement.walkToRandomized(position.randomize(1));
    }

    public static void walkToPositionAndInteractWithNpc(Position position, String npcName, String action, Predicate<? super Npc> predicate) {
        //Talk to npc if they are interactable
        Npc npc = Npcs.newQuery().names(npcName).actions(action).filter(predicate).results().nearest();
        if (npc != null && npc.isPositionInteractable()) {
            npc.interact(action);
            Time.sleep(gaussian(50, 15000, 6000, 5000));
            return;
        }
        //Npc is loaded but not interactable, walk towards them (might be behind a door etc.)
        if (npc != null && !npc.isPositionInteractable()) {
            Log.info("Npc is loaded but not interactable, walk towards them (might be behind a door etc.)");
            Movement.walkToRandomized(npc);
            return;
        }
        //Npc is null and not interactable, walk towards position
        Log.info("Npc is null and not interactable, walk towards position");
        Movement.walkToRandomized(position.randomize(1));
    }

    public static void walkToPositionAndInteractWithObject(Position position, String objectName, String action) {
        //Interact with object if it is interactable (must be within a distance of 7, so we don't pick a wrong object with the same name
        SceneObject sceneObject = SceneObjects.getNearest(objectName);
        if (sceneObject != null && sceneObject.isPositionInteractable() && sceneObject.getPosition().distance(position) < 7) {
            sceneObject.interact(action);
            Time.sleep(gaussian(50, 15000, 6000, 5000));
            return;
        }
        //Object is loaded but not interactable, walk towards it (might be behind a door etc.)
        if (sceneObject != null && !sceneObject.isPositionInteractable() && sceneObject.getPosition().distance(position) < 7) {
            Log.info("Object is loaded but not interactable, walk towards them (might be behind a door etc.)");
            Movement.walkToRandomized(sceneObject);
            //Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
            return;
        }
        //Object is null and not interactable, walk towards position
        Log.info("Object is null and not interactable, walk towards position");
        Movement.walkToRandomized(position.randomize(1));
        //Time.sleepUntil(() -> Movement.getDestinationDistance() <= Random.nextInt(1, 10), Random.nextInt(1500, 15000));
    }

    public static void walkToPositionAndPickupItem(Position position, String itemName) {
        //Pick up item if it is interactable
        Pickable pickable = Pickables.getNearest(itemName);
        if (pickable != null && pickable.isPositionInteractable()) {
            pickable.interact("Take");
            Time.sleep(gaussian(50, 10000, 5000, 3000));
            return;
        }
        //Item is loaded but not interactable, walk towards it (might be behind a door etc.)
        if (pickable != null && !pickable.isPositionInteractable()) {
            Log.info("Pickable is loaded but not interactable, walk towards them (might be behind a door etc.)");
            Movement.walkToRandomized(pickable);
            return;
        }
        //Item is null and not interactable, walk towards position
        Log.info("Pickable is null and not interactable, walk towards position");
        Movement.walkToRandomized(position.randomize(1));
    }
}
