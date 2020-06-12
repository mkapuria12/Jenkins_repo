package com.salesforce;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;


public class TC1_SFDC_LoginValid extends BrowserUtility{
		
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
		
		@Test(dataProvider="LoginCredentials",priority=0)
		public void TC1ValidCresentials(String sUser, String sPass) {
			System.out.println("Test case 1:");
			login(sUser, sPass);
			System.out.println("TC1_Log in successfully with valid credentials!");
		}
		
		@AfterMethod
		public void logoutURL()
		{
			LogOut();
			System.out.println("Log out successfully!");
		}
		
		@AfterSuite
		public void quitbrowser()
		{
			quitBrowser();
			System.out.println("Quit browser successfully!");
		}
}
