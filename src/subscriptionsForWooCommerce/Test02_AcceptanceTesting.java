package subscriptionsForWooCommerce;


import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test02_AcceptanceTesting {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// Acceptance Testing of General Setting and availability of all navy menus
		System.setProperty("webdriver.chrome.driver", "/home/cedcoss/MWB Testing Chirag Important/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		
		// Maximize Windows
		driver.manage().window().maximize();
		
		
		// Login admin
		driver.get("http://localhost:10003/wp-admin/");
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("root");
		driver.findElement(By.id("wp-submit")).click();
		
		
		// Activate plugin
		WebDriverWait wait = new WebDriverWait(driver,30);
		driver.findElement(By.cssSelector("a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']")).click();
		driver.findElement(By.id("activate-subscriptions-for-woocommerce")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("Activation Success message printed");
		
		Actions actions = new Actions(driver);
		WebElement MakeWebBetter = driver.findElement(By.xpath("//div[normalize-space()='MakeWebBetter']"));
		actions.moveToElement(MakeWebBetter).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Subscriptions For WooCommerce']")));
		
		
		//Verifying Subscriptions For WooCommerce Plugin under MakeWebBetter
		driver.findElement(By.xpath("//a[normalize-space()='Subscriptions For WooCommerce']")).click();
		System.out.println("Subscriptions For WooCommerce Plugin is present under MakeWebBetter");
		
		
		// Verifying availability of text, support link, and setting tabs.
		assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='SUBSCRIPTIONS FOR WOOCOMMERCE']")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//a[@class='mwb-link']")).isDisplayed());
		
		assertTrue(driver.findElement(By.id("subscriptions-for-woocommerce-overview")).isDisplayed());
		
		assertTrue(driver.findElement(By.id("subscriptions-for-woocommerce-general")).isDisplayed());
		
		assertTrue(driver.findElement(By.id("subscriptions-for-woocommerce-subscriptions-table")).isDisplayed());
		
		assertTrue(driver.findElement(By.id("subscriptions-for-woocommerce-system-status")).isDisplayed());

		
		// Verifying General settings availability and basic working.
		driver.findElement(By.id("subscriptions-for-woocommerce-general")).click();

		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Enable/Disable Subscription']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Check this box to enable the subscription.']")).isDisplayed());
		boolean addtocart = driver.findElement(By.id("mwb_sfw_enable_plugin")).isSelected();
		if(addtocart == false) {
			driver.findElement(By.id("mwb_sfw_enable_plugin")).click();
			driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
			System.out.println("Add To Cart was unchecked and now checked");
			boolean addtocart1 = driver.findElement(By.id("mwb_sfw_enable_plugin")).isSelected();
			System.out.println("true= "+addtocart1);
			boolean success = driver.findElement(By.xpath("//p[normalize-space()='Settings saved !']")).isDisplayed();
			if(success == true) {
				System.out.println("'Settings saved !' message displayed= "+success);
				driver.findElement(By.cssSelector(".notice-dismiss")).click();
				System.out.println("cross button working");
			} else {
				System.out.println("'Settings saved !' message not displayed= "+success);
			}
			
		} else {
			driver.findElement(By.id("mwb_sfw_enable_plugin")).click();
			driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
			System.out.println("Add To Cart was checked and now unchecked");
			boolean addtocart1 = driver.findElement(By.id("mwb_sfw_enable_plugin")).isSelected();
			System.out.println("false= "+addtocart1);
			boolean success = driver.findElement(By.xpath("//p[normalize-space()='Settings saved !']")).isDisplayed();
			if(success == true) {
				System.out.println("'Settings saved !' message displayed= "+success);
				driver.findElement(By.cssSelector(".notice-dismiss")).click();
				System.out.println("cross button working");
			} else {
				System.out.println("'Settings saved !' message not displayed= "+success);
			}
		}
		
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Add to cart text']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//input[@placeholder='Add to cart button text']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Use this option to change add to cart button text.')]")).isDisplayed());
		driver.findElement(By.cssSelector("#mwb_sfw_add_to_cart_text")).clear();
		driver.findElement(By.cssSelector("#mwb_sfw_add_to_cart_text")).sendKeys("TEST ADD");
		driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
		driver.findElement(By.xpath("//input[@value='TEST ADD']")).isDisplayed();
		
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Place order text']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//input[@placeholder='Place order button text']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Use this option to change place order button text.')]")).isDisplayed());
		driver.findElement(By.cssSelector("#mwb_sfw_place_order_button_text")).clear();
		driver.findElement(By.cssSelector("#mwb_sfw_place_order_button_text")).sendKeys("TEST PLACE ORDER");
		driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@value='TEST PLACE ORDER']")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Allow Customer to cancel Subscription']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Enable this option to allow the customer to cancel')]")).isDisplayed());
		boolean allowcancel = driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).isSelected();
		if(allowcancel == false) {
			driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).click();
			driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
			System.out.println("Allow cancel subscription was unchecked and now checked");
			boolean allowcancel1 = driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).isSelected();
			System.out.println("true= "+allowcancel1);
			boolean success = driver.findElement(By.xpath("//p[normalize-space()='Settings saved !']")).isDisplayed();
			if(success == true) {
				System.out.println("'Settings saved !' message displayed= "+success);
				driver.findElement(By.cssSelector(".notice-dismiss")).click();
				System.out.println("cross button working");
			} else {
				System.out.println("'Settings saved !' message not displayed= "+success);
			}
		} else {
			driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).click();
			driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
			System.out.println("Allow cancel subscription checked and now unchecked");
			boolean allowcancel1 = driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).isSelected();
			System.out.println("false= "+allowcancel1);
			boolean success = driver.findElement(By.xpath("//p[normalize-space()='Settings saved !']")).isDisplayed();
			if(success == true) {
				System.out.println("'Settings saved !' message displayed= "+success);
				driver.findElement(By.cssSelector(".notice-dismiss")).click();
				System.out.println("cross button working");
			} else {
				System.out.println("'Settings saved !' message not displayed= "+success);
			}
		}

		
		//Deactivate plugin code
		driver.findElement(By.cssSelector("a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']")).click();
		driver.findElement(By.id("deactivate-subscriptions-for-woocommerce")).click();
		try {

			Actions actions1 = new Actions(driver);
			WebElement deactivation = driver.findElement(By.cssSelector(".mwb-sfw-on-boarding-wrapper.mdc-dialog__surface"));
			actions1.moveToElement(deactivation).build().perform();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			if(deactivation.isDisplayed() == true) {
				driver.findElement(By.xpath("//a[normalize-space()='Skip and Deactivate Now']")).click();
				System.out.println("Deactivation form present");
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
