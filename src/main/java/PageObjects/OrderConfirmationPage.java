package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents{
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;//this keywords refers to current class instance variable
		PageFactory.initElements(driver, this);//driver is for constructor driver, this refers to global driver
	}
	
	@FindBy(xpath="//label[@class='ng-star-inserted']")
	WebElement OrderNum;
	
	public String getOrderNum()
	{
		return OrderNum.getText();
	}

}
