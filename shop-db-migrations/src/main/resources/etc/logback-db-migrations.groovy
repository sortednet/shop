import ch.qos.logback.classic.encoder.PatternLayoutEncoder 
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.INFO

appender("FILE", FileAppender) {
  file = System.getProperty("user.home")+"/log/flowfx-db-migrations.log"
  append = true
  encoder(PatternLayoutEncoder) {
    pattern = "%d %5p %m%n"
  }
}

root(INFO, ["FILE"])