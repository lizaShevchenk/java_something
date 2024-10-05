package tasks;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Employee {
    String name;
    int age;
    String nationality;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}