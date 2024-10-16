package tasks.streamApi;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Task9 {
    public static void main(String[] args) {
        /*TODO Задача №9*/
        //Вернуть имена людей разделив их запятой
        //Input parameters
         Employee sara = new Employee("Sara", 4);
                Employee viktor = new Employee("Viktor", 40);
                Employee eva = new Employee("Eva", 42);
                List<Employee> collection = asList(sara, viktor, eva);
        //Expected result
        //Names: Sara, Viktor, Eva.
//
        System.out.println(collection.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", ")));


//        last task need more details
        //Как вы думаете сколько операций будет сделано в данном примере? 4???
        //Employee employee = Stream.of(empIds)
        //      .map(employeeRepository::findById)
        //      .filter(e -> e != null)
        //      .filter(e -> e.getSalary() > 100000)
        //      .findFirst()
        //      .orElse(null);
    }
}
