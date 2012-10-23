package net.sorted.shop.db.carbonfive;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.sorted.shop.db.migrations.DbmsOutputLogger;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import com.carbonfive.db.jdbc.DatabaseType;
import com.carbonfive.db.migration.AbstractMigration;
import com.carbonfive.db.migration.MigrationException;

public class PlsqlScriptMigration extends AbstractMigration {

    private final Resource script;
    private final DbmsOutputLogger dbmsOutputLogger;

    public PlsqlScriptMigration(String version, Resource script, DbmsOutputLogger dbmsOutputLogger) {
        super(version);
        this.script = script;
        this.dbmsOutputLogger = dbmsOutputLogger;
    }
    
    @Override
    public void migrate(DatabaseType dbType, Connection connection) {
        String plsqlBlock;
        InputStream inputStream = null;
        try {
            inputStream = script.getInputStream();
            plsqlBlock = IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new MigrationException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        
        Statement plsqlStatement = null;
        try {
            dbmsOutputLogger.enableDbmsOutputLogging(connection);
            plsqlStatement = connection.createStatement();
            if (log.isDebugEnabled()) {
                log.debug("Executing PL/SQL block\n" + plsqlBlock);
            }
            plsqlStatement.executeUpdate(plsqlBlock);
            dbmsOutputLogger.printDbmsOutput(connection);
            dbmsOutputLogger.disableDbmsOutputLogging(connection);
            
            Assert.isTrue(!connection.isClosed(), "JDBC Connection should not be closed");
        } catch (SQLException e) {
            throw new MigrationException(e);
        } finally {
            closeSqlStatement(plsqlStatement);
        }
    }
    
    private void closeSqlStatement(Statement plsqlStatement) {
        try {
            if(plsqlStatement != null) {
                plsqlStatement.close();
            }
        } catch (SQLException e) {
            throw new MigrationException(e);
        }
    }
}
