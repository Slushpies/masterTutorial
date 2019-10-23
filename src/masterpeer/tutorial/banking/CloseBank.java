package masterpeer.tutorial.banking;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.script.task.Task;

public class CloseBank extends Task {
    @Override
    public boolean validate() {
        return false;
        //return Interfaces.firstByText((text) -> text.toLowerCase().contains("click on the indicated booth")) != null;
    }

    @Override
    public int execute() {
        Bank.open();
        Time.sleepUntil(Bank::isOpen, Random.nextInt(10000, 30000));
        return Random.nextInt(50, 5000);
    }
}
