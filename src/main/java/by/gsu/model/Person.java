package by.gsu.model;

import lombok.Data;
import org.jooq.Record;

import static by.gsu.domain.Tables.PERSON;

@Data
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(Record record) {
        this.id = record.get(PERSON.ID);
        this.firstName = record.get(PERSON.FIRSTNAME);
        this.lastName = record.get(PERSON.LASTNAME);
        this.email = record.get(PERSON.EMAIL);
    }

}