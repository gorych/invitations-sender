package by.gsu.service;

import by.gsu.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getAll();

    void add(Event event);

}
