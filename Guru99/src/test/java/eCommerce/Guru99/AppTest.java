package eCommerce.Guru99;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		//url = "http://live.techpanda.org/";
		url = "http://live.techpanda.org/index.php/mobile.html";
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
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
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
