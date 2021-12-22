package test;

import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import main.BookingConfirmationPage;
import main.IndexPage;
import main.PurchaseBookingPage;
import main.ReserveFlightPage;

public class FlightBookingTest {

	
    String baseUrl = "https://blazedemo.com/index.php";
    

    
		@Test(priority=1)
	    public void validateSuccess() throws ParseException {
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			
		    IndexPage indexPage = new IndexPage(driver);
		    ReserveFlightPage reserveFlightPage = new ReserveFlightPage(driver);
		    PurchaseBookingPage purchaseBookingPage = new PurchaseBookingPage(driver);
		    BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage(driver);
		    
			driver.get(baseUrl);
			driver.manage().window().maximize();
			indexPage.selectSourceCity("Paris", "London");
			Assert.assertTrue(reserveFlightPage.calculateTotal() > 0);
			reserveFlightPage.chooseFlight(1);
			purchaseBookingPage.enterBookingDetails("Test User", "123 Main Street", "Chennai", "TN", "12345", "visa",
					"444112345", "09", "2023", "Test User");

			bookingConfirmationPage.validateBookingConfirmation();
	       
	        driver.close();
	    }
	
	
	@Test(priority=2)
    public void validateCardDetailsOnConfirmation() throws ParseException {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
	    IndexPage indexPage = new IndexPage(driver);
	    ReserveFlightPage reserveFlightPage = new ReserveFlightPage(driver);
	    PurchaseBookingPage purchaseBookingPage = new PurchaseBookingPage(driver);
	    BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage(driver);
	    
		driver.get(baseUrl);
		driver.manage().window().maximize();
		indexPage.selectSourceCity("Paris", "London");
		Assert.assertTrue(reserveFlightPage.calculateTotal() > 0);
		reserveFlightPage.chooseFlight(1);
		purchaseBookingPage.enterBookingDetails("Test User", "123 Main Street", "Chennai", "TN", "12345", "visa",
				"444112345", "09", "2023", "Test User");

		bookingConfirmationPage.validateCardDetails("444112345", "09", "2023");
       
        driver.close();
    }
	
	
	@Test(priority=3)
    public void validateCardExpiryInResults() throws ParseException {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
	    IndexPage indexPage = new IndexPage(driver);
	    ReserveFlightPage reserveFlightPage = new ReserveFlightPage(driver);
	    PurchaseBookingPage purchaseBookingPage = new PurchaseBookingPage(driver);
	    BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage(driver);
	    
		driver.get(baseUrl);
		driver.manage().window().maximize();
		indexPage.selectSourceCity("Paris", "London");
		Assert.assertTrue(reserveFlightPage.calculateTotal() > 0);
		reserveFlightPage.chooseFlight(1);
		purchaseBookingPage.enterBookingDetails("Test User", "123 Main Street", "Chennai", "TN", "12345", "visa",
				"444112345", "09", "2023", "Test User");

		bookingConfirmationPage.validateCardExpiry();
       
        driver.close();
    }
	
}
