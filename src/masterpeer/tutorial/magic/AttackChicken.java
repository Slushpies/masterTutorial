package masterpeer.tutorial.magic;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class AttackChicken extends Task {
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("click on this spell to select it")) != null;
    }

    @Override
    public int execute() {
        Npc chicken = Npcs.getNearest("Chicken");
        Magic.cast(Spell.Modern.WIND_STRIKE, chicken);
        return Random.nextInt(500, 5000);
    }
}
