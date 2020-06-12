package com.salesforce;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility {
	
	public static WebDriver driver;
	
	// for extent report
	static ExtentReports reports;
    static ExtentTest logger;
    static  String reportFolder = "/Users/Mit/eclipse-workspace/SalesforceWithTestNG/ExtentReport";


    public static void InitializeReport(){
        reports = new ExtentReports(reportFolder + new SimpleDateFormat("'SalesForceReport_'YYYYMMddHHmm'.html'").format(new Date()));
    }

    public static String takeScreenshot() throws IOException {
        TakesScreenshot srcShot = ((TakesScreenshot) driver);
        File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
        String imagePath = reportFolder + "ScreenShots\\" + new SimpleDateFormat("'Image_'YYYYMMddHHmm'.png'").format(new Date());
        File destFile = new File(imagePath);
        FileUtils.copyFile(srcFile,destFile);
        return imagePath;
    }
	
    public Object[][] readDataFromExcelSheet(String sFile) throws Exception {
		File f = new File(System.getProperty("user.dir")+"/TestNGData.xls");
		HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(sFile));
		HSSFSheet myExcleSheet = myExcelBook.getSheet("Valid Credential");
		HSSFRow row1 = myExcleSheet.getRow(0);
		System.out.println(row1.getPhysicalNumberOfCells()); //To take total number of collumns
		System.out.println(myExcleSheet.getPhysicalNumberOfRows());//To take total number of Rows
		//System.out.println(row1.getLastCellNum());
		int iCountCol =row1.getLastCellNum();
		int iCountRow = myExcleSheet.getPhysicalNumberOfRows();
		Object[][] excelData= new Object[iCountRow][iCountCol]; //Creating multi dimensional array
		
		for(int countRow=0;countRow<iCountRow;countRow++) {
			for(int countCol = 0; countCol<iCountCol;countCol++) {
				HSSFRow tempRow=myExcleSheet.getRow(countRow);
				String sTemp;
				try {
				//System.out.println(tempRow.getCell(countCol).getStringCellValue());
				sTemp=tempRow.getCell(countCol).getStringCellValue();
				}catch(Exception e) {
					//System.out.println(tempRow.getCell(countCol).getNumericCellValue());
					sTemp=Double.toString(tempRow.getCell(countCol).getNumericCellValue());
				}
				excelData[countRow][countCol] = sTemp;
			}
		}	
		return excelData;
		}
    
    public Object[][] readDataFromExcelSheet1(String sFile) throws Exception {
		File f = new File(System.getProperty("user.dir")+"/TestNGData.xls");
		HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(sFile));
		HSSFSheet myExcleSheet = myExcelBook.getSheet("Invalid Credentials");
		HSSFRow row1 = myExcleSheet.getRow(0);
		System.out.println(row1.getPhysicalNumberOfCells()); //To take total number of collumns
		System.out.println(myExcleSheet.getPhysicalNumberOfRows());//To take total number of Rows
		//System.out.println(row1.getLastCellNum());
		int iCountCol =row1.getLastCellNum();
		int iCountRow = myExcleSheet.getPhysicalNumberOfRows();
		Object[][] excelData= new Object[iCountRow][iCountCol]; //Creating multi dimensional array
		
		for(int countRow=0;countRow<iCountRow;countRow++) {
			for(int countCol = 0; countCol<iCountCol;countCol++) {
				HSSFRow tempRow=myExcleSheet.getRow(countRow);
				String sTemp;
				try {
				//System.out.println(tempRow.getCell(countCol).getStringCellValue());
				sTemp=tempRow.getCell(countCol).getStringCellValue();
				}catch(Exception e) {
					//System.out.println(tempRow.getCell(countCol).getNumericCellValue());
					sTemp=Double.toString(tempRow.getCell(countCol).getNumericCellValue());
				}
				excelData[countRow][countCol] = sTemp;
			}
		}	
		return excelData;
		}
    
	static void StartBrowser(String str) throws InterruptedException
	{
		try {
		if(str.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		
		driver=new ChromeDriver();
		
		driver.get("http://google.com");
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().deleteAllCookies();
				
		}
		else if(str.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			
			driver=new FirefoxDriver();
			
			driver.get("http://google.com");
			
			driver.manage().deleteAllCookies();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	static void login(String sUsername, String sPassword)
	{

		 driver.get("https://login.salesforce.com/");
		 		
		WebElement username = driver.findElement(By.id("username"));
		username.clear();
		username.sendKeys(sUsername);

		WebElement password=driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys(sPassword);

		driver.findElement(By.id("Login")).click();
		
//		if(driver.findElement(By.xpath("//div[contains(text(),'Please enter your password.')]")).isDisplayed()) {
//			
//			WebElement errorMessage=driver.findElement(By.xpath("//div[contains(text(),'Please enter your password.')]"));
//			System.out.println(errorMessage.getText());
//		}
//		else
//		{
//			System.out.println("Login successfully!");
//		}

	}
	
    static void LogOut()
    {
    	
    	WebElement UsernameArrow=driver.findElement(By.id("userNav-arrow"));
		
		waitForPageElementToVisible(UsernameArrow);
					
		UsernameArrow.click();
				
    	WebElement logOut=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		
		waitForPageElementToVisible(logOut);
		
		logOut.click();
    }
    
    static void waitForPageElementToVisible(WebElement eleToWait) 
    {
		WebDriverWait wait = new WebDriverWait(BrowserUtility.driver, 30);
		
		wait.until(ExpectedConditions.visibilityOf(eleToWait));
		
	}	
	static void quitBrowser()
	{
		driver.quit();
	}



}
