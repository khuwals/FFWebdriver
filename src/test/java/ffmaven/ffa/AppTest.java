package ffmaven.ffa;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

//import ru.stqa.selenium.wait.ExpectedConditions;

/**
 * Unit test for simple App.
 */
public class AppTest {

	String siteName = "https://login.salesforce.com";
	String usrUsername = "sprint_154_unmanaged_smoke@fforce.com";
	String usrPassword = "metacube#123";
	String homePageTitle = "Force.com Home Page ~ Salesforce - Developer Edition";
	String companyLabel = "table[id$=':dtUserCompanySelector'] tbody tr:nth-of-type(sf_replace_string) td:nth-of-type(1) label";
	String companyCheckBox = "table[id$=':dtUserCompanySelector'] tbody tr:nth-of-type(sf_replace_string) td:nth-of-type(2) input";
	String COMPANY = "Merlin Auto USA";
	String companyList = "table[id$=':dtUserCompanySelector'] tbody tr";
	
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, 60);

	@Test
	public void testApp() {
		
		driver.get(siteName);
		driver.manage().window().maximize();

		loginApp(usrUsername, usrPassword);
		
		Assert.assertEquals(driver.getTitle(), homePageTitle, "Unable to Login");
		
		selectCompany(COMPANY);
		
		sleepForTimePeriod();

	}

	public void selectCompany(String company)
	{
		boolean companyselected = false;
		driver.findElement(By.linkText("Select Company")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(companyList)));
		//De-select all the companies
		deSelectAllCompanies();
		
		int numberOfCompanies = driver.findElements(By.cssSelector("table[id$=dtUserCompanySelector] tbody tr")).size();
		String textValue = "";

		for (int lineNumber = 1; lineNumber <= numberOfCompanies; lineNumber++) {
			String lineNumbers = Integer.toString(lineNumber);
			textValue = driver.findElement(By.cssSelector(companyLabel.replaceAll("sf_replace_string",lineNumbers))).getText();

			if (textValue.equals(company)) {
				WebElement checkboxElement = driver.findElement(By
						.cssSelector(companyCheckBox.replaceAll(
								"sf_replace_string", lineNumbers)));
				if (!checkboxElement.isSelected()) {
					checkboxElement.click();
					companyselected = true;
				}
			}
		}
		
		if(companyselected)
			clickSaveButton();
		else
			Assert.assertEquals(false, true, "Company is not present");
	}
	
	public void deSelectAllCompanies()
	{
		List<WebElement> companies = driver.findElements(By.cssSelector(companyList));
		
		for (WebElement company : companies) {
			if(company.findElement(By.tagName("Input")).isSelected())
				company.findElement(By.tagName("Input")).click();			
		}
	}
	
	public void clickSaveButton()
	{
		List<WebElement> saveButton = driver.findElements(By.xpath("//input[@value='Save' and @class='btn']"));
		saveButton.get(0).click();
	}

	public void loginApp(String usrUsername, String usrPassword) 
	{	
		driver.findElement(By.id("username")).sendKeys(usrUsername);
		driver.findElement(By.id("password")).sendKeys(usrPassword);
		driver.findElement(By.id("Login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.linkText("Select Company")));
	}

	@AfterSuite
	public void closeBrowser() {
		System.out.print("Loggin out of salesforce");
		driver.quit();
	}

	public void sleepForTimePeriod() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
