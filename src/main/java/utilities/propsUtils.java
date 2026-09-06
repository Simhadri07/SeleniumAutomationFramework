package utilities;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class propsUtils {
        private static final Properties properties = loadProperties();

        private static Properties loadProperties() {
            Properties loaded = new Properties();
            try (InputStream inputStream = propsUtils.class.getClassLoader()
                    .getResourceAsStream("credentials.properties")) {
                if (inputStream != null) {
                    loaded.load(inputStream);
                }
            } catch (IOException e) {
                throw new IllegalStateException("Unable to load credentials.properties", e);
            }
            return loaded;
        }

        private static String get(String property, String environmentVariable) {
            String value = System.getProperty(property);
            if (value == null || value.trim().isEmpty()) {
                value = System.getenv(environmentVariable);
            }
            if (value == null || value.trim().isEmpty()) {
                value = properties.getProperty(property);
            }
            if (value == null || value.trim().isEmpty()) {
                throw new IllegalStateException(
                        "Missing credential. Set -D" + property + " or " + environmentVariable);
            }
            return value;
        }

        public static String getUsername() {
            return get("username", "TEST_USERNAME");
        }

        public static String getPassword() {
            return get("password", "TEST_PASSWORD");
        }
    }
