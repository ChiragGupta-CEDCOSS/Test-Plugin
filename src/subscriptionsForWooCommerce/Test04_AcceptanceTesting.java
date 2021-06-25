package subscriptionsForWooCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test04_AcceptanceTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// To test activation and deactivation of Subscription plugin with other MWB
		// plugins and WooCommerce plugin.
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

		// Deactivate WooCommerce plugin code
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.findElement(By.cssSelector(
				"a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']"))
				.click();
		driver.findElement(By.id("deactivate-woocommerce")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
		System.out.println("WooCommerce deactivated.");
		// Activate Subscription plugin
		driver.findElement(By.id("activate-subscriptions-for-woocommerce")).click();
		try {
			driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
			System.out.println("Subscription plugin activated............error occured.............");
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//p[contains(text(),'WooCommerce is not activated, Please activate WooC')]")));
			driver.findElement(By.xpath("//p[contains(text(),'WooCommerce is not activated, Please activate WooC')]"));
			System.out.println("Subscription plugin not activated ........ Working Fine ........");
		}

		// Activate Woocommerce plugin
		driver.findElement(By.id("activate-woocommerce")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("WooCommerce activated.");

		// Activate Woocommerce Stripe plugin
		driver.findElement(By.id("activate-woocommerce-gateway-stripe")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("WooCommerce Stripe activated.");

		// Activate Subscription plugin
		driver.findElement(By.id("activate-subscriptions-for-woocommerce")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("Subscription Activation Success message printed");
		System.out.println(" ");

		// Activate Event plugin code
		driver.findElement(By.id("activate-event-tickets-manager-for-woocommerce")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.print("Checking on- events plugin");

		// Activate Membership plugin code
		driver.findElement(By.id("activate-membership-for-woocommerce")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.print(", Membership plugin");

		// Activate RMA plugin code
		driver.findElement(By.id("activate-woo-refund-and-exchange-lite")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println(", RMA plugin");

		// Deactivate Subscription plugin code
		driver.findElement(By.id("deactivate-subscriptions-for-woocommerce")).click();
		try {

			Actions actions1 = new Actions(driver);
			WebElement deactivation = driver
					.findElement(By.cssSelector(".mwb-sfw-on-boarding-wrapper.mdc-dialog__surface"));
			actions1.moveToElement(deactivation).build().perform();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			if (deactivation.isDisplayed() == true) {
				driver.findElement(
						By.xpath("//a[@class='mwb-sfw-deactivation-no_thanks mdc-button mdc-ripple-upgraded']"))
						.click();
				System.out.println("Subscription Deactivation form present");
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
				driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
				System.out.println("Subscription Deactivated ........ Working fine ........");
				System.out.println(" ");

			}

		} catch (Exception e) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
			driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
			System.out.println("Subscription Deactivated ........ Working fine ........");
			System.out.println("Subscription Deactivation form not present");
			System.out.println(" ");
		}

		// Activate Subscription plugin
		driver.findElement(By.id("activate-subscriptions-for-woocommerce")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("Subscription Plugin activated:-");

		// Deactivate Event plugin code
		driver.findElement(By.id("deactivate-event-tickets-manager-for-woocommerce")).click();
		try {

			Actions actions1 = new Actions(driver);
			WebElement deactivation = driver
					.findElement(By.cssSelector(".mwb-etmfw-on-boarding-wrapper.mdc-dialog__surface"));
			actions1.moveToElement(deactivation).build().perform();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			if (deactivation.isDisplayed() == true) {
				driver.findElement(By.cssSelector(".mwb-etmfw-deactivation-no_thanks.mdc-button.mdc-ripple-upgraded"))
						.click();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
				driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
				System.out.print("Events Deactivated");

			}

		} catch (Exception e) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
			driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
			System.out.print("Events Deactivated");
		}

		// Deactivate Membership plugin code
		driver.findElement(By.id("deactivate-membership-for-woocommerce")).click();
		try {

			Actions actions1 = new Actions(driver);
			WebElement deactivation = driver.findElement(By.cssSelector(".mwb-on-boarding-wrapper"));
			actions1.moveToElement(deactivation).build().perform();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			if (deactivation.isDisplayed() == true) {
				driver.findElement(By.cssSelector(".mwb-deactivation-no_thanks")).click();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
				driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
				System.out.print(", Membership Deactivated");

			}

		} catch (Exception e) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
			driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
			System.out.print(", Membership Deactivated");
		}

		// Deactivate RMA plugin code
		driver.findElement(By.id("deactivate-woo-refund-and-exchange-lite")).click();
		try {

			Actions actions1 = new Actions(driver);
			WebElement deactivation = driver.findElement(By.cssSelector(".mwb-on-boarding-wrapper"));
			actions1.moveToElement(deactivation).build().perform();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			if (deactivation.isDisplayed() == true) {
				driver.findElement(By.cssSelector(".mwb-deactivation-no_thanks")).click();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
				driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
				System.out.println(", RMA Deactivated");
				System.out.println(" ");

			}

		} catch (Exception e) {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
			driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
			System.out.println(", RMA Deactivated");
			System.out.println(" ");
		}

		// Deactivate WooCommerce Stripe plugin code
		driver.findElement(By.id("deactivate-woocommerce-gateway-stripe")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin deactivated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin deactivated.']"));
		System.out.println("WooCommerce Stripe deactivated.");

		// Deactivate Subscription plugin code
		driver.findElement(By.id("deactivate-subscriptions-for-woocommerce")).click();
		try {

			Actions actions1 = new Actions(driver);
			WebElement deactivation = driver
					.findElement(By.cssSelector(".mwb-sfw-on-boarding-wrapper.mdc-dialog__surface"));
			actions1.moveToElement(deactivation).build().perform();
			JavascriptExecutor js = (JavascriptExecutor) driver;
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
