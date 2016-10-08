package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Sashi on 07-10-2016.
 */
public class SelectFarePage {
    WebDriver driver;
    @FindBy(xpath = "id('outbound')//flights-table//label/span")
    public List<WebElement> SelectFare;

    public SelectFarePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectfare() {

        //page sync
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('outbound')//flights-table//label/span")));

        //select fare
        SelectFare.get(0).click();

        //continue after selecting fare
        driver.findElement(By.xpath("id('continue')")).click();
    }
}
