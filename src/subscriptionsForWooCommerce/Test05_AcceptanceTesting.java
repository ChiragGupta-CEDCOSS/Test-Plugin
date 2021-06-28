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

public class Test05_AcceptanceTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub\

		// Acceptance testing of Subscription product settings.
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

		// Activate Subscription plugin
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector(
				"a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']"))
				.click();
		driver.findElement(By.id("activate-subscriptions-for-woocommerce")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("Subscription Activation Success message printed");
		System.out.println(" ");

		// Checking Subscription product setting for Simple product
		driver.findElement(By.xpath("//div[normalize-space()='Products']")).click();
		driver.findElement(By.xpath("//a[@href='post-new.php?post_type=product']")).click();
		driver.findElement(By.xpath("//input[@id='title']")).sendKeys("Subscription product");
		driver.findElement(By.xpath("//textarea[@id='content']")).sendKeys(
				"With Subscriptions for WooCommerce, allow the WooCommerce merchants to sell their subscriptions and avail recurring revenue.");
		driver.findElement(By.xpath("//input[@id='_regular_price']")).sendKeys("120");
		driver.findElement(By.xpath("//input[@id='_sale_price']")).sendKeys("100");
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Subscription:']")).isDisplayed());
		driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).click();
		assertTrue(driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).isSelected());
		assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).isDisplayed());
		driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).click();
		assertTrue(
				driver.findElement(By.xpath("//label[normalize-space()='Subscriptions Per Interval']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Subscriptions Expiry Interval']"))
				.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Initial Signup fee($)']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Free trial interval']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath(
				"//span[@data-tip='Choose the subscriptions time interval for the product \"for example 10 days\"']"))
				.isDisplayed());
		assertTrue(driver.findElement(By.xpath(
				"//span[@data-tip='Choose the subscriptions expiry time interval for the product \"leave empty for unlimited\"']"))
				.isDisplayed());
		assertTrue(driver.findElement(By.xpath(
				"//span[@data-tip='Choose the subscriptions initial fee for the product \"leave empty for no initial fee\"']"))
				.isDisplayed());
		assertTrue(driver.findElement(By.xpath(
				"//span[@data-tip='Choose the trial period for subscription \"leave empty for no trial period\"']"))
				.isDisplayed());
		System.out.println("All settings and labels are present with help-tip.");

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

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
		driver.findElement(By.xpath("//input[@id='publish']")).click();
		System.out.println("Subscription Product Published");

		driver.get("http://localhost:10013/product/subscription-product/");
		assertTrue(driver.findElement(By.xpath("//h1[normalize-space()='Subscription product']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='price']")).isDisplayed());
		System.out.println("Subscription Product Displayed at store");
		System.out.println(" ");

		// Deactivate Subscription plugin code
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
				System.out.println("Subscription Deactivation form present");
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
				driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
				System.out.println("Subscription Deactivation Success message printed");

			}

		} catch (Exception e) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
			driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
			System.out.println("Subscription Deactivation Success message printed");
			System.out.println("Subscription Deactivation form not present");
		}

		// Close Window
		driver.close();
	}

}
