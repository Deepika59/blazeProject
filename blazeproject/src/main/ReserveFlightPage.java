package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReserveFlightPage {


	WebDriver driver;
	
	private By countofFlightsBy = By.xpath("//input[@type='submit']");
	String chooseFlightBy = "//*[@class='table']/tbody/tr[";//1]/td[0]/input";
	private By tableBy = By.className("table");
	
	public ReserveFlightPage(WebDriver driver) { 
		this.driver = driver;
	}
	
	public int calculateTotal() {
		
		return (driver.findElements(countofFlightsBy).size());	
	}
	
	public PurchaseBookingPage chooseFlight(int i) { 

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(tableBy));
		chooseFlightBy = chooseFlightBy+(char)i+"]/td[0]/input";
		
		driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]/input")).click();

		return new PurchaseBookingPage(driver);
	}
	
}
