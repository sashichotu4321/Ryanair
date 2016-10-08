package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testData.GetData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sashi on 07-10-2016.
 */
public class PassengerDetailPage {
    WebDriver driver;
    @FindBy(xpath = "//select[contains(@id, 'title')]")
    public List<WebElement> title;
    @FindBy(xpath = "//input[contains(@id, 'firstName')]")
    public List<WebElement> firstName;
    @FindBy(xpath = "//input[contains(@id, 'lastName')]")
    public List<WebElement> lastName;
    @FindBy(xpath = "//input[contains(@id, 'email')]")
    public WebElement email;
    @FindBy(xpath = "//input[contains(@id, 'confirmEmail')]")
    public WebElement confirmEmail;
    @FindBy(xpath = "//select[@name='phoneNumberCountry']")
    public WebElement phoneNumCountry;
    @FindBy(xpath = "//input[@name='phoneNumber']")
    public WebElement phoneNumber;
    @FindBy(xpath = "//input[contains(@id, 'cardNumber')]")
    public WebElement cardNum;
    @FindBy(xpath = "//select[contains(@id, 'cardType')]")
    public WebElement cardType;
    @FindBy(xpath = "//select[contains(@id, 'expiryMonth')]")
    public WebElement cardExpiryMon;
    @FindBy(xpath = "//select[contains(@name, 'expiryYear')]")
    public WebElement cardExpiryYear;
    @FindBy(xpath = "//input[contains(@name, 'securityCode')]")
    public WebElement cardCVV;
    @FindBy(xpath = "//input[contains(@name, 'cardHolderName')]")
    public WebElement cardHolderName;
    @FindBy(xpath = "//input[contains(@name, 'billingAddressAddressLine1')]")
    public WebElement cardBillingAdd1;
    @FindBy(xpath = "//input[contains(@name, 'billingAddressAddressLine2')]")
    public WebElement cardBillingAdd2;
    @FindBy(xpath = "//input[contains(@id, 'billingAddressCity')]")
    public WebElement cardBillingCity;
    @FindBy(xpath = "//input[contains(@id, 'billingAddressPostcode')]")
    public WebElement cardBillingZip;
    @FindBy(xpath = "//select[contains(@id, 'billingAddressCountry')]")
    public WebElement cardBillingCountry;
    @FindBy(xpath = "//input[contains(@id, 'acceptTerms')]")
    public WebElement acceptTerms;
    @FindBy(xpath = "//button[contains(@id, 'pay-now-btn')]")
    public WebElement payNow;

    public PassengerDetailPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void passengerdetails(long TotalPassengers) throws IOException {

        ArrayList<String> intials = GetData.getCompleteRowData("Passengers", "Pass_Initial");
        ArrayList<String> firstname = GetData.getCompleteRowData("Passengers", "Pass_FirstName");
        ArrayList<String> lastname = GetData.getCompleteRowData("Passengers", "Pass_LastName");
        String emaildata = GetData.getCellVal("Other_Details", "Pass_Email");
        String phoneNumCountrydata = GetData.getCellVal("Other_Details", "Pass_PhoneCountry");
        String phoneNumberdata = GetData.getCellVal("Other_Details", "Pass_PhoneNum");
        String cardNumdata = GetData.getCellVal("Other_Details", "Card_Num");
        String cardTypedata = GetData.getCellVal("Other_Details", "Card_Type");
        String cardExpiryMondata = GetData.getCellVal("Other_Details", "Card_ExpiryMonth");
        String cardExpiryYeardata = GetData.getCellVal("Other_Details", "Card_ExpiryYear");
        String cardCVVdata = GetData.getCellVal("Other_Details", "Card_CVV");
        String cardHolderNamedata = GetData.getCellVal("Other_Details", "CardHolder_Name");
        String cardBillingAdd1data = GetData.getCellVal("Other_Details", "BillingAddress1");
        String cardBillingAdd2data = GetData.getCellVal("Other_Details", "BillingAddress2");
        String cardBillingCitydata = GetData.getCellVal("Other_Details", "BillingCity");
        String cardBillingZipdata = GetData.getCellVal("Other_Details", "BillingZip");
        String cardBillingCountrydata = GetData.getCellVal("Other_Details", "Billing_Country");
        //Fill Passengers details page
        /* for (int CntPass = 0; CntPass<=intials.size() - 1; CntPass++) {
            title.get(CntPass).sendKeys(intials.get(CntPass));
        } */
        int CntPass = 0;
        for (String ini : intials)
        {
            title.get(CntPass).sendKeys(ini);
            CntPass++;
        }
        CntPass = 0;
        for (String fname : firstname)
        {
            firstName.get(CntPass).sendKeys(fname);
            CntPass++;
        }
        CntPass = 0;
        for (String lname : lastname)
        {
            lastName.get(CntPass).sendKeys(lname);
            CntPass++;
        }

        email.sendKeys(emaildata);
        confirmEmail.sendKeys(emaildata);
        phoneNumCountry.sendKeys(phoneNumCountrydata);
        phoneNumber.sendKeys(phoneNumberdata);
        cardNum.sendKeys(cardNumdata);
        cardType.sendKeys(cardTypedata);
        cardExpiryMon.sendKeys(cardExpiryMondata);
        cardExpiryYear.sendKeys(cardExpiryYeardata);
        cardCVV.sendKeys(cardCVVdata);
        cardHolderName.sendKeys(cardHolderNamedata);
        cardBillingAdd1.sendKeys(cardBillingAdd1data);
        cardBillingAdd2.sendKeys(cardBillingAdd2data);
        cardBillingCity.sendKeys(cardBillingCitydata);
        cardBillingZip.sendKeys(cardBillingZipdata);
        cardBillingCountry.sendKeys(cardBillingCountrydata);
        acceptTerms.click();
        payNow.click();
    }
}
