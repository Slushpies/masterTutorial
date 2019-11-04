package masterpeer.quests.cooksassistant;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class HandleDialog extends Task {
    String[] dialogChoices = {"Can you make me a cake", "I'm always happy to help a cook in distress"};

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
        return gaussian(50, 15000, 2500, 2000);
    }
}
