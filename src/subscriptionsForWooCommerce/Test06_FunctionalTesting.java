package subscriptionsForWooCommerce;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test06_FunctionalTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Functional Testing of Subscription product.
		System.setProperty("webdriver.chrome.driver",
				"/home/cedcoss/MWB Testing Chirag Important/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();

		// Maximize Windows
		driver.manage().window().maximize();

		// Login admin
		driver.get("http://localhost:10013/wp-admin/");
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("root");
		driver.findElement(By.id("wp-submit")).click();
//
//		// Activate plugin
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector(
				"a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']"))
				.click();
		driver.findElement(By.id("activate-subscriptions-for-woocommerce")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("Activation Success message printed");

		Actions actions = new Actions(driver);
		WebElement MakeWebBetter = driver.findElement(By.xpath("//div[normalize-space()='MakeWebBetter']"));
		actions.moveToElement(MakeWebBetter).build().perform();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Subscriptions For WooCommerce']")));

		// Verifying Subscriptions For WooCommerce Plugin under MakeWebBetter
		driver.findElement(By.xpath("//a[normalize-space()='Subscriptions For WooCommerce']")).click();
		System.out.println("Subscriptions For WooCommerce Plugin is present under MakeWebBetter");

		// Verifying General settings availability and basic working.
		driver.findElement(By.id("subscriptions-for-woocommerce-general")).click();

		boolean addtocart = driver.findElement(By.id("mwb_sfw_enable_plugin")).isSelected();
		if (addtocart == false) {
			driver.findElement(By.id("mwb_sfw_enable_plugin")).click();
			driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
			System.out.println("Add To Cart was unchecked and now checked");
			boolean addtocart1 = driver.findElement(By.id("mwb_sfw_enable_plugin")).isSelected();
			System.out.println("true= " + addtocart1);
			boolean success = driver.findElement(By.xpath("//p[normalize-space()='Settings saved !']")).isDisplayed();
			if (success == true) {
				System.out.println("'Settings saved !' message displayed= " + success);
				driver.findElement(By.cssSelector(".notice-dismiss")).click();
				System.out.println("cross button working");
			} else {
				System.out.println("'Settings saved !' message not displayed= " + success);
			}

		} else {
			driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
			System.out.println("Add To Cart is already checked");
			boolean addtocart1 = driver.findElement(By.id("mwb_sfw_enable_plugin")).isSelected();
			System.out.println("true= " + addtocart1);
			boolean success = driver.findElement(By.xpath("//p[normalize-space()='Settings saved !']")).isDisplayed();
			if (success == true) {
				System.out.println("'Settings saved !' message displayed= " + success);
				driver.findElement(By.cssSelector(".notice-dismiss")).click();
				System.out.println("cross button working");
			} else {
				System.out.println("'Settings saved !' message not displayed= " + success);
			}
		}

		driver.findElement(By.cssSelector("#mwb_sfw_add_to_cart_text")).clear();
		driver.findElement(By.cssSelector("#mwb_sfw_add_to_cart_text")).sendKeys("TEST ADD");
		driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
		driver.findElement(By.xpath("//input[@value='TEST ADD']")).isDisplayed();

		driver.findElement(By.cssSelector("#mwb_sfw_place_order_button_text")).clear();
		driver.findElement(By.cssSelector("#mwb_sfw_place_order_button_text")).sendKeys("TEST PLACE ORDER");
		driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@value='TEST PLACE ORDER']")).isDisplayed());

		boolean allowcancel = driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).isSelected();
		if (allowcancel == false) {
			driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).click();
			driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
			System.out.println("Allow cancel subscription was unchecked and now checked");
			boolean allowcancel1 = driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).isSelected();
			System.out.println("true= " + allowcancel1);
			boolean success = driver.findElement(By.xpath("//p[normalize-space()='Settings saved !']")).isDisplayed();
			if (success == true) {
				System.out.println("'Settings saved !' message displayed= " + success);
				driver.findElement(By.cssSelector(".notice-dismiss")).click();
				System.out.println("cross button working");
			} else {
				System.out.println("'Settings saved !' message not displayed= " + success);
			}
		} else {
			driver.findElement(By.xpath("//span[@class='mdc-button__ripple']")).click();
			System.out.println("Allow cancel subscription is checked");
			boolean allowcancel1 = driver.findElement(By.id("mwb_sfw_cancel_subscription_for_customer")).isSelected();
			System.out.println("true= " + allowcancel1);
			boolean success = driver.findElement(By.xpath("//p[normalize-space()='Settings saved !']")).isDisplayed();
			if (success == true) {
				System.out.println("'Settings saved !' message displayed= " + success);
				driver.findElement(By.cssSelector(".notice-dismiss")).click();
				System.out.println("cross button working");
			} else {
				System.out.println("'Settings saved !' message not displayed= " + success);
			}
		}

