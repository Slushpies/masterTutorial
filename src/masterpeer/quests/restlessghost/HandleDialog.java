package masterpeer.quests.restlessghost;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

public class HandleDialog extends Task {
    String[] dialogChoices = {"I'm looking for a quest", "Ok, let me help then", "Father Aereck sent me", "He's got a ghost haunting his graveyard",
    "Yup, now tell me what the problem is"};

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
