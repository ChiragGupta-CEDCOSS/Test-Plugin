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

public class Test03_AcceptanceTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Acceptance testing of Subscription Table.
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

		// Activate plugin
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

		// Verifying availability of Subscriptions table, Bulk actions, search box, and
		// table headings.
		driver.findElement(By.xpath("//a[normalize-space()='Subscription Table']")).click();
		assertTrue(driver.findElement(By.xpath("//h3[normalize-space()='Subscriptions']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//p[@class='search-box']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[@class='tablenav top']")).isDisplayed());

		int s_indicator = driver.findElements(By.xpath("//span[@class='sorting-indicator']")).size();
		System.out.println(s_indicator + " Sorting Indicators are used");

		assertTrue(driver.findElement(By.xpath(
				"//table[@class='wp-list-table widefat fixed striped table-view-list makewebbetter_page_subscriptions_for_woocommerce_menu']"))
				.isDisplayed());
		System.out.println("Pass");
		assertTrue(driver.findElement(By.xpath("//th[@id='subscription_id']//span[contains(text(),'Subscription ID')]"))
				.isDisplayed());
		System.out.println("Pass1");
		assertTrue(driver.findElement(By.xpath("//th[@id='parent_order_id']//span[contains(text(),'Parent Order ID')]"))
				.isDisplayed());
		System.out.println("Pass2");
		assertTrue(driver.findElement(By.xpath("//th[@id='status']//span[contains(text(),'Status')]")).isDisplayed());
		System.out.println("Pass3");
		assertTrue(driver.findElement(By.xpath("//th[@id='product_name']")).isDisplayed());
		System.out.println("Pass4");
		assertTrue(driver.findElement(By.xpath("//th[@id='recurring_amount']")).isDisplayed());
		System.out.println("Pass5");
		assertTrue(driver.findElement(By.xpath("//th[@id='user_name']")).isDisplayed());
		System.out.println("Pass6");
		assertTrue(driver.findElement(By.xpath("//th[@id='next_payment_date']")).isDisplayed());
		System.out.println("Pass7");
		assertTrue(driver.findElement(By.xpath("//th[@id='subscriptions_expiry_date']")).isDisplayed());
		System.out.println("Pass8");

		assertTrue(driver.findElement(By.xpath("//tfoot//span[contains(text(),'Subscription ID')]")).isDisplayed());
		System.out.println("Pass9");
		assertTrue(driver.findElement(By.xpath("//tfoot//span[contains(text(),'Parent Order ID')]")).isDisplayed());
		System.out.println("Pass10");
		assertTrue(driver.findElement(By.xpath("//tfoot//span[contains(text(),'Status')]")).isDisplayed());
		System.out.println("Pass11");
		assertTrue(driver
				.findElement(By.xpath(
						"//tfoot//th[@class='manage-column column-product_name'][normalize-space()='Product Name']"))
				.isDisplayed());
		System.out.println("Pass12");
		assertTrue(driver.findElement(By.xpath(
				"//tfoot//th[@class='manage-column column-recurring_amount'][normalize-space()='Recurring Amount']"))
				.isDisplayed());
		System.out.println("Pass13");
		assertTrue(driver
				.findElement(
						By.xpath("//tfoot//th[@class='manage-column column-user_name'][normalize-space()='User Name']"))
				.isDisplayed());
		System.out.println("Pass14");
		assertTrue(driver.findElement(By.xpath(
				"//tfoot//th[@class='manage-column column-next_payment_date'][normalize-space()='Next Payment Date']"))
				.isDisplayed());
		System.out.println("Pass15");
		assertTrue(driver.findElement(By.xpath(
				"//tfoot//th[@class='manage-column column-subscriptions_expiry_date'][normalize-space()='Subscription Expiry Date']"))
				.isDisplayed());
		System.out.println("Pass16");
		assertTrue(driver.findElement(By.xpath("//div[@class='tablenav bottom']")).isDisplayed());
		System.out.println("Pass17");

		// Deactivate plugin code
		driver.findElement(By.cssSelector(
				"a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']"))
				.click();
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
