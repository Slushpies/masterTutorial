package masterpeer.quests.piratestreasure;

import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.input.menu.ActionOpcodes;
import org.rspeer.script.task.Task;

import static masterpeer.CommonUtils.Tools.gaussian;

public class HandleDialog extends Task {
    String[] dialogChoices = {"I'm in search of treasure", "Ok, I will bring you some rum"};

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
