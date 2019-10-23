package masterpeer.combat;

import org.rspeer.runetek.api.movement.position.Position;

public class CombatUtil {
    static final Position SEAGULL_POS = new Position(3028, 3235);
    static final Position CHICKEN_POS = new Position(3177, 3294);
    static final Position GOBLIN_POS = new Position(3145, 3302);

    public static Position monsterPosition(String monsterName){
        if(monsterName.equals("seagull")) return SEAGULL_POS;
        else if(monsterName.equals("chicken")) return CHICKEN_POS;
        else if(monsterName.equals("goblin")) return GOBLIN_POS;
        return null;
    }
}
