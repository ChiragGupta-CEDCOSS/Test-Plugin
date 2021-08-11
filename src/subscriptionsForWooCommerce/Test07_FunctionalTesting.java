package subscriptionsForWooCommerce;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test07_FunctionalTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Functional Testing of Subscription order.
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
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		String strUrl = driver.getCurrentUrl();
		driver.findElement(By.cssSelector(
				"a[class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-plugins'] div[class='wp-menu-name']"))
				.click();
		driver.findElement(By.id("activate-subscriptions-for-woocommerce")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Plugin activated.']")));
		driver.findElement(By.xpath("//p[normalize-space()='Plugin activated.']"));
		System.out.println("Activation Success message printed");

		// Place order for Subscription products
		for (int i = 0; i < 4; i++) {
			int k = i + 1;
			driver.get("http://localhost:10013/product/subscription-product" + k + "/");
			driver.findElement(By.xpath("//button[normalize-space()='TEST ADD']")).click();
			driver.findElement(
					By.xpath("//div[@role='alert']//a[@class='button wc-forward'][normalize-space()='View cart']"))
					.click();
			System.out.println("Subscription product" + k + " order:" + driver
					.findElement(By.xpath("//a[normalize-space()='Subscription product" + k + "']")).isDisplayed());
			driver.get("http://localhost:10013/cart/");
			js1.executeScript("window.scrollBy(0,1000)");
			driver.findElement(By.xpath("//a[normalize-space()='Proceed to checkout']")).click();
			System.out.println("..........Current Url is: " + strUrl);

			driver.findElement(By.cssSelector("#billing_first_name")).clear();
			driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("Chirag");
			driver.findElement(By.cssSelector("#billing_last_name")).clear();
			driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("Gupta");

			driver.findElement(By.cssSelector("#select2-billing_country-container")).click();
			driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("India");
			driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[2]")).click();

			driver.findElement(By.cssSelector("#billing_address_1")).clear();
			driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("Street address");
			driver.findElement(By.cssSelector("#billing_city")).clear();
			driver.findElement(By.cssSelector("#billing_city")).sendKeys("LUCKNOW");

			driver.findElement(By.cssSelector("#select2-billing_state-container")).click();
			driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("Uttar Pradesh");
			driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li")).click();

			driver.findElement(By.cssSelector("#billing_postcode")).clear();
			driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("226001");
			driver.findElement(By.cssSelector("#billing_phone")).clear();
			driver.findElement(By.cssSelector("#billing_phone")).sendKeys("2233223322");
			driver.findElement(By.cssSelector("#billing_email")).clear();
			driver.findElement(By.cssSelector("#billing_email")).sendKeys("chiraggupta@makewebbetter.com");

			driver.findElement(By.xpath("//label[normalize-space()='Credit Card (Stripe)']")).click();

			try {

				driver.findElement(By.xpath("//input[@id='terms']")).isDisplayed();
				driver.findElement(By.xpath("//input[@id='terms']")).click();
				driver.findElement(By.xpath("//button[normalize-space()='TEST PLACE ORDER']")).click();

			} catch (Exception e) {
				driver.findElement(By.xpath("//button[normalize-space()='TEST PLACE ORDER']")).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']")));
			String OrderID = driver
					.findElement(By.cssSelector("li[class='woocommerce-order-overview__order order'] strong"))
					.getText();
			System.out.println("Order ID is:" + OrderID);

			// Testing Subscription details
			driver.get(
					"http://localhost:10013/wp-admin/admin.php?page=subscriptions_for_woocommerce_menu&sfw_tab=subscriptions-for-woocommerce-subscriptions-table");

			driver.findElement(By.xpath("//input[@id='mwb-sfw-order-search-input']")).clear();
			driver.findElement(By.xpath("//input[@id='mwb-sfw-order-search-input']")).sendKeys(OrderID);
			driver.findElement(By.xpath("//input[@id='search-submit']")).click();

			String childElement = driver.findElement(By.xpath("//a[normalize-space()='" + OrderID + "']/../.."))
					.getText();
			System.out.println(childElement);
			String subsID = driver.findElement(By.xpath("//a[normalize-space()='" + OrderID + "']/../../td[1]"))
					.getText();
			int i4 = Integer.parseInt(OrderID);
			int j4 = i4 + 1;
			System.out.println("Subscription ID is " + j4 + ": " + subsID);
			String status = driver.findElement(By.xpath("//a[normalize-space()='" + OrderID + "']/../../td[3]"))
					.getText();
			System.out.println("Subscription Status is active: " + status);
			String productname = driver.findElement(By.xpath("//a[normalize-space()='" + OrderID + "']/../../td[4]"))
					.getText();
			System.out.println("Product name is Subscription product" + k + ": " + productname);
		}

		// Place order for Subscription product1 and validating availability of payment
		// gateways
		driver.get("http://localhost:10013/product/subscription-product1/");
		driver.findElement(By.xpath("//button[normalize-space()='TEST ADD']")).click();
		driver.findElement(
				By.xpath("//div[@role='alert']//a[@class='button wc-forward'][normalize-space()='View cart']")).click();
		System.out.println("Subscription product1 order:"
				+ driver.findElement(By.xpath("//a[normalize-space()='Subscription product1']")).isDisplayed());
		driver.get("http://localhost:10013/product/subscription-product2/");
		driver.findElement(By.xpath("//button[normalize-space()='TEST ADD']")).click();
		assertTrue(driver
				.findElement(By.xpath("//li[contains(text(),'You can not add multiple subscription product in c')]"))
				.isDisplayed());
		driver.get("http://localhost:10013/cart/");
		js1.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("//a[normalize-space()='Proceed to checkout']")).click();
		System.out.println("..........Current Url is: " + strUrl);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='PayPal acceptance mark']")));
		WebElement paypal_p = driver.findElement(By.xpath("//img[@alt='PayPal acceptance mark']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(paypal_p).click().perform();
		System.out.println("Clicked");

		System.out.println("Paypal available:"
				+ driver.findElement(By.xpath("//button[normalize-space()='Proceed to PayPal']")).isDisplayed());
		driver.findElement(By.xpath("//label[normalize-space()='Credit Card (Stripe)']")).click();
		System.out.println("Place order text displayed with stripe: "
				+ driver.findElement(By.xpath("//button[normalize-space()='TEST PLACE ORDER']")).isDisplayed());

		// Checking Manual payment gateways are not available.
		try {
			assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Direct bank transfer']")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Check payments']")).isDisplayed());
			assertTrue(driver.findElement(By.xpath("//label[normalize-space()='Cash on delivery']")).isDisplayed());
			System.out.println("*********Test Fail: Manual payment gateways are available********");

		} catch (Exception e) {
			System.out.println("Pass: Manual payment gateways are not available");
		}
		driver.get("http://localhost:10013/cart/");
		driver.findElement(By.xpath("//a[normalize-space()='×']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-empty.woocommerce-info")));
		assertTrue(driver.findElement(By.cssSelector(".cart-empty.woocommerce-info")).isDisplayed());
		driver.findElement(By.xpath("//a[normalize-space()='Undo?']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js1.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath("//a[normalize-space()='Proceed to checkout']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js1.executeScript("window.scrollBy(0,1200)");
		WebElement paypal_p1 = driver.findElement(By.xpath("//img[@alt='PayPal acceptance mark']"));
		actions.moveToElement(paypal_p1).click().perform();
		try {

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@id='terms']")).isDisplayed();
			driver.findElement(By.xpath("//input[@id='terms']")).click();
			driver.findElement(By.xpath("//button[normalize-space()='Proceed to PayPal']")).click();

		} catch (Exception e) {
			driver.findElement(By.xpath("//button[normalize-space()='Proceed to PayPal']")).click();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js1.executeScript("window.scrollBy(0,1200)");
		driver.findElement(By.xpath("//div[@id='content']//a[@id='cancelLink']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(
				"http://localhost:10013/wp-admin/admin.php?page=subscriptions_for_woocommerce_menu&sfw_tab=subscriptions-for-woocommerce-subscriptions-table");
		String statusP = driver.findElement(By.xpath("//*[@id=\"the-list\"]/tr[1]/td[3]")).getText();
		System.out.println("Subscription Status is pending: " + statusP);

		// Deactivate plugin code
		driver.get("http://localhost:10013/wp-admin/plugins.php");
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
