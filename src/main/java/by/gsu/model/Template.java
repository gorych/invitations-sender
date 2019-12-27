package by.gsu.model;

import lombok.Data;
import org.jooq.Record;

import static by.gsu.domain.Tables.TEMPLATE;

@Data
public class Template {

    private int id;
    private String name;
    private byte[] value;

    public Template(Record record) {
        this.id = record.get(TEMPLATE.ID);
        this.name = record.get(TEMPLATE.NAME);
        this.value = record.get(TEMPLATE.VALUE);
    }

}