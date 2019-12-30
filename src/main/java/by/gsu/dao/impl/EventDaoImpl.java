package by.gsu.dao.impl;

import by.gsu.dao.EventDao;
import by.gsu.dao.util.DbManager;
import by.gsu.domain.tables.records.EventRecord;
import by.gsu.model.Event;

import java.util.List;
import java.util.Optional;

import static by.gsu.domain.tables.Event.EVENT;
import static by.gsu.util.DateTimeUtil.YYYY_MM_DD_FORMATTER;

public class EventDaoImpl implements EventDao {

    @Override
    public Optional<Event> findById(int id) {
        return Optional.of(new Event(DbManager.getDslContext().fetchOne(EVENT, EVENT.ID.eq(id))));
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
        EventRecord eventRecord = DbManager.getDslContext().newRecord(EVENT);
        saveOrUpdate(event, eventRecord);
    }

    @Override
    public void update(Event event) {
        EventRecord eventRecord = DbManager.getDslContext().fetchOne(EVENT, EVENT.ID.eq(event.getId()));
        saveOrUpdate(event, eventRecord);
    }

    @Override
    public void delete(Event person) {
        DbManager.getDslContext()
                .delete(EVENT)
                .where(EVENT.ID.eq(person.getId()))
                .execute();
    }

    @Override
    public void delete(List<Event> events) {
        events.forEach(this::delete);
    }

    private Event saveOrUpdate(Event event, EventRecord eventRecord) {
        eventRecord.setName(event.getName());
        eventRecord.setDescription(event.getDescription());
        eventRecord.setDate(event.getDate().format(YYYY_MM_DD_FORMATTER));
        eventRecord.store();
        return new Event(eventRecord);
    }

}