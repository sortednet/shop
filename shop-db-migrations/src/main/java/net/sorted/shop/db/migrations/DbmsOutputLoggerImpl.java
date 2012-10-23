package net.sorted.shop.db.migrations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class DbmsOutputLoggerImpl implements DbmsOutputLogger {

    private static final Logger log = LoggerFactory.getLogger(DbmsOutputLoggerImpl.class);

    /*
     * Configuration
     */
    private String enableDbmsOutputSql;
    private String fetchDbmsOutputSql;
    private String disableDbmsOutputSql;

    private int dbmsOutputBufferSize;

    @Override
    public void enableDbmsOutputLogging(Connection connection) throws SQLException {
        CallableStatement enableDbmsOutput = connection.prepareCall(enableDbmsOutputSql);
        enableDbmsOutput.setInt(1, dbmsOutputBufferSize);
        enableDbmsOutput.executeUpdate();
    }

    @Override
    public void printDbmsOutput(Connection connection) throws SQLException {
        CallableStatement fetchDbmsOutput = connection.prepareCall(fetchDbmsOutputSql);

        fetchDbmsOutput.registerOutParameter(2, java.sql.Types.INTEGER);
        fetchDbmsOutput.registerOutParameter(3, java.sql.Types.VARCHAR);

        StringBuilder buffer = new StringBuilder();
        for (;;) {
            fetchDbmsOutput.setInt(1, 32000);
            fetchDbmsOutput.executeUpdate();

            buffer.append(fetchDbmsOutput.getString(3));
            if (fetchDbmsOutput.getInt(2) == 1) {
                break;
            }
        }
        log.info("DBMS output: " + buffer.toString());
    }

    @Override
    public void disableDbmsOutputLogging(Connection connection) throws SQLException {
        CallableStatement disableDbmsOutput = connection.prepareCall(disableDbmsOutputSql);
        disableDbmsOutput.executeUpdate();
    }

    @Required
    public void setEnableDbmsOutputSql(String enableDbmsOutputSql) {
        this.enableDbmsOutputSql = enableDbmsOutputSql;
    }

    @Required
    public void setFetchDbmsOutputSql(String fetchDbmsOutputSql) {
        this.fetchDbmsOutputSql = fetchDbmsOutputSql;
    }

    @Required
    public void setDisableDbmsOutputSql(String disableDbmsOutputSql) {
        this.disableDbmsOutputSql = disableDbmsOutputSql;
    }

    @Required
    public void setDbmsOutputBufferSize(int dbmsOutputBufferSize) {
        this.dbmsOutputBufferSize = dbmsOutputBufferSize;
    }
}
