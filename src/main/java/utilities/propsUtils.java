package utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class propsUtils {
        private static final String CREDENTIALS_FILE = "./main/resources/credentials.properties";
        private static final Properties properties;

        static {
            properties = new Properties();
            try {
                FileInputStream inputStream = new FileInputStream(CREDENTIALS_FILE);
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String getUsername() {
            return properties.getProperty("username");
        }

        public static String getPassword() {
            return properties.getProperty("password");
        }
    }
