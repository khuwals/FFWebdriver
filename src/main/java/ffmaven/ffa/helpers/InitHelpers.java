package ffmaven.ffa.helpers;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InitHelpers {
	
	WebDriver driver = new ChromeDriver();
	
	InitHelpers()
	{
		driver.manage().window().maximize();
		
		SFHelper sfHelper = new SFHelper(driver);
		
	}
	

}
