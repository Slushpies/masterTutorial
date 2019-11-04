package masterpeer;

import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

public class Constants {
    /**FISHING**/
    public static final String[] RAWFISH_NAMES = {"Raw shrimps"};
    public enum FISHING_METHODS {
        SMALL_NET
    }
    public enum FISHING_SPOTS {
        DRAYNOR_VILLAGE
    }
    public static Position ALKHARID_MINE = new Position(3300, 3312);
    public static Position WIZARD_HOUSE = new Position(3140, 3087);
    public static Position QUEST_HOUSE = new Position(3086, 3125);
    public static Position RAT_PEN = new Position(3110, 9518);
    public static Position BANK_TILE = new Position(3122, 3123);
    public static Position CHAPEL = new Position(3126, 3106);
    public static Area LUMBRIDGE = Area.rectangular(3231, 3224, 3239, 3215);
    public static Position LUMBRIDGEKITCHEN_POSITION = new Position(3208, 3213);
    public static final String[] JUNK = {"bronze axe", "tinderbox", "small net", "shrimps", "bronze dagger", "copper ore", "shortbow", "bronze arrow", "air rune", "mind rune", "bucket",
            "pot", "bread", "water rune", "earth rune", "body rune"};
    public static final int COOKSASSISTANT_VARP = 29;
    public static final int RUNEMYSTERIES_VARP = 63;
    public static final int PIRATESTREASURE_VARP = 71;
    public static final int RESTLESSGHOST_VARP = 107;
    public static final int ROMEOANDJULIET_VARP = 144;
    public static final int SHEEPSHEARER_VARP = 179;
    public static final int XMARKSTHESPOT_VARP = 2111;


}
