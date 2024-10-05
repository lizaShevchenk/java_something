package tasks.streamApi;

import java.util.List;

import static java.util.Arrays.asList;

public class Task2 {
    public static void main(String[] args) {
        /*TODO Задача №2*/
        //Отфильтровать коллекцию так, что бы только получить елементы длина которых меньше 4 символов
        //Input Parameters
         List<String> collection = asList("My", "name", "is", "John", "Doe");
        //Expected result
        // List<String> expected = asList("My", "is", "Doe");

        List<String> expected = collection.stream().filter(element -> element.length() < 4).toList();
        System.out.println(expected);
    }
}
