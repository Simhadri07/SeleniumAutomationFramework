package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogManagerUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogManagerUtils.class);

    private LogManagerUtils() {
    }

    public static void configure(Boolean printInConsole) {
        LOGGER.info("Logging initialized; console output is configured by logback.xml");
    }

    public static void clearLogs() {
        LOGGER.debug("Log files are managed by logback rolling appenders");
    }

    public static void logMsg(String message) {
        LOGGER.info(message);
    }

    public static void logError(String message) {
        LOGGER.error(message);
    }

    public static void logError(String message, Throwable error) {
        LOGGER.error(message, error);
    }

    public static void logWarn(String message) {
        LOGGER.warn(message);
    }
}
