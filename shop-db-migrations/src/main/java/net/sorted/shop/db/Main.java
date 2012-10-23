package net.sorted.shop.db;

import net.sorted.shop.db.migrations.DbMigrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public final class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        try {
            BeanFactory bf = new ClassPathXmlApplicationContext("etc/spring/shop-db-migrations-context.xml");
            DbMigrator dbMigrator = (DbMigrator) bf.getBean("dbMigrator");

            for (String goal : args) {
                log.info("Executing goal: " + goal);
                if ("migrate".equalsIgnoreCase(goal)) {
                    dbMigrator.migrateBaseDb();
                } else if ("reset".equalsIgnoreCase(goal)) {
                    dbMigrator.resetBaseDb();
                } else if ("migrateAll".equalsIgnoreCase(goal)) {
                    dbMigrator.migrateAll();
                } else if ("resetAll".equalsIgnoreCase(goal)) {
                    dbMigrator.resetAll();
                } else {
                    throw new RuntimeException("Unknown db migration goal: " + goal);
                }
            }
        } catch (Throwable e) {
            log.info("Unable to migrate database", e);
            System.exit(1);
        }
    }
}