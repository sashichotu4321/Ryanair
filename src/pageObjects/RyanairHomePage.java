package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testData.GetData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sashi on 07-10-2016.
 */
public class RyanairHomePage {
    WebDriver driver;

    @FindBy(id="flight-search-type-option-one-way")
    public WebElement tripOneway;
    @FindBy(id="flight-search-type-option-return")
    public WebElement tripRound;
    @FindBy(className = "core-input")
    public List<WebElement> SelectAirport;
    @FindBy(xpath = "id('search-container')//strong")
    public List<WebElement> AirportList;
    @FindBy(className = "calendar-view")
    public WebElement dateWidget;
    @FindBy(xpath = "id('row-dates-pax')//div[@class='dropdown-handle']/core-icon/div")
    public WebElement Passengers;
    @FindBy(xpath = "id('row-dates-pax')//button[2]")
    public List<WebElement> ListOfPassengers;
    @FindBy(xpath = "id('row-dates-pax')//div[@class='value']")
    public List<WebElement> NumPassengersTravel;
    @FindBy(xpath = "id('search-container')//form//button[2]")
    public WebElement LetsGo;

    public RyanairHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public long SearchFlight() throws IOException {

        //get data
        String strTrip = GetData.getCellVal("Trip", "Trip");
        String strFromAirport = GetData.getCellVal("Trip", "From_Airport");
        String strToAirport = GetData.getCellVal("Trip", "To_Airport");
        int FromDate = Integer.valueOf(GetData.getCellVal("Trip", "From_Date").split("/")[1]);
        ArrayList<String> NumPassengersCol = GetData.getCompleteRowData("Passengers", "Pass_type");


        //Select One way trip
        if (strTrip.equals("One way")) {
            tripOneway.click();
        }
        else
        {
            tripRound.click();
        }

        //Select From Airports
        SelectAirport.get(0).clear();
        SelectAirport.get(0).sendKeys(strFromAirport);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        AirportList.get(0).click();

        //Select To Airport
        SelectAirport.get(1).clear();
        SelectAirport.get(1).sendKeys(strToAirport);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        AirportList.get(0).click();

        //wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Select Date
        List<WebElement> rows=dateWidget.findElements(By.tagName("ul"));
        List<WebElement> columns=dateWidget.findElements(By.tagName("li"));
        for (WebElement cell: columns){
            //Select From & To Dates
            if (String.valueOf(cell.getText()).equals(String.valueOf(FromDate))){
                cell.click();
                if (strTrip.equals("One way")) {
                    break;
                }
            }
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            if (!strTrip.equals("One way")) {
                int ToDate = Integer.valueOf(GetData.getCellVal("Trip", "To_Date").split("/")[1]);
                if (String.valueOf(cell.getText()).equals(String.valueOf(ToDate))){
                    cell.click();
                    break;
                }
            }
        }

        //wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //scroll up
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(250, 0)");

        //Select no. of passengers
        Passengers.click();
        int count = Collections.frequency(NumPassengersCol, "Adult");
        for (String PassType : NumPassengersCol)
        {
            if (PassType.equals("Adult") && (count - 1 != 0)) {
                ListOfPassengers.get(0).click();
                count --;
            } else if (PassType.equals("Teens")) {
                ListOfPassengers.get(1).click();
            } else if (PassType.equals("Child")) {
                ListOfPassengers.get(2).click();
            } else if (PassType.equals("Infant")) {
                ListOfPassengers.get(3).click();
            }
        }
        Passengers.click();

        //Get passengers count
        long PassCount = Integer.parseInt(NumPassengersTravel.get(0).getText().replaceAll("\\D", ""));

        //click on search (lets go button)
        LetsGo.click();

        return PassCount;
    }
}
