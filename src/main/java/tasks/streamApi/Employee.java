package tasks.streamApi;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Employee {
    String name;
    int age;
    String nationality;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Employee(String name, int age, String nationality) {
        this(name, age);
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }
}