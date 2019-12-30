package by.gsu.dao;

import by.gsu.model.Person;

import java.util.List;

public interface PersonDao extends CrudDao<Person> {

    void delete(List<Person> people);

}
