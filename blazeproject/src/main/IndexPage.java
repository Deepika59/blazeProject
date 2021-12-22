package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class IndexPage {

	WebDriver driver;
	
	private By sourceCity = By.name("fromPort");
	private By destinationCity = By.name("toPort");
	private By findFlightsButton = By.xpath("//input[@type='submit']");
	
	public IndexPage(WebDriver driver) { 
		this.driver = driver;
	}
	
	public ReserveFlightPage selectSourceCity(String source, String dest) {
		Select selectSource = new Select(driver.findElement(sourceCity));
		selectSource.selectByValue(source);
		
		Select selectDest = new Select(driver.findElement(destinationCity));
		selectDest.selectByValue(dest);
		
		driver.findElement(findFlightsButton).click();
		
		return new ReserveFlightPage(driver);
		
	}
	

}
