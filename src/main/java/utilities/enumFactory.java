package utilities;

public class enumFactory {

    public enum EBrowserName {
        CHROME,
        FIREFOX,
        EDGE;

        public static EBrowserName from(String browser) {
            try {
                return valueOf(browser.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "Unsupported browser '" + browser + "'. Supported browsers: chrome, edge, firefox", e);
            }
        }
    }

    public enum EWebsiteName {
        PolicyCenter,
        ClaimCenter,
        BillingCenter,
        demoURL,
        quickRefURL,
    }
}
