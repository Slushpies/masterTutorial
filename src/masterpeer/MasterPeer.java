package masterpeer;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.input.Camera;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

import java.awt.*;

import static masterpeer.CommonUtils.Tools.formatTime;
import static masterpeer.CommonUtils.Tools.perHour;

@ScriptMeta(developer = "Lyocell", desc = "For gay sex purposez", name = "MasterPeer")
public class MasterPeer extends TaskScript implements RenderListener {
    int startXp = 0;
    long startTime = 0;
    int gained = 0;
    String currentTask = "none";
    int pitch = 0;

    @Override
    public void onStart() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Gui(this);
        });
        Log.info("[Setting paused to true]");
        this.setPaused(true);
        Log.info("[Starting]");
        Log.info("isviewingchatOptions? " + Dialog.isViewingChatOptions());
        Log.info("Dialog is open? " + Dialog.isOpen());
        Log.info("inventory has pot of flour? " + Inventory.contains("Pot of flour"));
        Log.info("inventory has bucket?" + Inventory.contains("Bucket"));
        Log.info("inventory has bucket of milk?" + Inventory.contains("Bucket of milk"));

        /*int lvl = Skills.getLevel(Skill.THIEVING);
        startXp = Skills.getExperience(Skill.THIEVING);
        startTime = System.currentTimeMillis();
        if (lvl < 5) {
            Log.info("Adding men tasks");
            submit(new PickMenTask(), new PouchTask());
        } else if (lvl < 38) {
            Log.info("Adding tea tasks");
            submit(new StallTask(), new DropTask());
        } else
            submit(new EatTask(), new BankTask(), new StunActionTask(), new PickTask(), new NecklaceTask(), new DropTask());
        submit(new AntibanTask());*/
    }

    void afkBreak() { //Take a break to simulate going AFK or getting distracted
        long afkDuration = CommonUtils.Tools.gaussian(10000, 240000, 45000, 30000);
        Log.info("Going afk for " + (afkDuration / 1000) + "s ");
        Time.sleep(afkDuration); //Simulate a break/distraction
        Log.info("Back");
    }

    void printHourly() {
        long hourly = perHour(startXp, Skills.getExperience(Skill.THIEVING), startTime);
        String time = formatTime(System.currentTimeMillis() - startTime);
        Log.info("[" + time + "] Hourly is " + hourly);
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        //Graphics g = renderEvent.getSource();
        if (Camera.getPitch() != pitch) {
            pitch = Camera.getPitch();
            Log.info("New pitch " + pitch);
        }
        if (getCurrent() != null) {
            String taskName = getCurrent().getClass().getSimpleName();
            if (taskName.equals("Logout") || taskName.equals("CloseQuestCompleteInterface")) {
                setStopping(true);
                Log.info("Set stopping true");
            }
            if (!currentTask.equals(taskName)) {
                currentTask = taskName;
                Log.info(currentTask);
                if (Camera.getPitch() != 383) {
                }
            }
        }
    }
}