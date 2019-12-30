package by.gsu.dao.impl;

import by.gsu.dao.PersonDao;
import by.gsu.dao.util.DbManager;
import by.gsu.domain.tables.records.PersonRecord;
import by.gsu.model.Person;

import java.util.List;
import java.util.Optional;

import static by.gsu.domain.tables.Person.PERSON;

public class PersonDaoImpl implements PersonDao {

    @Override
    public Optional<Person> findById(int id) {
        return Optional.of(new Person(DbManager.getDslContext().fetchOne(PERSON, PERSON.ID.eq(id))));
    }

    @Override
    public List<Person> findAll() {
        return DbManager.getDslContext()
                .select()
                .from(PERSON)
                .fetch()
                .map(Person::new);
    }

    @Override
    public void save(Person person) {
        PersonRecord personRecord = DbManager.getDslContext().newRecord(PERSON);
        saveOrUpdate(person, personRecord);
    }

    @Override
    public void update(Person person) {
        PersonRecord personRecord = DbManager.getDslContext().fetchOne(PERSON, PERSON.ID.eq(person.getId()));
        saveOrUpdate(person, personRecord);
    }

    @Override
    public void delete(Person person) {
        DbManager.getDslContext()
                .delete(PERSON)
                .where(PERSON.ID.eq(person.getId()))
                .execute();
    }

    private Person saveOrUpdate(Person person, PersonRecord personRecord) {
        personRecord.setFirstname(person.getFirstName());
        personRecord.setLastname(person.getLastName());
        personRecord.setMiddlename(person.getMiddleName());
        personRecord.setEmail(person.getEmail());
        personRecord.store();
        return new Person(personRecord);
    }

    @Override
    public void delete(List<Person> people) {
        people.forEach(this::delete);
    }
}