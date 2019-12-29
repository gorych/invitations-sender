package by.gsu.service;

import by.gsu.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();

    void add(Person person);

}
