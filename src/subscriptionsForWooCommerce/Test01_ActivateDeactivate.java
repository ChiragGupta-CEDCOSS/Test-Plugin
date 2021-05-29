package subscriptionsForWooCommerce;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test01_ActivateDeactivate {

	public static void main(String[] args) {
		
		// To test the Activation and Deactivation of Subscriptions For WooCommerce Plugin with onboarding and deactivation form working
		System.setProperty("webdriver.chrome.driver", "/home/cedcoss/MWB Testing Chirag Important/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		// Maximize Windows
		driver.manage().window().maximize();
		
		// Login admin
		driver.get("http://localhost:10003/wp-admin/");
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("root");
		driver.findElement(By.id("wp-submit")).click();
		
		// Code to get date and time
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		// Activate plugin
		WebDriverWait wait = new WebDriverWait(driver,30);
		driver.findElement(By.cssSelector("a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']")).click();
		driver.findElement(By.id("activate-subscriptions-for-woocommerce")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("Activation Success message printed");
		
		//Verify link with plugin description.
		assertTrue(driver.findElement(By.xpath("//tr[@data-slug='subscriptions-for-woocommerce']//a[contains(text(),'Settings')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//a[@id='deactivate-subscriptions-for-woocommerce']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//a[@href='https://makewebbetter.com/?utm_source=MWB-subscriptions-backend&utm_medium=MWB-ORG-backend&utm_campaign=MWB-backend']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//a[@aria-label='More information about Subscriptions For WooCommerce']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//a[normalize-space()='Free Demo']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//a[@href='https://docs.makewebbetter.com/subscriptions-for-woocommerce/?utm_source=MWB-subscriptions-backend&utm_medium=MWB-docORG-backend&utm_campaign=MWB-backend']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//a[@href='https://makewebbetter.com/submit-query/']")).isDisplayed());
		
		// Check MakeWebBetter Sub-menu
		Actions actions = new Actions(driver);
		WebElement MakeWebBetter = driver.findElement(By.xpath("//div[normalize-space()='MakeWebBetter']"));
		actions.moveToElement(MakeWebBetter).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Subscriptions For WooCommerce']")));
		
		//Verifying Subscriptions For WooCommerce Plugin under MakeWebBetter
		driver.findElement(By.xpath("//a[normalize-space()='Subscriptions For WooCommerce']")).click();
		System.out.println("Subscriptions For WooCommerce Plugin is present");
		
		// Checking On-boarding form
		try {

			WebElement onboarding = driver.findElement(By.cssSelector(".mwb-sfw-on-boarding-wrapper.mdc-dialog__surface"));
			if(onboarding.isDisplayed() == true) {
				driver.findElement(By.xpath("//input[@value='0-500']")).click();
				driver.findElement(By.id("mwb-sfw-onboard-number")).sendKeys("9058061029");
				driver.findElement(By.cssSelector("input[value='Send Us']")).click();
				System.out.println("Onboarding form present and submitted at: " + dt.format(now));
			}
		}
		catch (Exception e){
			System.out.println("Onboarding form not present");
			//Deactivate plugin
			driver.findElement(By.cssSelector("a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']")).click();
			driver.findElement(By.id("deactivate-subscriptions-for-woocommerce")).click();
		}
		
		// Check Deactivation form
		try {

			WebElement deactivation = driver.findElement(By.cssSelector(".mwb-sfw-on-boarding-wrapper.mdc-dialog__surface"));
			if(deactivation.isDisplayed() == true) {
				driver.findElement(By.xpath("//input[@value='temporary-deactivation-for-debug']")).click();
				driver.findElement(By.xpath("//textarea[@id='mwb-sfw-deactivation-reason-text']")).sendKeys("Chirag Gupta QA");
				driver.findElement(By.cssSelector("input[value='Send Us']")).click();
				//driver.findElement(By.xpath("//a[normalize-space()='Skip and Deactivate Now']")).click();
				System.out.println("Deactivation form present and submitted at: " + dt.format(now));
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
				driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
				System.out.println("Deactivation Success message printed");
				
			}

		}
		catch (Exception e){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
			driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
			System.out.println("Deactivation Success message printed");
			System.out.println("Deactivation form not present");
		}

	}

}
