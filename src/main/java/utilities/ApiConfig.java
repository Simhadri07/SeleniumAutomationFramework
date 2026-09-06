package utilities;

public final class ApiConfig {
    private ApiConfig() {
    }

    public static String getBaseUrl() {
        String configured = System.getProperty("apiBaseUrl");
        if (configured == null || configured.trim().isEmpty()) {
            configured = System.getenv("API_BASE_URL");
        }
        if (configured == null || configured.trim().isEmpty()) {
            configured = "https://jsonplaceholder.typicode.com";
        }
        return configured.trim();
    }
}
