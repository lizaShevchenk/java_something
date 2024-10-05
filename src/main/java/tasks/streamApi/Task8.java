package tasks.streamApi;

import tasks.Employee;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Task8 {
    public static void main(String[] args) {
        /*TODO Задача №8*/
        //Группировать людей по национальности
        //Input parameters
        Employee sara = new Employee("Sara", 4, "Norwegian");
        Employee viktor = new Employee("Viktor", 40, "Serbian");
        Employee eva = new Employee("Eva", 42, "Norwegian");

        List<Employee> employeeList = List.of(sara, viktor, eva);
        Map<String, List<Employee>> map = employeeList.stream().collect(groupingBy(Employee::getNationality));
        System.out.println(map);
    }
}
