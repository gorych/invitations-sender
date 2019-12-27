package by.gsu.dao.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.nio.channels.NotYetConnectedException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@UtilityClass
public class DbManager {

    private static final String URL = "jdbc:sqlite:src\\main\\resources\\db\\invitations-sender.db";

    private static Connection connection;

    public static DSLContext getDslContext() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                log.error("Error creating connection", e);
                throw new NotYetConnectedException();
            }
        }

        return DSL.using(connection, SQLDialect.SQLITE);
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error closing connection", e);
            }
        }
    }

}
