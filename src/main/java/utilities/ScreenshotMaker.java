package utilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotMaker {

    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            File directory = new File("target/screenshots");
            Files.createDirectories(directory.toPath());
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(directory, screenshotName + ".png");
            FileUtils.copyFile(screenshotFile, destination);
            LogManagerUtils.logMsg("Screenshot captured: " + destination.getPath());
            return destination.getAbsolutePath();
        } catch (IOException | RuntimeException e) {
            LogManagerUtils.logError("Failed to capture screenshot: " + screenshotName, e);
            return null;
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
                            LogManagerUtils.logMsg("Deleted screenshot: " + file.getName());
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            LogManagerUtils.logError("Failed to clear screenshots", e);
        }
    }
}
