package tasks.library.command;

public interface Command {

    Boolean canHandle(String command);

    void handle();
}
