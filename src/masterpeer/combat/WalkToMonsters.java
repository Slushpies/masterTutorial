package masterpeer.combat;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static masterpeer.combat.CombatUtil.monsterPosition;

public class WalkToMonsters extends Task {
    Position outsideGate = new Position(3260, 3228);
    Position grandExchange = new Position(3164, 3485);
    String[] lootNames;

    Path path = null;
    Position monsterPos;
    int runThreshold = Random.nextInt(1, 100);

    public WalkToMonsters(String monsterName, String[] lootNames){
        this.monsterPos = monsterPosition(monsterName);
        this.lootNames = lootNames;
    }

    @Override
    public boolean validate() {
        if (Inventory.isFull() && Inventory.contains(lootNames)) {
            return false;
        }
        return monsterPos != null && monsterPos.distance() > 15;
    }

    @Override
    public int execute() {
        Log.info("monster distance " + monsterPos.distance());
        if (Movement.getRunEnergy() > runThreshold) {
            Movement.toggleRun(true);
            runThreshold = Random.nextInt(1, 100);
        }
        if (path == null) {
            Log.info("Path is null, building");
            path = Movement.buildPath(monsterPos);
        }
        if (!path.walk()) {
            Log.info("path walk failed, rebuilding");
            path = Movement.buildPath(monsterPos);
        }
        Time.sleepUntil(() -> Movement.getDestinationDistance() <= 10, Random.nextInt(1500, 15000));
        return 0;
    }
}
