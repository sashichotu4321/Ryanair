package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Created by Sashi on 07-10-2016.
 */
public class DeclinedMessagePage {

    WebDriver driver;
    @FindBy(xpath = "//prompt[contains(@text, 'common.components.payment_forms.error_explain_declined')]")
    public List<WebElement> declineMsg;

    public DeclinedMessagePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean declineMessage() {
        if (declineMsg.size() != 0)
            return true;
        else
            return false;
    }
}
