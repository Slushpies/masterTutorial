package masterpeer.quests.romeoandjuliet;

import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class HandleDialog extends Task {
    String[] dialogChoices = {"Yes, I have seen her actually", "Yes, ok, I'll let her know", "Ok, thanks", "Talk about Romeo"};

    @Override
    public boolean validate() {
        return Dialog.isOpen();
    }

    @Override
    public int execute() {
        if (Dialog.canContinue()) {
            Dialog.processContinue();
        } else if (Dialog.isViewingChatOptions()) {
            //Handle apothecary
            if (Dialog.getChatOption((text) -> text.contains("Can you make potions for me")) != null && Dialog.getChatOption((text) -> text.contains("Can you make potions for me")).isVisible()) {
                Dialog.getChatOption((text) -> text.contains("Talk about something else")).interact(ActionOpcodes.BUTTON_DIALOG);
            }
            for (String choice : dialogChoices) {
                if (Dialog.getChatOption((text) -> text.contains(choice)) != null && Dialog.getChatOption((text) -> text.contains(choice)).isVisible()) {
                    Dialog.getChatOption((text) -> text.contains(choice)).interact(ActionOpcodes.BUTTON_DIALOG);
                }
            }
        }
        return gaussian(50, 15000, 2500, 2000);
    }
}
