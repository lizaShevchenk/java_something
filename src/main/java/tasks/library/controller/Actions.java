package tasks.library.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Actions {
    HELP("help"),
    EXIT("exit"),
    ADD_BOOK("add book"),
    ADD_JOURNAL("add journal"),
    SHOW_STORAGE("show storage"),
    DELETE("delete");

    private String actionName;

    Actions(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public static String getAllActions() {
        return Arrays.stream(Actions.values()).map(Actions::getActionName).collect(Collectors.joining(", "));
    }
}
