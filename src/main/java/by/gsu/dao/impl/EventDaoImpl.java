package by.gsu.dao.impl;

import by.gsu.dao.EventDao;
import by.gsu.dao.util.DbManager;
import by.gsu.model.Event;

import java.util.List;
import java.util.Optional;

import static by.gsu.domain.tables.Event.EVENT;

public class EventDaoImpl implements EventDao {

    @Override
    public Optional<Event> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Event> findAll() {
        return DbManager.getDslContext()
                .select()
                .from(EVENT)
                .fetch()
                .map(Event::new);
    }

    @Override
    public void save(Event event) {

    }

    @Override
    public void update(Event event) {

    }

    @Override
    public void delete(Event event) {

    }

}