package masterpeer.quests.runemysteries;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class HandleDialog extends Task {
    String[] dialogChoices = {"Have you any quests for me?", "Sure, no problem", "I'm looking for the head wizard", "Ok, here you are", "Yes, certainly",
    "I have been sent here with a package for you"};

    @Override
    public boolean validate() {
        return Dialog.isOpen();
    }

    @Override
    public int execute() {
        if (Dialog.canContinue()) {
            Dialog.processContinue();
        } else if (Dialog.isViewingChatOptions()) {
            for (String choice : dialogChoices) {
                if (Dialog.getChatOption((text) -> text.contains(choice)) != null) {
                    Dialog.getChatOption((text) -> text.contains(choice)).interact(ActionOpcodes.BUTTON_DIALOG);
                }
            }
        }
        return Random.nextInt(250, 5000);
    }
}
