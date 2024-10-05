package tasks.streamApi;

import java.util.List;

import static java.util.Arrays.asList;

public class Task5 {
    public static void main(String[] args) {
        /*TODO Задача№5*/
        //Суммировать все елементы коллекции
        //Input Parameters
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        //Expected result
        //15

        System.out.println(numbers.stream().mapToInt(Integer::intValue).sum());
    }
}
