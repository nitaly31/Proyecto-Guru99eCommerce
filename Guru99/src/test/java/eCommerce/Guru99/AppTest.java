package eCommerce.Guru99;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
	private WebDriver driver;
	public String url;
	public int scc = 0;

	@BeforeTest
	public void setUp() {

		driver = new FirefoxDriver();

		url = "http://live.techpanda.org/";
		// url = "http://live.techpanda.org/index.php/mobile.html";

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
	}

	@Test(priority = 0)
	public void verifyTitleHome() {

		String titleHome = "THIS IS DEMO SITE FOR   ";
		String titleHomePage = driver.findElement(By.tagName("h2")).getText();

		Assert.assertEquals(titleHomePage, titleHome);
	}

	@Test(priority = 1)
	public void navegateMobilePage() {
		driver.findElement(By.linkText("MOBILE")).click();
	}

	@Test(priority = 2)
	public void verifyTitleMobile() {
		String titleMobile = "MOBILE";
		String titleMobilePage = driver.findElement(By.tagName("h1")).getText();

		Assert.assertEquals(titleMobilePage, titleMobile);
	}

	@Test(priority = 3)
	public void selectProductByName() {
		new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");
	}

	@Test(priority = 4)
	public void sortForProductName() throws Exception {
		String EVIDENCE_PATH = "..\\Guru99\\evidence\\";
		scc = (scc + 1);

		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String png = (EVIDENCE_PATH + scc + ".png");
		FileUtils.copyFile(screen, new File(png));
	}

	@Test(priority = 5)
	public void VerifyCostOfProduct() {
		String XPeriaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
		driver.findElement(By.id("product-collection-image-1")).click();

		String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();

		Assert.assertEquals(XPeriaPrice, detailPrice);
	}

	@Test(priority = 6)
	public void errorVerification() {
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/form[1]/div[4]/div[1]/div[1]/div[2]/button[1]"))
				.click();

		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input[1]")).clear();
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input[1]")).sendKeys("1000");
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[4]/button[1]"))
				.click();

	}

	@Test(priority = 7)
	public void errorVerificationTxt() {
		String txtError = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[2]/p[1]"))
				.getText();
		String msgError = "* The maximum quantity allowed for purchase is 500.";

		Assert.assertEquals(txtError, msgError);
	}
	@Test(priority = 8)
	public void  VerificationEmptyCart() {
		driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]")).click();
		
		// Verifica que el carrito esté vacío
		String txtEmpty = driver.findElement(By.xpath("//h1[contains(text(),'Shopping Cart is Empty')]")).getText();
		String msgEmpty = "SHOPPING CART IS EMPTY";
		
		Assert.assertEquals(txtEmpty, msgEmpty);
		
	}

	@AfterTest
	public void close() {
		 driver.close();
	}
}
