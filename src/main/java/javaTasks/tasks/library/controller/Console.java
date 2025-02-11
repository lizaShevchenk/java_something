package javaTasks.tasks.library.controller;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Console implements View {

   private final static Scanner scanner = new Scanner(System.in);

    public Console() {}

    @Override
    public int readInt() {
        try {
            return Integer.parseInt(read());
        } catch (NumberFormatException e) {
            write("Can't read int value, please try again");
            readInt();
        }
        return 0;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String input) {
        System.out.println(input);
    }

    @Override
    public int readValueByPattern() {
        String currentYear = Year.now().toString();
        try {
            return Integer.parseInt(scanner.next(Pattern.compile(
                    String.format("(19\\d{2}|20[01]\\d|202[0-%s])", currentYear.substring(currentYear.length() - 1)))));
        } catch (InputMismatchException | NumberFormatException e) {
            write("Введите корректный год публикации: ");
            read();
            readValueByPattern();
        }
        return 0;
    }
}
