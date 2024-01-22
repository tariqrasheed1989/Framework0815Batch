package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductCateloguePage extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCateloguePage(WebDriver driver)
	{
		super(driver);//super keyword sends the driver to parent class constructor
		this.driver=driver;//this keywords refers to current class instance variable
		PageFactory.initElements(driver, this);//driver is for constructor driver, this refers to global driver
	}
	
	@FindBy(xpath="//div[@class='card-body']/h5/b") //@FindBy automatically detect "driver.findElements" when we user List<WebElement>
	List<WebElement> ProductsName;

	@FindBy(xpath="//button[normalize-space()='Add To Cart']")
	List<WebElement> AddToCart;
	
	@FindBy(xpath="//div[contains(@class,'toast-message')]")
	private WebElement ToastMsg;
	
	
//	By msg=By.xpath("//div[contains(@class,'toast-message')]");
	
	public void AddToCart(String Product)
	{
		for(int i=0;i<ProductsName.size();i++)
		{
			String productName=ProductsName.get(i).getText();
			if(productName.equalsIgnoreCase(Product)){
				
				AddToCart.get(i).click();
			}			
		}
	}
	
	public String getToastMessage()
	{
		waitUntillElementIsVisible(ToastMsg, 10);
		String msg=ToastMsg.getText();
		waitUntillElementGetsDisappear(ToastMsg, 10);
		return msg;
	}
	
	
	
}
