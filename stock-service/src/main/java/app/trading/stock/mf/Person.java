package app.trading.stock.mf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    private String fullName;
    private String lastName;
    private String email;

    public Person() {
    }

    public Person(String fullName, String lastName, String email) {
        this.fullName = fullName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
