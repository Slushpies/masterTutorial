package masterpeer;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.ui.Log;

public class CommonUtils {
    public static class Tools {
         public static int gaussian(int min, int max, int mean, int stdev) {
            int result;
            double val;
            do {
                val = Random.nextGaussian() * stdev + mean;
                result = (int) Math.round(val);
            } while (result > max || result < min);
            return result;
        }

        public static long perHour(int startValue, int currentValue, long startTime) {
            long valueGained = currentValue - startValue;
            long elapsed = (System.currentTimeMillis() - startTime) / 1000;
            double hoursElapsed = (float) elapsed / 3600;
            return Math.round((1 / hoursElapsed) * valueGained);
        }

        public static String formatTime(long _elapsed) { //Format time to an hours, minutes, seconds string
            long elapsed = _elapsed / 1000;
            String elapsedSeconds = Long.toString(elapsed % 60);
            String elapsedMinutes = Long.toString((elapsed / 60) % 60);
            String elapsedHours = Long.toString((elapsed / 3600) % 24);
            return elapsedHours + ":" + elapsedMinutes + ":" + elapsedSeconds;
        }
    }


}