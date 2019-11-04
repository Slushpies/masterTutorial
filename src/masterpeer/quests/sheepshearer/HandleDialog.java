package masterpeer.quests.sheepshearer;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class HandleDialog extends Task {
    String[] dialogChoices = {"I'm looking for a quest", "Yes okay. I can do that.", "Of course!", "I'm something of an expert actually!"};

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
