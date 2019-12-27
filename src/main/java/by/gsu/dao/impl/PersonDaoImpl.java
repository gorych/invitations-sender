package by.gsu.dao.impl;

import by.gsu.dao.PersonDao;
import by.gsu.dao.util.DbManager;
import by.gsu.model.Person;

import java.util.List;
import java.util.Optional;

import static by.gsu.domain.tables.Person.PERSON;

public class PersonDaoImpl implements PersonDao {

    @Override
    public Optional<Person> findById(long id) {
        return Optional.empty();
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

    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {

    }

}