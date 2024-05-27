package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LogManagerUtils {
    public static void configure(Boolean printInConsole) {
        try {
            LogManager.getLogManager().reset();
            Logger logger = Logger.getLogger("");
            logger.setLevel(Level.FINE);

            if (printInConsole) {
                // Create console handler
                ConsoleHandler consoleHandler = new ConsoleHandler();
                consoleHandler.setLevel(Level.FINE);
                consoleHandler.setFormatter(new LogFormatter());
                logger.addHandler(consoleHandler);
            }

            // Create file handler
            FileHandler fileHandler = new FileHandler("test.log", true);
            fileHandler.setLevel(Level.FINE);
            fileHandler.setFormatter(new LogFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.err.println("Failed to initialize logging: " + e.getMessage());
        }
    }

    public static void clearLogs() {
        try {
            File logFile = new File("test.log");
            if (logFile.exists()) {
                logFile.delete();
                System.out.println("Cleared previous logs.");
            }
        } catch (Exception e) {
            System.err.println("Failed to clear logs: " + e.getMessage());
        }
    }

    public void logMsg(String message) {
        Logger.getLogger("").config(message);
        System.err.println(message);
    }

    public void logError(String message) {
        Logger.getLogger("").severe(message);
        System.err.println(message);
    }

    public void logWarn(String message) {
        Logger.getLogger("").warning(message);
        System.err.println(message);
    }

    static class LogFormatter extends Formatter {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder();
            builder.append(dateFormat.format(new Date(record.getMillis()))).append(" ");
            builder.append("[").append(record.getLevel()).append("] ");
            builder.append(formatMessage(record)).append("\n");
            return builder.toString();
        }
    }
}
