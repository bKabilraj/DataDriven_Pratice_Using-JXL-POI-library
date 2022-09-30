package loginTestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Login {
	
	String [][] data=null;
	WebDriver driver;
	
	
	@DataProvider(name="loginData")
	public String [][] loginDataProvider() throws BiffException, IOException {
		
		data=getExcelData();
		
		return data;
	}
	
	public String[][] getExcelData() throws BiffException, IOException {
		FileInputStream excel = new FileInputStream
				("C:\\Users\\hp\\Desktop\\Selenium_Learning\\New Microsoft Excel Worksheet.xls\\");
		Workbook workbook=Workbook.getWorkbook(excel);
		Sheet sheet=workbook.getSheet(0);
		
		int rowCount=sheet.getRows();
		int columnCount=sheet.getColumns();
		
		String testData[][]=new String[rowCount-1][columnCount];
		for(int i=1; i<rowCount; i++) {
			for(int j=0; j<columnCount; j++) {
				testData[i-1][j]=sheet.getCell(j, i).getContents();
			}
		}
		return testData;
		
	}
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\hp\\Downloads\\new driver\\chromedriver.exe\\");
		driver=new ChromeDriver();
		
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	@Test(dataProvider="loginData")
	public void loginWithCorrectUserName(String uName, String pWord) {
		
		
		driver.get("http://transport-hub-qa-release.com.s3-website.ap-south-1.amazonaws.com/");
		
		WebElement username=driver.findElement(By.id("email"));
		username.sendKeys(uName);
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(pWord);
		
		WebElement  subButton=driver.findElement(By.id("mui-1"));
		subButton.click();
		
		
		
	}
	

}
