package masterpeer.mining;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;

public class MiningUtil {
    static final Position RIMMINGTON_COPPER = new Position(2980, 3246);


    public static Position bestMiningSpot() {
        int miningLevel = Skills.getLevel(Skill.MINING);
        int combatLevel = Players.getLocal().getCombatLevel();
        if (combatLevel < 28) {
            if (miningLevel < 15) {
                return RIMMINGTON_COPPER;
            }
        }
        return null;
    }
}
