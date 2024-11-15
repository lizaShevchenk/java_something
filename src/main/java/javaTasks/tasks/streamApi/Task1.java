package javaTasks.tasks.streamApi;

import java.util.List;

import static java.util.Arrays.asList;

public class Task1 {
    public static void main(String[] args) {
        /*TODO Задача №1*/
        //Конвертировать елементы коллекции в аппер кейс
        //Input Parameters
         List<String> collection = asList("My", "name", "is", "John", "Doe");
        //Expected result
        // List<String> expected = asList("MY", "NAME", "IS", "JOHN", "DOE");

        List<String> collection1 = collection.stream().map(String::toUpperCase).toList();
        System.out.println(collection1);
    }
}
