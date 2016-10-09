package ryanair;


import Screenshot.CaptureSnapshot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pageObjects.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by Sashi on 05-10-2016.
 */
public class DeclinedPayment {
    //initialize report file variable
    public static String extentReportFile = System.getProperty("user.dir") + "\\report\\RyanairTestResult.html";
    //Instantiate ExtentReport class
    public static ExtentReports extent = new ExtentReports(extentReportFile, false);
    //Start Test for report
    public static ExtentTest extentTest = extent.startTest("Ryanair Test", "Verify Declined Payment Message");
    //Initialize webdriver for Firefox browser
    static WebDriver driver = new FirefoxDriver();

    public static void main(String[] args) throws Exception {
        DeclinedPayment dec = new DeclinedPayment();
        try {
            dec.setup();
            dec.declineMessageCase();
            dec.end();
        } catch (Exception ex) {
            extentTest.log(LogStatus.ERROR, "Exception Occured. Please find the exception error below - " + ex);
            String extentReportImageError = CaptureSnapshot.createScreenshot(driver, System.getProperty("user.dir") + "\\report\\");
            extentTest.log(LogStatus.ERROR, "Exception Error Snapshot : " + extentTest.addScreenCapture(extentReportImageError));
            dec.end();
        }

    }

    @BeforeTest
    public void setup() {
        //Launch Ryanair website
        driver.get("https://www.ryanair.com/ie/en/");
        //Maximize browser
        driver.manage().window().maximize();
        //page sync
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("core-input")));
        extentTest.log(LogStatus.INFO, "Ryanair home page launched.");
    }

    @Test
    public void declineMessageCase() {

        //calling RyanairHomePage class to fill details
        RyanairHomePage flight = new RyanairHomePage(driver);
        long TotalNumPassengers = 0;
        try {
            TotalNumPassengers = flight.SearchFlight();
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.log(LogStatus.INFO, "Entered required details and clicked on search flights.");

        //check for offer dialog box
        OfferDialogBox dialog = new OfferDialogBox(driver);
        dialog.offerdialog();

        //select fare
        SelectFarePage fare = new SelectFarePage(driver);
        fare.selectfare();
        extentTest.log(LogStatus.INFO, "Selected fare and clicked on Continue.");

        //Click on check out to continue to passenger details page
        CheckOutPage checkout = new CheckOutPage(driver);
        checkout.checkout();
        extentTest.log(LogStatus.INFO, "Clicked on Check out.");

        //check for seat selection dialog box
        SeatSelectionDialogBox seats = new SeatSelectionDialogBox(driver);
        seats.selectSeats();
        extentTest.log(LogStatus.INFO, "Clicked No Thanks on seats selection dialog box.");

        //fill passenger details
        PassengerDetailPage pass = new PassengerDetailPage(driver);
        try {
            pass.passengerdetails(TotalNumPassengers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.log(LogStatus.INFO, "Passenger and payment details filled and submitted.");

        //wait for error message
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //check for declined transaction message
        DeclinedMessagePage decline = new DeclinedMessagePage(driver);
        boolean result = decline.declineMessage();

        //log results
        if (result) {
            extentTest.log(LogStatus.PASS, "Declined payment message is getting displayed.");
            String extentReportImagePass = CaptureSnapshot.createScreenshot(driver, System.getProperty("user.dir") + "\\report\\");
            extentTest.log(LogStatus.PASS, "Snapshot : " + extentTest.addScreenCapture(extentReportImagePass));
        }
        else {
            extentTest.log(LogStatus.FAIL, "Declined payment message is NOT getting displayed.");
            String extentReportImageFail = CaptureSnapshot.createScreenshot(driver, System.getProperty("user.dir") + "\\Report\\");
            extentTest.log(LogStatus.FAIL, "Error Snapshot : " + extentTest.addScreenCapture(extentReportImageFail));
        }

    }

    @AfterTest
    public void end() {
        //close browser
        extent.endTest(extentTest);
        extent.flush();
        driver.quit();
    }
}