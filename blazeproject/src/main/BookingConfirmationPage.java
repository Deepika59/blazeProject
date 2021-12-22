package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BookingConfirmationPage {

	WebDriver driver;
	
	private By confMsgBy = By.xpath("//div[@class='container hero-unit']/h1");
	private By idBy = By.xpath("//div[@class='container hero-unit']/table/tbody/tr[1]/td[2]");
	private By statusBy = By.xpath("//div[@class='container hero-unit']/table/tbody/tr[2]/td[2]");
	private By cardNoBy = By.xpath("//div[@class='container hero-unit']/table/tbody/tr[4]/td[2]");
	private By cardExpBy = By.xpath("//div[@class='container hero-unit']/table/tbody/tr[5]/td[2]");

	
	public BookingConfirmationPage(WebDriver driver) { 
		this.driver = driver;
	}
	
	public void validateBookingConfirmation() throws ParseException { 
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(confMsgBy));
		
		//verify confirmation message
		Assert.assertTrue(driver.findElement(confMsgBy).isDisplayed());
		
		//verify Booking ID
		Assert.assertTrue(driver.findElement(idBy).isDisplayed() && 
				driver.findElement(idBy).getText().length() == 13);
		
		//verify Booking status
		Assert.assertTrue(
				driver.findElement(statusBy).isDisplayed() && 
				driver.findElement(statusBy).getText().equalsIgnoreCase("PendingCapture"));
		
	
	}
	
	public void validateCardDetails(String cardNo, String ccMon, String ccYear) throws ParseException { 
		
		//verify if entered card matches with one displaying in booking confirmation
		StringBuilder encCardNo = new StringBuilder(cardNo);
		for (int i=0; i<=cardNo.length()-4; i++)
			encCardNo.setCharAt(i, 'x');
		Assert.assertTrue(
				driver.findElement(cardNoBy).getText().equals(encCardNo.toString()), "Entered Card does not match with results");
		Assert.assertTrue(
						driver.findElement(cardExpBy).getText().equals(ccMon+" /"+ccYear), "Entered Expiry does not match with results" );
		
			
	}
	
	
	public void validateCardExpiry() throws ParseException { 
		
		//verify if entered card is not a expired one.
		Calendar now = Calendar.getInstance();
		int todaymonth = now.get(Calendar.MONTH)+1;
		int todayyear = now.get(Calendar.YEAR);
		int cardmonth = Integer.parseInt(driver.findElement(cardExpBy).getText().split(" /")[0]);
		int cardyear = Integer.parseInt(driver.findElement(cardExpBy).getText().split(" /")[1]);

		Assert.assertTrue(cardyear >=todayyear, "Card is expired");
		if (cardyear >=todayyear) 
			Assert.assertTrue(cardmonth>=todaymonth, "Card is expired");
		
	}
	
}
