package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public final class ExtentReportManager {
    private static final String REPORT_PATH = "target/extent-reports/ExtentReport.html";
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();

    private ExtentReportManager() {
    }

    public static synchronized void startReport() {
        if (extentReports != null) {
            return;
        }
        File reportFile = new File(REPORT_PATH);
        File parent = reportFile.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Unable to create report directory: " + parent.getAbsolutePath());
        }

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportFile);
        reporter.config().setDocumentTitle("Selenium Automation Framework");
        reporter.config().setReportName("Test Execution Report");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Browser", BrowserConfig.getBrowser().name());
        extentReports.setSystemInfo("Headless", String.valueOf(BrowserConfig.isHeadless()));
    }

    public static synchronized ExtentTest createTest(String name, String description) {
        if (extentReports == null) {
            startReport();
        }
        ExtentTest test = extentReports.createTest(name, description);
        CURRENT_TEST.set(test);
        return test;
    }

    public static ExtentTest getCurrentTest() {
        return CURRENT_TEST.get();
    }

    public static void clearCurrentTest() {
        CURRENT_TEST.remove();
    }

    public static synchronized void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
            extentReports = null;
        }
        clearCurrentTest();
    }
}
