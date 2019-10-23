package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

public class CharacterDesign extends Task {
    int[] designComponents = {106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 121, 122, 123, 124, 125, 127, 129, 130, 131, 137};
    @Override
    public boolean validate() {
        return Interfaces.getComponent(269, 99) != null;
    }

    @Override
    public int execute() {
        Time.sleep(500, 5000);
        int customizationsCount = Random.nextInt(0, 20);
        for(int i = 0; i < customizationsCount; i++){
            int designComponent = designComponents[Random.nextInt(0, designComponents.length)];
            Interfaces.getComponent(269, designComponent).interact(ActionOpcodes.BUTTON_INPUT);
            Time.sleep(500, 5000);
        }
        Interfaces.getComponent(269, 99).interact(ActionOpcodes.BUTTON_INPUT);
        return Random.nextInt(500, 5000);
    }
}
