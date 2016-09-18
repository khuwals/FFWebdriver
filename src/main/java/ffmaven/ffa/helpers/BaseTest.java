package ffmaven.ffa.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BaseTest
{
	public WebDriver driver;
	public SFHelper sfHelper;
	
	/* Initialise locators*/
	String baseURL = "https://login.salesforce.com";
	String usrUsername = "sprint_154_unmanaged_smoke@fforce.com";
	String usrPassword = "metacube#123";
	
	
	public void LaunchBrowser()
	{
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("xpinstall.signatures.required", false);
		
		firefoxProfile.setPreference("browser.startup.homepage_override.mstone", "ignore");
		
		driver = new FirefoxDriver(firefoxProfile);
		
		//driver = new FirefoxDriver();	
		try {
			Thread.sleep(10000);
		} 
		catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		driver.manage().window().maximize();		
	}
	


	public void GoToApplication()
	{
		driver.get(baseURL);
	}
	
}
