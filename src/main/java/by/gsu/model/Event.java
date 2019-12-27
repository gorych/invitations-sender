package by.gsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.Record;

import static by.gsu.domain.Tables.EVENT;

@Data
@NoArgsConstructor
public class Event {

    private int id;
    private String name;
    private String description;

    public Event(Record record) {
        this.id = record.get(EVENT.ID);
        this.name = record.get(EVENT.NAME);
        this.description = record.get(EVENT.DESCRIPTION);
    }

}