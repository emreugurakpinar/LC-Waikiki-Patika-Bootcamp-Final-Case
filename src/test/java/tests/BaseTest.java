package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasketPage;
import pages.FavoritesPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.ProductPage;

// Her test için ortak olan methodlar burada bulunur
public class BaseTest {
	// Driver, testler için kullanılacak Page'ler, URL, şifre ve E-mail son olarak Log4j
	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	ProductPage productPage;
	ProductDetailPage productDetailPage;
	BasketPage basketPage;
	FavoritesPage favoritesPage;
	final protected String baseURL = "https://www.lcw.com/";
	final protected String email = "lcwodevi3344@outlook.com";
	final protected String password = "12345lcw";
	protected static final Logger logger = LogManager.getLogger(BaseTest.class);

	// Teste başlamak için gerekli olan configuration'lar
	@BeforeMethod
	public void setupTest() {

		logger.info("Chrome driver başlatılıyor.");
		ChromeOptions cop = new ChromeOptions();
		cop.addArguments("--remote-allow-origins=*");
		cop.addArguments("--disable-notifications");
		// Chrome driver başlatır
		driver = new ChromeDriver(cop);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Sayfayı maximize eder.
		driver.manage().window().maximize();
		// Tüm çerezleri temizler test başında
		driver.manage().deleteAllCookies();
		logger.info("Chrome driver başladı.");
		// kullanacağımız pagelere driver gönderilir ve objeler oluşur.
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		productDetailPage = new ProductDetailPage(driver);
		basketPage = new BasketPage(driver);
		favoritesPage = new FavoritesPage(driver);

	}

	// ExtentReport için SS alır ve rapora attach eder.
	public String takeScreenShotForExtentReports(String testCaseName, WebDriver driver) throws IOException {
		logger.info("Screenshot ExtentReports için alınıyor: {}", testCaseName);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "//ExtentReports//" + testCaseName + ".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	// AllureReport için SS alır ve rapora attach eder.
	public FileInputStream takeScreenShotForAllure(String testCaseName, WebDriver driver) throws IOException {
		logger.info("Screenshot Allure Report için alınıyor: {}", testCaseName);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		return new FileInputStream(source);
	}

	// Test sonunda çalışır ve tarayıcı null değilse tüm pencereleri kapatır.
	@AfterMethod
	public void tearDown() {
		logger.info("Tarayıcı kapanıyor...");
		if (driver != null) {
			driver.quit();
		}
		logger.info("Tarayıcı kapandı.Test tamamlandı");
	}
}
