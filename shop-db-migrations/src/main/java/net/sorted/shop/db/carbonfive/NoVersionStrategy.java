package net.sorted.shop.db.carbonfive;

import java.sql.Connection;
import java.util.Date;
import java.util.Set;

import com.carbonfive.db.jdbc.DatabaseType;
import com.carbonfive.db.migration.VersionStrategy;

public class NoVersionStrategy implements VersionStrategy {

    @Override
    public void enableVersioning(DatabaseType dbType, Connection connection) {
    }

    @Override
    public Set<String> appliedMigrations(DatabaseType dbType, Connection connection) {
        return null;
    }

    @Override
    public void recordMigration(DatabaseType dbType, Connection connection, String version, Date startTime, long duration) {
    }
}
