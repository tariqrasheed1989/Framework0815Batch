package testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import PageObjects.LandingPage;

public class Base {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
					
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Tariq\\eclipse-workspace\\com.FrameWork815Batch\\src\\test\\java\\resources\\info.properties");
		prop.load(fis);
		String browser=prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			
		 driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			
			driver=new EdgeDriver();
		}else if(browser.equals("firefox")) {
			//firefox
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
