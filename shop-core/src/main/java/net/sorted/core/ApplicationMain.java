package net.sorted.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ApplicationMain {
    private static final Logger log = LoggerFactory.getLogger(ApplicationMain.class);
    
    public static void startApplication(String contextPath) {
        try {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(contextPath);
            ctx.registerShutdownHook();
        } catch (Throwable t) {
            log.info("Error starting application", t);
            System.exit(1);
        }
    }
}
