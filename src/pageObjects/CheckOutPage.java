package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sashi on 07-10-2016.
 */
public class CheckOutPage {
    WebDriver driver;
    @FindBy(xpath = "id('booking-selection')/article//section//button[@class='core-btn-primary core-btn-block core-btn-medium btn-text']")
    public WebElement checkoutbutton;

    public CheckOutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkout() {
        //page sync
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('booking-selection')/article//section//button[@class='core-btn-primary core-btn-block core-btn-medium btn-text']")));

        //click on Check out
        checkoutbutton.click();
    }
}
