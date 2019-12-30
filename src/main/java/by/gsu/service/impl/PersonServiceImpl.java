package by.gsu.service.impl;

import by.gsu.dao.PersonDao;
import by.gsu.model.Person;
import by.gsu.service.PersonService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    @Override
    public List<Person> getAll() {
        return personDao.findAll();
    }

    @Override
    public void add(Person person) {
        personDao.save(person);
    }

    @Override
    public void delete(Person person) {
        personDao.delete(person);
    }

}
