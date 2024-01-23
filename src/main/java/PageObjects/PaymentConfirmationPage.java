package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class PaymentConfirmationPage extends AbstractComponents{
	
	WebDriver driver;
	public PaymentConfirmationPage(WebDriver driver)
	{
		super(driver);//super keyword sends the driver to parent class constructor
		this.driver=driver;//this keywords refers to current class instance variable
		PageFactory.initElements(driver, this);//driver is for constructor driver, this refers to global driver
	}
	
	@FindBy(xpath="//div[contains(.,'CVV Code') and @class='title']/following-sibling::input")
	WebElement CVV_Code;
	
	@FindBy(xpath="//div[contains(.,'Name on Card') and @class='title']/following-sibling::input")
	WebElement NameOnCard;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted'] //button")
	List<WebElement> CountryOptions;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement PlaceOrderButton;
	
	public void paymentDetails(String CVV,String CardName) {
		CVV_Code.sendKeys(CVV);
		NameOnCard.sendKeys(CardName);
	}
	
	public void selectCountry(String country) {
		Country.sendKeys(country);
		waitUntillElementIsVisible(CountryOptions.get(0), 10);
		for(WebElement opt:CountryOptions)
		{
			String countryName=opt.getText();
			if(countryName.equals(country))
			{
				opt.click();
			}
		}
	}
	
	public OrderConfirmationPage placeOrder()
	{
		clickElement(PlaceOrderButton, "Place Order Button");
		return new OrderConfirmationPage(driver);
	}
}
