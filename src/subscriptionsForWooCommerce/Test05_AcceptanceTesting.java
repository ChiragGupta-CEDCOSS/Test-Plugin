package subscriptionsForWooCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

		// Checking Subscription product setting for Simple product
		driver.findElement(By.xpath("//div[normalize-space()='Products']")).click();
		driver.findElement(By.xpath("//a[@href='post-new.php?post_type=product']")).click();
		driver.findElement(By.xpath("//input[@id='title']")).sendKeys("Subscription product");
		driver.findElement(By.xpath("//textarea[@id='content']")).sendKeys(
				"With Subscriptions for WooCommerce, allow the WooCommerce merchants to sell their subscriptions and avail recurring revenue.");
		driver.findElement(By.xpath("//input[@id='_regular_price']")).sendKeys("120");
		driver.findElement(By.xpath("//input[@id='_sale_price']")).sendKeys("100");
		driver.findElement(By.xpath("//label[normalize-space()='Subscription:']")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).click();
		driver.findElement(By.xpath("//input[@id='_mwb_sfw_product']")).isSelected();
		driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).isDisplayed();
		driver.findElement(By.xpath("//span[normalize-space()='Subscription Settings']")).click();

	}

}
