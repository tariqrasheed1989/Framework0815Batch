package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;//this keywords refers to current class instance variable
		PageFactory.initElements(driver, this);//driver is for constructor driver, this refers to global driver
	}
	
	@FindBy(xpath="//div[@class='cartSection'] //h3")
	WebElement CartProductName;
	
	@FindBy(xpath="//div[@class='cartSection'] //p[@class='itemNumber']")
	WebElement OrderNumber;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement Checkout;
	
	public String getCartProductName() {
		
		String cartProd=CartProductName.getText();
		return cartProd;
	}
	
	public String getOrderNum() {		
		return OrderNumber.getText();		
	}
	
	public void checkout() {
		waitUnitlElementIsClickable(Checkout, 10);
		clickElement(Checkout,"CheckOut button");
	}

}
