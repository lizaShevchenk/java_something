package javaTasks.tasks.streamApi;

import java.util.List;

import static java.util.Arrays.asList;

public class Task6 {
    public static void main(String[] args) {
        /*TODO Задача №6*/
        //Получить имена всех людей, которым меньше 25 лет
        //Input parameters
        Employee sara = new Employee("Sara", 19);
        Employee viktor = new Employee("Viktor", 40);
        Employee eva = new Employee("Eva", 42);
        Employee anna = new Employee("Anna", 20);
        List<Employee> collection = asList(sara, eva, viktor, anna);
        //Expected result
        //[Sara, Anna]

        List<String> names = collection.stream()
                .filter(e -> e.getAge() < 25)
                .map(Employee::getName)
                .toList();
        System.out.println(names);
    }
}
