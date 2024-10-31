package tasks.streamApi;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Task3 {
    public static void main(String[] args) {
        /*TODO Задача №3*/
        //Обьединить два списка в один!
        //Input Parameters
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        //Expected result
        // List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");

        List<String> expected = new ArrayList<>();

        collection.forEach(expected::addAll);
        System.out.println(expected);
    }
}
