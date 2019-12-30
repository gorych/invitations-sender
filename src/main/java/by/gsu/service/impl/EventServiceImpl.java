package by.gsu.service.impl;

import by.gsu.dao.EventDao;
import by.gsu.model.Event;
import by.gsu.service.EventService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventDao eventDao;

    @Override
    public List<Event> getAll() {
        return eventDao.findAll();
    }

    @Override
    public void add(Event event) {
        eventDao.save(event);
    }

    @Override
    public void delete(Event event) {
        eventDao.delete(event);
    }

}
