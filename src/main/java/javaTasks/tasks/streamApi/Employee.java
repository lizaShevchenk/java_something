package javaTasks.tasks.streamApi;

import java.util.Objects;

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

    @Override
    public String toString() {
        return String.format("Employee{name=%s, age=%s, nationality=%s}", name, age, nationality);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age
                && Objects.equals(name, employee.name)
                && Objects.equals(nationality, employee.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, nationality);
    }
}