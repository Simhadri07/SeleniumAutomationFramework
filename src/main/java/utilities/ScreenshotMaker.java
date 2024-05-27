package utilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotMaker {

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
             FileUtils.copyFile(screenshotFile, new File("target/screenshots/" + screenshotName + ".png"));
            System.out.println("Screenshot captured: " + screenshotName);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public static void clearScreenshots() {
        try {
            File directory = new File("target/screenshots");
            if (directory.exists()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            file.delete();
                            System.out.println("Deleted file: " + file.getName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to clear screenshots: " + e.getMessage());
        }
    }
}
