package com.hdfcHybrid;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class HybdridHdfc {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		String action;
		String locator;
		String element;
		String inputData;

		WebDriver driver;

		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://portal.hdfc.com/signup?redirectTo=gettingStarted&back=signup");

		String file_path = "C:\\Users\\HP\\Desktop\\Hdfc.xlsx";

		FileInputStream file = new FileInputStream(file_path);

		Workbook book = WorkbookFactory.create(file);

		Sheet sheet = book.getSheet("Sheet1");

		Object[][] fetch_data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			int j = 0;
			action = sheet.getRow(i + 1).getCell(j).toString().trim();
			locator = sheet.getRow(i + 1).getCell(j + 1).toString().trim();
			element = sheet.getRow(i + 1).getCell(j + 2).toString().trim();
			inputData = sheet.getRow(i + 1).getCell(j + 3).toString().trim();

			switch (action) {
			case "EnterData":
				if (locator.equalsIgnoreCase("id")) {
					driver.findElement(By.id(element)).sendKeys(inputData);
				} else if (locator.equalsIgnoreCase("xpath")) {
					driver.findElement(By.xpath(element)).sendKeys(inputData);
				} else {
					System.out.println("Invalid Locator for enter data...");
				}
				break;

			case "Click":
				if (locator.equalsIgnoreCase("id")) {
					driver.findElement(By.id(element)).click();
				} else if (locator.equalsIgnoreCase("xpath")) {
					driver.findElement(By.xpath(element)).click();
				} else {
					System.out.println("Invalid Locator for click...");
				}
				break;

			default:
				System.out.println("No action found...");
				break;
			}
		}

	}

}
