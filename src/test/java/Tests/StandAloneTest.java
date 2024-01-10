package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StandAloneTest 
{
	public static void main(String[] args) 
	{
		String product="Zara coat 3";
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement txt_UserName=driver.findElement(By.xpath("//input[@id='userEmail']"));
		WebElement txt_Password=driver.findElement(By.xpath("//input[@type='password']"));
		WebElement btn_Login=driver.findElement(By.id("login"));
		
		txt_UserName.sendKeys("pathan@gmail.com");
		txt_Password.sendKeys("Abcd@1234");
		btn_Login.click();
		
		driver.findElement(By.xpath("//b[normalize-space()='"+product.toUpperCase()+"']/parent::h5/parent::div //button[text()=' Add To Cart']")).click();
		
	}

}
