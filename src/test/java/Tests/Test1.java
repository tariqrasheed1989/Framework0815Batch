package Tests;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.PaymentConfirmationPage;
import PageObjects.ProductCateloguePage;
import testBase.Base;

public class Test1 extends Base
{
	@Test(dataProvider = "getData")
	public void PlaceOrder(String username, String password, String product) throws IOException 
	{
		//String product="Zara coat 3";
		String country="India";		
//		LandingPage landingPage=launchApplication();
		ProductCateloguePage productCateloguePage=landingPage.login(username, password);
		productCateloguePage.AddToCart(product);
		String ActualToastMsg=productCateloguePage.getToastMessage();
		Assert.assertEquals(ActualToastMsg, "Product Added To Cart");
		CartPage cartPage=productCateloguePage.goToCart();	
		String ActualCartProductName=cartPage.getCartProductName();
		String OrderNumber = cartPage.getOrderNum();
		Assert.assertEquals(ActualCartProductName, product.toUpperCase());
		PaymentConfirmationPage paymentConfirmationPage=cartPage.checkout();
		paymentConfirmationPage.paymentDetails("0997", "Sachin");
		paymentConfirmationPage.selectCountry(country);
		OrderConfirmationPage orderConfirmationPage=paymentConfirmationPage.placeOrder();	
		String actualOrderNum=orderConfirmationPage.getOrderNum();
//		Assert.assertEquals(actualOrderNum, OrderNumber);
		
	}
	
	@DataProvider
	public String[][] getData() {
		
		String[][] dataSet= {{"pathan@gmail.com","Abcd@1234","ADIDAS ORIGINAL"},{"sunil3001@gmail.com","India@123","Zara coat 3"}};
		return dataSet;
	}
	
}
