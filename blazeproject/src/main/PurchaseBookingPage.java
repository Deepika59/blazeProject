package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PurchaseBookingPage {

WebDriver driver;
	
	private By nameBy = By.id("inputName");
	private By addressBy = By.id("address");
	private By cityBy = By.id("city");
	private By stateBy = By.id("state");
	private By zipBy = By.id("zipCode");
	private By cardTypeBy = By.id("cardType");
	private By ccNumberBy = By.id("creditCardNumber");
	private By ccMonthBy = By.id("creditCardMonth");
	private By ccYearBy = By.id("creditCardYear");
	private By ccNameBy = By.id("nameOnCard");
	private By purchaseFlightButtonBy = By.xpath("//input[@type='submit']");
	
	public PurchaseBookingPage(WebDriver driver) { 
		this.driver = driver;
	}
	
	public BookingConfirmationPage enterBookingDetails(String Name, String Address, String City, String State,
			String zipCode, String cardType, String ccNumber, String ccMonth, String ccYear, String ccName) {
		
		driver.findElement(nameBy).sendKeys(Name);
		driver.findElement(addressBy).sendKeys(Address);
		driver.findElement(cityBy).sendKeys(City);
		driver.findElement(stateBy).sendKeys(State);
		driver.findElement(zipBy).sendKeys(zipCode);
		driver.findElement(ccNumberBy).sendKeys(ccNumber);
		driver.findElement(ccMonthBy).sendKeys(ccMonth);
		driver.findElement(ccYearBy).sendKeys(ccYear);
		driver.findElement(ccNameBy).sendKeys(ccName);
		
		if(cardType != "" ) {
			Select selectType = new Select(driver.findElement(cardTypeBy));
			selectType.selectByValue(cardType);
		}
		driver.findElement(purchaseFlightButtonBy).click();
		
		return new BookingConfirmationPage(driver); 
		
	}
	
}
