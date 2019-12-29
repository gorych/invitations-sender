package by.gsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.Record;

import static by.gsu.domain.Tables.PERSON;

@Data
@NoArgsConstructor
public class Person {

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

    public Person(Record record) {
        this.id = record.get(PERSON.ID);
        this.firstName = record.get(PERSON.FIRSTNAME);
        this.middleName = record.get(PERSON.MIDDLENAME);
        this.lastName = record.get(PERSON.LASTNAME);
        this.email = record.get(PERSON.EMAIL);
    }

    public static class Builder {

        private String firstName;
        private String middleName;
        private String lastName;
        private String email;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.setFirstName(firstName);
            person.setMiddleName(middleName);
            person.setLastName(lastName);
            person.setEmail(email);

            return person;
        }

    }

}