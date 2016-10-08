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
public class OfferDialogBox {
    WebDriver driver;
    @FindBy(xpath = "id('ngdialog1')//dialog-body//button")
    public List<WebElement> dialog;

    public OfferDialogBox(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void offerdialog() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (dialog.size() != 0)
            dialog.get(0).click();
    }
}
