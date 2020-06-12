package com.salesforce;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC2_SFDC_LoginInvalid extends BrowserUtility{
	
	@BeforeSuite
	public void launchBrowser() throws InterruptedException
	{
		StartBrowser("chrome");
		System.out.println("Browser launched successfully!");
		//reports.startTest("Login with valid credentials");
		//logger.log(LogStatus.INFO, "Launched the browser successfully!");
	}
	@DataProvider(name="LoginCredentials")
	Object[][] trainerInfoFromExcel()throws Exception{
		String sFile = System.getProperty("user.dir")+"/TestNGData.xls";
		 return readDataFromExcelSheet1(sFile);
	}
	
	@Test(dataProvider="LoginCredentials", priority=1)
	public void TC1InValidCresentials(String sUser, String sPass) throws InterruptedException {
		System.out.println("Test case 3:");

		login(sUser, sPass);
		System.out.println("TC2_Log in failed with invalid credentials.");
		Thread.sleep(3000);
		WebElement errorMessage=driver.findElement(By.xpath("//div[@id='error']"));
		String error=errorMessage.getText();
		if(error.equalsIgnoreCase("Please enter your password.")) {
			
			System.out.println(error);
			
			AssertJUnit.assertEquals("Please enter your password.", true);
		}
		else if(error.equalsIgnoreCase("Please check your username and password. If you still can't log in, contact your Salesforce administrator."))
		{
			System.out.println(error);
			//assertEquals(error, "Please enter your password.");
			AssertJUnit.assertEquals("Please check your username and password. If you still can't log in, contact your Salesforce administrator.", true);
			
		}
		else
		{
			System.out.println("Login successfully!");
		}
		quitBrowser();
	}
	@AfterSuite
	public void quitbrowser()
	{
		quitBrowser();
	}

}
