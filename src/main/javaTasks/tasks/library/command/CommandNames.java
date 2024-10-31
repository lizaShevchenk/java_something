package tasks.library.command;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CommandNames {
    HELP("help"),
    EXIT("exit"),
    ADD_BOOK("add book"),
    ADD_JOURNAL("add journal"),
    SHOW_STORAGE("show storage"),
    DELETE("delete");

    private final String commandName;

    CommandNames(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public static String getAllCommandNames() {
        return Arrays.stream(CommandNames.values())
                .map(CommandNames::getCommandName)
                .collect(Collectors.joining(",\n"));
    }

}
