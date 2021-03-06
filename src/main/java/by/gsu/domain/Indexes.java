/*
 * This file is generated by jOOQ.
 */
package by.gsu.domain;


import by.gsu.domain.tables.Event;
import by.gsu.domain.tables.Person;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index EVENTS_ID_UINDEX = Indexes0.EVENTS_ID_UINDEX;
    public static final Index PERSON_ID_UINDEX = Indexes0.PERSON_ID_UINDEX;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index EVENTS_ID_UINDEX = Internal.createIndex("events_id_uindex", Event.EVENT, new OrderField[] { Event.EVENT.ID }, true);
        public static Index PERSON_ID_UINDEX = Internal.createIndex("person_id_uindex", Person.PERSON, new OrderField[] { Person.PERSON.ID }, true);
    }
}
