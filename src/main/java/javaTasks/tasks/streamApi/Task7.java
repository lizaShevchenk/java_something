package javaTasks.tasks.streamApi;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class Task7 {
    public static void main(String[] args) {
        /*TODO Задача №7*/
        //Get people statistics: average age, count, maximum age, minimum age and sum og all ages.
        //Получить статистику по людям: средний возраст, подсчитать колличество людей, максимальный возраст, минимальный возраст, суммировать все возраста
        //Input parameters
        Employee sara = new Employee("Sara", 44);
        Employee viktor = new Employee("Viktor", 40);
        Employee eva = new Employee("Eva", 42);
        List<Employee> collection = asList(sara, eva, viktor);

        IntStream summaryStatistics = IntStream.of(
                (int) collection.stream().mapToInt(Employee::getAge).average().getAsDouble(),
                collection.size(),
                collection.stream().max(Comparator.comparingInt(Employee::getAge)).get().getAge(),
                collection.stream().min(Comparator.comparingInt(Employee::getAge)).get().getAge(),
                collection.stream().mapToInt(Employee::getAge).sum());

        summaryStatistics.forEach(System.out::println);
        //        ?? ugly

    }
}
