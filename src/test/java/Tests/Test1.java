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
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.OrderConfirmationPage;
import PageObjects.PaymentConfirmationPage;
import PageObjects.ProductCateloguePage;
import testBase.Base;

public class Test1 extends Base
{
	@Test
	public void PlaceOrder() throws IOException 
	{
		String product="Zara coat 3";
		String country="India";		
//		LandingPage landingPage=launchApplication();
		ProductCateloguePage productCateloguePage=landingPage.login("pathan@gmail.com", "Abcd@1234");
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
	
}
