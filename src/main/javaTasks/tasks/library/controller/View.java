package tasks.library.controller;

public interface View {

    String read();

    void write(String input);

    int readInt();

    int readValueByPattern();
}
