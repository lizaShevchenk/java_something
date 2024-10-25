import tasks.library.author.Author;
import tasks.library.controller.Controller;
import tasks.library.Library;
import tasks.library.models.Book;
import tasks.library.models.Journal;
import tasks.library.models.Publication;
import tasks.library.storage.InMemoryAuthorStorage;
import tasks.library.storage.ListStorage;
import tasks.library.storage.Storage;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        InMemoryAuthorStorage inMemoryAuthorStorage = new InMemoryAuthorStorage();
        inMemoryAuthorStorage.addAuthor(new Author(1L, "Leo", "Last", "leo.last@blabla.com"));
        inMemoryAuthorStorage.addAuthor(new Author(2L, "Serhii", "Serhii", "serhii.serhii@blabla.com"));
        inMemoryAuthorStorage.addAuthor(new Author(2L, "Serhii", "Serhii", "serhii.serhii@blabla.com"));
        inMemoryAuthorStorage.addAuthor(new Author(2L, "Sasha", "Y", "sasha.y@blabla.com"));
        inMemoryAuthorStorage.addAuthor(new Author(3L, "Sasha", "Y", "sasha.y@blabla.com"));
        inMemoryAuthorStorage.addAuthor(new Author(4L, "Sasha", "Y1", "sasha.y1@blabla.com"));
        inMemoryAuthorStorage.addAuthor(new Author(5L, "Sasha 1", "Y2", "sasha.y2@blabla.com"));
        inMemoryAuthorStorage.addAuthor(new Author(6L, "Sasha 2", "Y3", "sasha.y3@blabla.com"));
        inMemoryAuthorStorage.addAuthor(new Author(7L, "test", "Test", "test.test@blabla.com"));



        long authorId = 0;

        Publication[] publications = new Publication[4];
        publications[0] = new Journal("journal", 100, 1, 2000);
        publications[1] = new Book("book", 150, 1L);
        publications[2] = new Publication(null, 20);
        publications[3] = null;

//        Storage storage = new Storage(2);
        Storage storage1 = new Storage(4);
//        Arrays.stream(publications).forEach(storage::addPublication);
        new Library().printPublications(publications);

        for (int i = 0; i < 4; i++) {//checking storage increase, should increase 4 times: 4->6->9->13->19
            Arrays.stream(publications).forEach(storage1::addPublication);
        }

        //Task 2
        Storage storage = new Storage(6);
        Arrays.stream(publications).forEach(storage::addPublication);

        System.out.println(storage);
        storage.deletePublication(publications[0]);
        storage.deletePublication(null);
        storage.deletePublication(new Publication(null, 20));
        storage.deletePublication(new Publication(null, 0));
        System.out.println(storage);

        Storage emptyStorage = new Storage();
        emptyStorage.deletePublication(publications[0]);

        emptyStorage.deletePublicationByIndex(0);
        System.out.println("args = " + storage.getPublicationByIndex(0).print());
        storage.deletePublicationByIndex(0);
        System.out.println(storage);

        ListStorage listStorage = new ListStorage();
        listStorage.addPublication(publications[0]);
        listStorage.addPublication(publications[1]);
        listStorage.deletePublication(publications[0]);
        System.out.println(listStorage);

        Controller controller = new Controller(inMemoryAuthorStorage);
        controller.start();

    }

    private void task2() {
        /*
Задача 1.
Давайте попробуем реализовать метод удаления элементов хранилища. Не будем забывать, что Publication это наш класс,
в котором нужно переопределить метод сравнения equals и hashcode для правильного сравнения и поиска элементов.

Задача 2.
Реализуем метод поиска всех элементов и метод печати нашего хранилища, что бы увидеть все элементы, которые в нем находятся.

Задача 3.
Реализуем метод поиска елемента в хранилище по индексу, и метод удаления элемента по индексу. Если элемент не найдет, мы будем возвращать null.

Задача 4.
В библиотеке появились все необходимые базовые методы. Реализуем взаимодействие пользователей с нашей библиотекой с помощью консоли.
Для работы с консолью будем использовать класс Scanner.
1. Создать класс, который будет выполнять роль контроллера для работы с консолью.
2. Создать команду help, которая будет выводить список всех доступных команд.
3. Создать команду exit, для выхода из консоли.

Задача 5.
Добавим команду добавления книги в библиотеку
Добавим команду добавления журнала в библиотеку

Задача 6.
Добавим команду удаления книги из библиотеки.
Добавим команду печати всех доступных книг в библиотеке.

Задача 7.
Перевести приложение на использование списков, сделать отдельный сторедж с списками,
вынести все методы в интерфейс. Интерфест должны наследовать как и Сторедж так и ListStorage

Задача 8. Добавим авторов
1. Автор - должен соблюдать правила уникальности во всем проекте. Для уникальности авторов, в сущность Author нужно добавить пол id (long).
2. Для работы с автором создадим отдельный Storage, назовем его InMemoryAuthor Storage.
   В данном хранилище должны быть методы (поиск автора по id, добавление автора, удаление автора, редактирование автора)
3. Добавим команды по добавлению автора и удалению автора (удалять автора нужно по имейл адрессу, так как имя и фамилия могут повторятся, а имейл уникальный)
4. При создании книги нужно добавить логику по выбору существующего автора, так как список авторов может быть очень большой,
   нужно реализовать пейджинацию (показывать по 10 авторов с возможностью перелестнуть на следующую страницу с 10 авторами и
   возвратом на предыдущую страницу). При создании книги мы не предоставляем возможность создания автора, только выбор из существующих.
   У книг должна хранится теперь связь с автором через идентификатор.
        * */
    }
}
