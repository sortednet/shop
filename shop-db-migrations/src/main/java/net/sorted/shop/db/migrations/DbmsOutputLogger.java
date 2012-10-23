package net.sorted.shop.db.migrations;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbmsOutputLogger {

    void enableDbmsOutputLogging(Connection connection) throws SQLException;
    void printDbmsOutput(Connection connectio) throws SQLException;
    void disableDbmsOutputLogging(Connection connection) throws SQLException;
}
