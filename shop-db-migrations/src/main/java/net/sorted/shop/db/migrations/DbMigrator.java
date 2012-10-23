package net.sorted.shop.db.migrations;

public interface DbMigrator {

    void migrateBaseDb();
    void resetBaseDb();

    void migrateAll();
    void resetAll();
}
