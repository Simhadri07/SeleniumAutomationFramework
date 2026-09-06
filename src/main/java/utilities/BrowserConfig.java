package utilities;

public final class BrowserConfig {
    private BrowserConfig() {
    }

    public static enumFactory.EBrowserName getBrowser() {
        String browser = firstNonBlank(
                System.getProperty("browser"),
                System.getenv("BROWSER"),
                "chrome");
        return enumFactory.EBrowserName.from(browser);
    }

    public static boolean isHeadless() {
        String configured = firstNonBlank(
                System.getProperty("headless"),
                System.getenv("HEADLESS"),
                isJenkins() ? "true" : "false");
        return Boolean.parseBoolean(configured);
    }

    public static boolean isJenkins() {
        return System.getenv("JENKINS_URL") != null
                || System.getenv("BUILD_NUMBER") != null;
    }

    public static String getBaseUrl() {
        return firstNonBlank(
                System.getProperty("baseUrl"),
                System.getenv("BASE_URL"),
                JSONUtils.getValueFromJson("src/main/resources/URLStudio.json", "demoURL"));
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        }
        return "";
    }
}
