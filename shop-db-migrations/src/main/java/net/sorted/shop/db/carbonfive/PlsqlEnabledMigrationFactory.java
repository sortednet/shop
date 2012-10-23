package net.sorted.shop.db.carbonfive;

import net.sorted.shop.db.migrations.DbmsOutputLogger;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;

import com.carbonfive.db.migration.Migration;
import com.carbonfive.db.migration.MigrationFactory;

public class PlsqlEnabledMigrationFactory extends MigrationFactory {

    private DbmsOutputLogger dbmsOutputLogger;
    
    @Override
    public Migration create(String version, Resource resource) {
        
        if(resource.getFilename().endsWith(".plsql")) {
            return new PlsqlScriptMigration(version, resource, dbmsOutputLogger);
        } else {
            return super.create(version, resource);
        }
    }

    @Required
    public void setDbmsOutputLogger(DbmsOutputLogger dbmsOutputLogger) {
        this.dbmsOutputLogger = dbmsOutputLogger;
    }
}
