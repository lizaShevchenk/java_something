package javaTasks.tasks.library.command;

import javaTasks.tasks.library.controller.View;

public class Help implements Command {

    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public Boolean canHandle(String command) {
        return command.equalsIgnoreCase(CommandNames.HELP.getCommandName());
    }

    @Override
    public void handle() {
        view.write(String.format("Доступны команды: %s", CommandNames.getAllCommandNames()));
    }
}
