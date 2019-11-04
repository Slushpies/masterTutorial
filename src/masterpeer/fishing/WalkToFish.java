package masterpeer.fishing;

import masterpeer.Constants;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;

public class WalkToFish extends Task {
    Position DRAYNOR_FISH_POSITION = new Position(3086, 3230);
    private Position fishingPosition;
    private final String fishingMethod;
    public WalkToFish(String fishingSpot, String fishingMethod){
      switch(fishingSpot){
          case "Draynor Village":
              this.fishingPosition = DRAYNOR_FISH_POSITION;
              break;
      }
      this.fishingMethod = fishingMethod;
    }

    @Override
    public boolean validate() {
        return !Inventory.isFull() && Npcs.newQuery().names("Fishing spot").actions(fishingMethod).results().nearest() == null;
    }

    @Override
    public int execute() {
        Movement.walkToRandomized(fishingPosition.randomize(2));
        return 0;
    }
}
