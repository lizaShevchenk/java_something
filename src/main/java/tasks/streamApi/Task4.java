package tasks.streamApi;

import tasks.Employee;

import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

public class Task4 {
    public static void main(String[] args) {
        /*TODO Задача №4*/
        //Получить самого старого сотрудника с коллекции
        //Input Parameters
         Employee sara = new Employee("Sara", 45);
         Employee viktor = new Employee("Viktor", 40);
         Employee eva = new Employee("Eva", 42);
        List<Employee> collection = asList(sara, eva, viktor);
        //Expected result
        // Eva is the oldest person

        String nameOfOldestEmployee = collection.stream()
                .max(Comparator.comparingInt(Employee::getAge)).get()
                .getName();
        System.out.println(nameOfOldestEmployee + " is the oldest person");
    }
}
