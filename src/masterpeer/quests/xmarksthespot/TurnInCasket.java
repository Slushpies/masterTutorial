package masterpeer.quests.xmarksthespot;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

import static masterpeer.Constants.XMARKSTHESPOT_VARP;

public class TurnInCasket extends Task {
    final Position VEOS_PORTSARIM_POSITION = new Position(3054, 3247);

    @Override
    public boolean validate() {
        return Varps.get(XMARKSTHESPOT_VARP) == 6;
    }

    @Override
    public int execute() {
        Npc veos = Npcs.getNearest("Veos");
        //Make sure we are within 15 of Veos Port Sarim location to make sure we aren't at Lumbridge's Veos for some reason
        if(veos != null && veos.isPositionInteractable() && VEOS_PORTSARIM_POSITION.distance() < 15){
            veos.interact("Talk-to");
            Time.sleepUntil(() -> Dialog.isOpen(), Random.nextInt(1500, 15000));
            return 0;
        }
        //Veos is null or not interactable, walk towards him
        Movement.walkToRandomized(VEOS_PORTSARIM_POSITION);
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 10, Random.nextInt(1500, 15000));
        return 0;
    }
}
