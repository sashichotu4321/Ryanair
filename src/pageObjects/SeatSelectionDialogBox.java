package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sashi on 07-10-2016.
 */
public class SeatSelectionDialogBox {
    WebDriver driver;
    @FindBy(xpath = "id('ngdialog1')//button[@class='core-btn-ghost seat-prompt-popup-footer-btn']")
    public List<WebElement> selectseats;

    public SeatSelectionDialogBox(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectSeats() {
        //check for dialog box for seats selection
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (selectseats.size() != 0)
            selectseats.get(0).click();
    }
}
