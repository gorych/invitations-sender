package by.gsu.dao;

import by.gsu.model.Event;

import java.util.List;

public interface EventDao extends CrudDao<Event> {

    void delete(List<Event> events);

}
