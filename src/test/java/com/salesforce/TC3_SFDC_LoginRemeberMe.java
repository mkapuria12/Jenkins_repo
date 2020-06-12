package com.salesforce;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC3_SFDC_LoginRemeberMe extends BrowserUtility{
	
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
		 return readDataFromExcelSheet(sFile);
	}
	
	@Test(dataProvider="LoginCredentials", priority=1)
	public void TC2RememenerMe(String sUser, String sPass) throws InterruptedException {
		System.out.println("Test case 2:");

		driver.get("https://login.salesforce.com/");
		
		waitForPageElementToVisible(driver.findElement(By.id("username")));
		
		WebElement username=driver.findElement(By.id("username"));
		
		username.clear();
		
		username.sendKeys(sUser);
		
		WebElement password=driver.findElement(By.id("password"));
		
		waitForPageElementToVisible(password);
		password.clear();
		password.sendKeys(sPass);
		
		WebElement rememberMe=driver.findElement(By.id("rememberUn"));
		
		waitForPageElementToVisible(rememberMe);
		
		rememberMe.click();
		
		driver.findElement(By.id("Login")).click();
		
		System.out.println("Log in successfully with valid credentials");
		
		LogOut();
		
		System.out.println("Log out successfully!");
		
		//driver.get("https://login.salesforce.com/");
		Thread.sleep(3000);
		
		System.out.println(driver.findElement(By.xpath("//span[@id='idcard-identity']")).getText());
		
		System.out.println("Username remembered");
	}
	
	@AfterSuite
	public void quitbrowser()
	{
		quitBrowser();
	}
	
}
