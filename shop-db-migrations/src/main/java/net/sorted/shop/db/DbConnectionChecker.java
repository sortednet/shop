package net.sorted.shop.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DbConnectionChecker {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext("etc/spring/shop-db-migrations-context.xml");

        int failureCount = 0;
        Map<String, DataSource> dataSources = bf.getBeansOfType(DataSource.class);

        for (Entry<String, DataSource> dsByName : dataSources.entrySet()) {
            String name = dsByName.getKey();
            DataSource ds = dsByName.getValue();
            Connection c = null;
            try {
                c = ds.getConnection();
                System.out.println(name + " - Data source connection established");
            } catch (SQLException e) {
                System.out.println(name + " - Cannot connect to data source: " + e.getMessage());
                failureCount++;
            } finally {
                if (c != null) {
                    try {
                        c.close();
                    } catch (SQLException e) {

                    }
                }
            }
        }
        System.out.println(failureCount + " connections failed");
        System.exit(failureCount);
    }

}
