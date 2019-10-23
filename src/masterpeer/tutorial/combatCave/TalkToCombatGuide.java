package masterpeer.tutorial.combatCave;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class TalkToCombatGuide extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("speak to the guide and he will tell you all about it")) != null
                || Interfaces.firstByText((text) -> text.toLowerCase().contains("to the combat instructor")) != null;
    }

    @Override
    public int execute() {
        if (Interfaces.getComponent(84, 4) != null) {
            Interfaces.getComponent(84, 4).interact(ActionOpcodes.INTERFACE_ACTION);
            Time.sleep(50, 5000);
        }
        Npc combatGuide = Npcs.getNearest("Combat Instructor");
        if (!combatGuide.isPositionInteractable()) {
            Movement.walkTo(combatGuide.getPosition());
            Time.sleepUntil(() -> Movement.getDestinationDistance() <= 7, Random.nextInt(1500, 15000));
            return Random.nextInt(50, 5000);
        }
        if (combatGuide != null) {
            combatGuide.interact("Talk-to");
            Time.sleepUntil(() -> Dialog.canContinue(), Random.nextInt(5000, 15000));
        }
        return Random.nextInt(50, 5000);
    }
}
