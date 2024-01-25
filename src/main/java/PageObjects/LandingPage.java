package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents
{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;//this keywords refers to current class instance variable
		PageFactory.initElements(driver, this);//driver is for constructor driver, this refers to global driver
	}
	
	@FindBy(xpath="//input[@id='userEmail']") 
	private WebElement txt_Username;
	
	@FindBy(xpath="//input[@type='password']") 
	private WebElement txt_Password;
	
	@FindBy(id="login")
	private WebElement btn_Login;
	
	
	@FindBy(xpath="//div[contains(@class,'flyInOut')]")
	private WebElement ToastMsg;
	
	public ProductCateloguePage login(String username, String Password)
	{
		txt_Username.sendKeys(username);
		txt_Password.sendKeys(Password);
		btn_Login.click();
		
		ProductCateloguePage productCateloguePage=new ProductCateloguePage(driver);
		return productCateloguePage;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getMsg() {
		waitUntillElementIsVisible(ToastMsg,10);
		return ToastMsg.getText();
	}

	
}
