package net.sorted.config;

import java.io.IOException;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

public class PropertyConfigurer extends PropertyPlaceholderConfigurer {

    private static final Logger log = LoggerFactory.getLogger(PropertyConfigurer.class);
    private static final String HOSTNAME_PATTERN = "%HOSTNAME%";

    public PropertyConfigurer() {
        setLocalOverride(true);
        setIgnoreResourceNotFound(false);
        setIgnoreUnresolvablePlaceholders(false);
        setSystemPropertiesMode(SYSTEM_PROPERTIES_MODE_OVERRIDE);
    }

    @Override
    public void setLocation(Resource location) {
        location = augmentLocation(location);
        super.setLocation(location);
    }

    @Override
    public void setLocations(Resource[] locations) {
        for (int i = 0; i < locations.length; i++) {
            locations[i] = augmentLocation(locations[i]);
        }
        super.setLocations(locations);
    }

    private Resource augmentLocation(Resource location) {
        try {
            if (location.getFilename().contains(HOSTNAME_PATTERN)) {
                String hostname = InetAddress.getLocalHost().getHostName().toLowerCase();
                String augmentedFilename = location.getFilename().replaceAll(HOSTNAME_PATTERN, hostname);
                log.info("Replaced " + location.getFilename() + " with " + augmentedFilename);
                location = location.createRelative(augmentedFilename);
            }
            return location;
        } catch (IOException e) {
            throw new RuntimeException("Unhandled exception trying to load property file", e);
        }
    }
}