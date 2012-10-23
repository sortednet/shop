package net.sorted.shop.db.migrations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carbonfive.db.migration.DataSourceMigrationManager;

/**
 * NB! The order in which databases are migrated matters!
 */
public class DbMigratorImpl implements DbMigrator {

    private static final Logger log = LoggerFactory.getLogger(DbMigratorImpl.class);

    private final DataSourceMigrationManager baseDbMigrationManager;
    private final DataSourceMigrationManager appMigrationManager;
    private final DataSourceMigrationManager baseDbDropManager;
    private final DataSourceMigrationManager appDropManager;


    public DbMigratorImpl(DataSourceMigrationManager baseDbMigrationManager,
            DataSourceMigrationManager appMigrationManager,
            DataSourceMigrationManager baseDbDropManager,
            DataSourceMigrationManager appDropManager) {
        this.baseDbMigrationManager = baseDbMigrationManager;
        this.appMigrationManager = appMigrationManager;

        this.baseDbDropManager = baseDbDropManager;
        this.appDropManager = appDropManager;
    }

    @Override
    public void migrateBaseDb() {
        log.info("Migrating main schema");
        baseDbMigrationManager.migrate();
        log.info("Migrating app schema");
        appMigrationManager.migrate();
    }

    @Override
    public void resetBaseDb() {
        log.info("Dropping APP schema");
        appDropManager.migrate();
        log.info("Dropping main schema");
        baseDbDropManager.migrate();

        migrateBaseDb();
    }

    @Override
    public void migrateAll() {
        /*
         * NB! The order in which databases are migrated matters!
         */
        migrateBaseDb();
    }

    @Override
    public void resetAll() {
        /*
         * NB! The order in which databases are migrated matters!
         */

        resetBaseDb();
        migrateAll();
    }
}