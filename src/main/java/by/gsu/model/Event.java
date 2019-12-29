package by.gsu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.Record;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static by.gsu.domain.Tables.EVENT;
import static by.gsu.util.DateTimeUtil.DD_MM_YYYY_FORMATTER;
import static by.gsu.util.DateTimeUtil.YYYY_MM_DD_FORMATTER;

@Data
@NoArgsConstructor
public class Event {

    private int id;
    private String name;
    private String description;
    private LocalDate date;

    public Event(Record record) {
        this.id = record.get(EVENT.ID);
        this.name = record.get(EVENT.NAME);
        this.description = record.get(EVENT.DESCRIPTION);
        this.description = record.get(EVENT.DESCRIPTION);
        this.date = LocalDate.parse(record.get(EVENT.DATE, String.class), YYYY_MM_DD_FORMATTER);
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public String getMonth() {
        return date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));
    }

    public static class Builder {

        private String name;
        private String description;
        private String date;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Event build() {
            Event event = new Event();
            event.setName(name);
            event.setDescription(description);
            event.setDate(LocalDate.parse(date, DD_MM_YYYY_FORMATTER));
            return event;
        }

    }

}