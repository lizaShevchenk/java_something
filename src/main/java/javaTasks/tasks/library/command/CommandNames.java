package javaTasks.tasks.library.command;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CommandNames {
    HELP("help", "h"),
    EXIT("exit", "x"),
    ADD_AUTHOR("add author", "add a"),
    ADD_BOOK("add book", "add b"),
    ADD_JOURNAL("add journal", "add j"),
    SHOW_BOOKS("show books", "show b"),
    SHOW_JOURNALS("show journals", "show j"),
    SHOW_AUTHORS("show authors", "show a"),
    DELETE_BOOK("delete book", "del b"),
    DELETE_JOURNAL("delete journal", "del j"),
    DELETE_AUTHOR("delete author", "del a");

    private final String command;
    private final String code;

    CommandNames(String command, String code) {
        this.command = command;
        this.code = code;
    }

    public String getCommand() {
        return command;
    }

    public String getCode() {
        return code;
    }

    public static String getAllCommands() {
        return Arrays.stream(CommandNames.values())
                .map(v -> v.getCode() + " = " + v.getCommand())
                .collect(Collectors.joining(",\n"));
    }

}
