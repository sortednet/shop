package net.sorted.core.boot;

import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Boot {

    private static final Logger log = LoggerFactory.getLogger(Boot.class);

    private final Object lock = new Object();

    /*
     * Configuration
     */
    private List<Startable> services;


    /*
     * Runtime
     */
    private ListIterator<Startable> currentServiceIterator;

    public void setServices(List<Startable> services) {
        this.services = services;
    }

    public void startServices() {
        log.info("*** STARTING ALL SERVICES ***");
        synchronized (lock) {
            for (currentServiceIterator = services.listIterator(); currentServiceIterator.hasNext();) {
                Startable service = currentServiceIterator.next();
                String formatedServiceName = formatServiceName(service.getClass());
                try {
                    log.info(formatedServiceName + "Starting");
                    service.start();
                    log.info(formatedServiceName + "Started");
                } catch (Throwable e) {
                    log.info(formatedServiceName + "Unable to start", e);
                    stopServices();
                    throw new RuntimeException("Unable to start service " + service.getClass().getName(), e);
                }
            }
        }
        log.info("*************************************");
        log.info("*** ALL SERVICES HAS BEEN STARTED ***");
        log.info("*************************************");

    }

    public void stopServices() {
        log.info("*** STOPPING ALL SERVICES ***");
        synchronized (lock) {
            for (; currentServiceIterator.hasPrevious();) {
                Startable service = currentServiceIterator.previous();
                String formatedServiceName = formatServiceName(service.getClass());
                try {
                    log.info(formatedServiceName + "Stopping");
                    service.stop();
                    log.info(formatedServiceName + "Stopped");
                } catch (Throwable e) {
                    log.info(formatedServiceName + "Unable to stop", e);
                }
            }
        }
        log.info("*** ALL SERVICES HAS BEEN STOPPED ***");
    }

    private String formatServiceName(Class<? extends Startable> service) {
        return "Service[" + service.getSimpleName() + "] ";
    }


}
