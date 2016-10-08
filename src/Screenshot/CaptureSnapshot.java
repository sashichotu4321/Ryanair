package Screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Sashi on 08-10-2016.
 */
public class CaptureSnapshot {

    public static String createScreenshot(WebDriver driver, String reportLocation) {

        UUID uuid = UUID.randomUUID();
        // generate screenshot as a file object
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            // copy file object to designated location
            FileUtils.copyFile(scrFile, new File(reportLocation + uuid + ".png"));
        } catch (IOException e) {
            System.out.println("Error while generating screenshot:\n" + e.toString());
        }
        return reportLocation + uuid + ".png";
    }
}
