package tasks.library.controller;

import tasks.library.models.Book;
import tasks.library.models.Journal;
import tasks.library.storage.InMemoryAuthorStorage;
import tasks.library.storage.Storage;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller {

    Scanner scanner = new Scanner(System.in);
    Storage storage = new Storage();
    InMemoryAuthorStorage authorStorage;

    public Controller(InMemoryAuthorStorage authorStorage) {
        this.authorStorage = authorStorage;
    }

    public Controller(InMemoryAuthorStorage authorStorage, Storage storage) {
        this.authorStorage = authorStorage;
        this.storage = storage;
    }

    public void start() {
        do {
            System.out.printf("\nВозможные команды - %s. Введите команду: ", Actions.getAllActions());
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase(Actions.HELP.getActionName())) {
                help();
            }
            if (command.equalsIgnoreCase(Actions.EXIT.getActionName())) {
                exit();
            }
            if (command.equalsIgnoreCase(Actions.ADD_BOOK.getActionName())) {
                addBook();
            }
            if (command.equalsIgnoreCase(Actions.ADD_JOURNAL.getActionName())) {
                addJournal();
            }
            if (command.equalsIgnoreCase(Actions.SHOW_STORAGE.getActionName())) {
                System.out.println(storage.toString());
            }
            if (command.equalsIgnoreCase(Actions.DELETE.getActionName())) {
                deletePublicationFromStorage();
            }
        } while (true);
    }

    private static void help() {
        System.out.printf("Доступны команды: %s", Actions.getAllActions());
    }

    private static void exit() {
        System.exit(0);
    }

    private void addBook() {
        Book book = getBook();
        storage.addPublication(book);
        System.out.println("Добавлена книга " + book.print());
    }

    private void addJournal() {
        Journal journal = getJournal();
        storage.addPublication(journal);
        System.out.println("Добавлен журнал " + journal.print());
    }

    private int enterIntValue() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели не числовое значение. Попробуйте снова:");
            scanner.nextLine();
            enterIntValue();
        }
        return 0;
    }

    private int enterValueByPattern() {
        try {
            return Integer.parseInt(scanner.next(Pattern.compile("(19\\d{2}|20[01]\\d|202[0-4])")));
        } catch (InputMismatchException e) {
            System.out.println("Введите корректный год публикации: ");
            scanner.nextLine();
            enterValueByPattern();
        }
        return 0;
    }

    private void deletePublicationFromStorage() {
        System.out.println("Storage " + storage.toString());
        System.out.println("Удалить публикацию по индексу? Нажмите 0. Удалить публикацию введя ее? Нажмите 1.");
        int methodToDelete = enterIntValue();
        if (methodToDelete == 0) {
            System.out.println("Введите индекс публикациии которую хотите удалить: ");
            int index = enterIntValue();
            storage.deletePublicationByIndex(index);
        } else if (methodToDelete == 1) {
            scanner.nextLine();
            Book book = getBook();
            storage.deletePublication(book);
        } else {
            System.out.println("Вы не выбрали корректный метод. Попробуйте снова.");
            scanner.nextLine();
            deletePublicationFromStorage();
        }
        System.out.println("Storage " + storage.toString());
    }

    private Book getBook() {
        String name;
        int countPages = 0;
        long authorId = 0;

        System.out.println("Добавление книги. \nВеедите название книги: ");
        name = scanner.nextLine();
        System.out.print("Введите количество страниц (число) : ");
        countPages = enterIntValue();
        System.out.print("Введите автора книги: ");
        authorId = authorStorage.selectAnAuthor(scanner).getId();

        return new Book(name, countPages, authorId);
    }

    private Journal getJournal() {
        String name;
        int countPages = 0;
        int number = 0;
        int publicationYear = 0;

        System.out.println("Добавление журнала. \nВеедите название журнала: ");
        name = scanner.nextLine();
        System.out.println("Введите количество страниц (число) : ");
        countPages = enterIntValue();
        System.out.println("Введите номер журнала (число) : ");
        number = enterIntValue();
        System.out.println("Введите год публикации : ");
        publicationYear = enterValueByPattern();

        return new Journal(name, countPages, number, publicationYear);
    }
}
