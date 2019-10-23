package masterpeer.tutorial.beginAndFish;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.input.Keyboard;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.Script;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class EnterName extends Task {
    String[] words = {"apple", "cold", "hot", "grape", "vape", "smoke", "fire", "cat", "white", "blue", "pink", "green"};
    @Override
    public boolean validate() {
        return Interfaces.firstByText((text) -> text.toLowerCase().contains("please pick a unique display")) != null
                && Interfaces.firstByText((text) -> text.toLowerCase().contains("you'll need a unique display name")) != null
                && !Interfaces.firstByText((text) -> text.toLowerCase().contains("you'll need a unique display name")).isVisible()
                && Interfaces.getComponent(162, 45).getText().length() <= 1;
    }

    @Override
    public int execute() {
        String word1 = words[Random.nextInt(0, words.length)];
        String word2 = words[Random.nextInt(0, words.length)];
        String username = word1 + word2 + Random.nextInt(100, 10000);
        Log.info("Entering username: " + username);
        Keyboard.sendText(username);
        Time.sleep(500, 5000);
        Keyboard.pressEnter();
        return Random.nextInt(500, 5000);
    }
}
