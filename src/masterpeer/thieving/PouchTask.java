package masterpeer.thieving;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class PouchTask extends Task {
    @Override
    public boolean validate() {
        return Inventory.contains(22521) && Inventory.getFirst(22521).getStackSize() == 28;
    }

    @Override
    public int execute() {
        Inventory.getFirst(22521).interact("Open-all");
        return gaussian(300, 800, 500, 200);
    }
}