//		// Create subscription Product1
		driver.findElement(By.xpath("//div[normalize-space()='Products']")).click();
		driver.findElement(By.xpath("//a[@href='post-new.php?post_type=product']")).click();
		driver.findElement(By.xpath("//input[@id='title']")).sendKeys("Subscription product1");
		driver.findElement(By.xpath("//iframe[@id='content_ifr']")).sendKeys(
				"With Subscriptions for WooCommerce, allow the WooCommerce merchants to sell their subscriptions and avail recurring revenue.");
		driver.findElement(By.xpath("//input[@id='_regular_price']")).sendKeys("120");
		driver.findElement(By.xpath("//input[@id='_sale_price']")).sendKeys("100");
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Subscription:']")).isDisplayed());
		driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).isSelected());
		assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).isDisplayed());
		driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).click();

		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_interval']")).click();
		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_interval']")).sendKeys(Keys.ARROW_DOWN,
				Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_free_trial_interval']")).click();
		driver.findElement(By.xpath(
				"//select[@id='mwb_sfw_subscription_free_trial_interval']//option[@value='day'][normalize-space()='Days']"))
				.click();

		// Filling data
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_number']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_expiry_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_expiry_number']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_initial_signup_price']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_initial_signup_price']")).sendKeys("10");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_free_trial_number']")).clear();
		// mwb_sfw_subscription_free_trial_number not filled in this product.

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
		driver.findElement(By.xpath("//input[@id='publish']")).click();
		System.out.println("Subscription Product1 Published");

		driver.get("http://localhost:10013/product/subscription-product1/");
		assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Subscription product1']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='price']")).isDisplayed());
		System.out.println("Subscription Product1 Displayed at store");
		System.out.println(" ");

		// Create subscription Product2
		driver.get("http://localhost:10013/wp-admin/");
		driver.findElement(By.xpath("//div[normalize-space()='Products']")).click();
		driver.findElement(By.xpath("//a[@href='post-new.php?post_type=product']")).click();
		driver.findElement(By.xpath("//input[@id='title']")).sendKeys("Subscription product2");
		driver.findElement(By.xpath("//iframe[@id='content_ifr']")).sendKeys(
				"With Subscriptions for WooCommerce, allow the WooCommerce merchants to sell their subscriptions and avail recurring revenue.");
		driver.findElement(By.xpath("//input[@id='_regular_price']")).sendKeys("120");
		driver.findElement(By.xpath("//input[@id='_sale_price']")).sendKeys("100");
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Subscription:']")).isDisplayed());
		driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).isSelected());
		assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).isDisplayed());
		driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).click();

		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_interval']")).click();
		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_interval']")).sendKeys(Keys.ARROW_DOWN,
				Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_free_trial_interval']")).click();
		driver.findElement(By.xpath(
				"//select[@id='mwb_sfw_subscription_free_trial_interval']//option[@value='day'][normalize-space()='Days']"))
				.click();

		// Filling data
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_number']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_expiry_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_expiry_number']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_initial_signup_price']")).clear();
		// mwb_sfw_subscription_initial_signup_price not filled in this product.
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_free_trial_number']")).clear();
		// mwb_sfw_subscription_free_trial_number not filled in this product.

		js.executeScript("window.scrollBy(0,-1000)");
		driver.findElement(By.xpath("//input[@id='publish']")).click();
		System.out.println("Subscription Product2 Published");

		driver.get("http://localhost:10013/product/subscription-product2/");
		assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Subscription product2']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='price']")).isDisplayed());
		System.out.println("Subscription Product2 Displayed at store");
		System.out.println(" ");

		// Create subscription Product3
		driver.get("http://localhost:10013/wp-admin/");
		driver.findElement(By.xpath("//div[normalize-space()='Products']")).click();
		driver.findElement(By.xpath("//a[@href='post-new.php?post_type=product']")).click();
		driver.findElement(By.xpath("//input[@id='title']")).sendKeys("Subscription product3");
		driver.findElement(By.xpath("//iframe[@id='content_ifr']")).sendKeys(
				"With Subscriptions for WooCommerce, allow the WooCommerce merchants to sell their subscriptions and avail recurring revenue.");
		driver.findElement(By.xpath("//input[@id='_regular_price']")).sendKeys("120");
		driver.findElement(By.xpath("//input[@id='_sale_price']")).sendKeys("100");
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Subscription:']")).isDisplayed());
		driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).isSelected());
		assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).isDisplayed());
		driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).click();

		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_interval']")).click();
		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_interval']")).sendKeys(Keys.ARROW_DOWN,
				Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_free_trial_interval']")).click();
		driver.findElement(By.xpath(
				"//select[@id='mwb_sfw_subscription_free_trial_interval']//option[@value='day'][normalize-space()='Days']"))
				.click();

		// Filling data
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_number']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_expiry_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_expiry_number']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_initial_signup_price']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_initial_signup_price']")).sendKeys("10");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_free_trial_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_free_trial_number']")).sendKeys("1");

		js.executeScript("window.scrollBy(0,-1000)");
		driver.findElement(By.xpath("//input[@id='publish']")).click();
		System.out.println("Subscription Product3 Published");

		driver.get("http://localhost:10013/product/subscription-product3/");
		assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Subscription product3']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='price']")).isDisplayed());
		System.out.println("Subscription Product3 Displayed at store");
		System.out.println(" ");

		// Create subscription Product4
		driver.get("http://localhost:10013/wp-admin/");
		driver.findElement(By.xpath("//div[normalize-space()='Products']")).click();
		driver.findElement(By.xpath("//a[@href='post-new.php?post_type=product']")).click();
		driver.findElement(By.xpath("//input[@id='title']")).sendKeys("Subscription product4");
		driver.findElement(By.xpath("//iframe[@id='content_ifr']")).sendKeys(
				"With Subscriptions for WooCommerce, allow the WooCommerce merchants to sell their subscriptions and avail recurring revenue.");
		driver.findElement(By.xpath("//input[@id='_regular_price']")).sendKeys("120");
		driver.findElement(By.xpath("//input[@id='_sale_price']")).sendKeys("100");
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Subscription:']")).isDisplayed());
		driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).isSelected());
		assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).isDisplayed());
		driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).click();

		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_interval']")).click();
		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_interval']")).sendKeys(Keys.ARROW_DOWN,
				Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElement(By.xpath("//select[@id='mwb_sfw_subscription_free_trial_interval']")).click();
		driver.findElement(By.xpath(
				"//select[@id='mwb_sfw_subscription_free_trial_interval']//option[@value='day'][normalize-space()='Days']"))
				.click();

		// Filling data
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_number']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_expiry_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_expiry_number']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_initial_signup_price']")).clear();
		// mwb_sfw_subscription_initial_signup_price not filled in this product
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_free_trial_number']")).clear();
		driver.findElement(By.xpath("//input[@id='mwb_sfw_subscription_free_trial_number']")).sendKeys("1");

		js.executeScript("window.scrollBy(0,-1000)");
		driver.findElement(By.xpath("//input[@id='publish']")).click();
		System.out.println("Subscription Product1 Published");

		driver.get("http://localhost:10013/product/subscription-product4/");
		assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Subscription product4']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='price']")).isDisplayed());
		System.out.println("Subscription Product4 Displayed at store");
		System.out.println(" ");

		// Deactivate plugin code
		driver.get("http://localhost:10013/wp-admin/plugins.php");
		driver.findElement(By.id("deactivate-subscriptions-for-woocommerce")).click();
		try {

			Actions actions1 = new Actions(driver);
			WebElement deactivation = driver
					.findElement(By.cssSelector(".mwb-sfw-on-boarding-wrapper.mdc-dialog__surface"));
			actions1.moveToElement(deactivation).build().perform();
			js.executeScript("window.scrollBy(0,1000)");
			if (deactivation.isDisplayed() == true) {
				driver.findElement(
						By.xpath("//a[@class='mwb-sfw-deactivation-no_thanks mdc-button mdc-ripple-upgraded']"))
						.click();
				System.out.println("Deactivation form present");
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
				driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
				System.out.println("Deactivation Success message printed");

			}

		} catch (Exception e) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
			driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
			System.out.println("Deactivation Success message printed");
			System.out.println("Deactivation form not present");
		}

		// Close Window
		driver.close();
	}

}
