/*
 * This file is generated by jOOQ.
 */
package by.gsu.domain;


import by.gsu.domain.tables.Event;
import by.gsu.domain.tables.SqliteSequence;
import by.gsu.domain.tables.Template;
import by.gsu.domain.tables.User;
import by.gsu.domain.tables.UserEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = -747414141;

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>event</code>.
     */
    public final Event EVENT = by.gsu.domain.tables.Event.EVENT;

    /**
     * The table <code>sqlite_sequence</code>.
     */
    public final SqliteSequence SQLITE_SEQUENCE = by.gsu.domain.tables.SqliteSequence.SQLITE_SEQUENCE;

    /**
     * The table <code>template</code>.
     */
    public final Template TEMPLATE = by.gsu.domain.tables.Template.TEMPLATE;

    /**
     * The table <code>user</code>.
     */
    public final User USER = by.gsu.domain.tables.User.USER;

    /**
     * The table <code>user_event</code>.
     */
    public final UserEvent USER_EVENT = by.gsu.domain.tables.UserEvent.USER_EVENT;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Event.EVENT,
            SqliteSequence.SQLITE_SEQUENCE,
            Template.TEMPLATE,
            User.USER,
            UserEvent.USER_EVENT);
    }
}
