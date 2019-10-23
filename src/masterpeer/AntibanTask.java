package masterpeer;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class AntibanTask extends Task {
    long nextBreak = 0;
    int timeBetweenBreak = 0;

    public AntibanTask(){
        timeBetweenBreak = Random.nextInt((1 * 60000), (30 * 60000));
        nextBreak = System.currentTimeMillis() + timeBetweenBreak; //Next break in between 5 and 45 mins
        Log.info("Next break in " + (timeBetweenBreak / 60000) + " minutes");
    }

    @Override
    public boolean validate() {
        return System.currentTimeMillis() > nextBreak;
    }

    @Override
    public int execute() {
        long afkDuration = CommonUtils.Tools.gaussian(10000, 240000, 45000, 30000);
        Log.info("Going afk for " + (afkDuration/1000) + "s ");
        Time.sleep(afkDuration); //Simulate a break/distraction
        Log.info("Back");
        timeBetweenBreak = Random.nextInt((1 * 60000), (30 * 60000));
        nextBreak = System.currentTimeMillis() + timeBetweenBreak; //Next break in between 5 and 45 mins
        Log.info("Next break in " + (timeBetweenBreak / 60000) + " minutes");
        return 0;
    }
}
