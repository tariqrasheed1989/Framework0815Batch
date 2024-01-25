package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.ProductCateloguePage;
import testBase.Base;

public class ErrorValidation extends Base{
	
	@Test
	public void loginValidation() {
		
	landingPage.login("pathan1@gmail.com", "Abcd@11234");
	String ActualMsg=landingPage.getMsg();
	Assert.assertEquals(ActualMsg, "Incorrect email or password.");
	}

	@Test
	public void ProductValidation() {
		String product="Zara coat 3";
		ProductCateloguePage productCateloguePage=landingPage.login("pathan@gmail.com", "Abcd@1234");
		productCateloguePage.AddToCart(product);
		String ActualToastMsg=productCateloguePage.getToastMessage();
		Assert.assertEquals(ActualToastMsg, "Product Added To Cart");
		CartPage cartPage=productCateloguePage.goToCart();	
		String ActualCartProductName=cartPage.getCartProductName();
		Assert.assertFalse(ActualCartProductName.equals("Zara coat 3"));;
	}
}
