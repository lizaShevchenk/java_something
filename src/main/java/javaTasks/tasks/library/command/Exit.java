package javaTasks.tasks.library.command;

import javaTasks.tasks.library.controller.View;

public class Exit implements  Command {
    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.EXIT.getCommandName());
    }

    @Override
    public void handle() {
        view.write("See you soon. Bye!");
        System.exit(0);
    }
}
