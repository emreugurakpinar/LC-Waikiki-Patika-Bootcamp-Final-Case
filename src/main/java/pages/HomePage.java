package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	WebDriver driver;

	// Ortak driver tanımı super ile
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement tanımlamaları PageFactory ile
	@FindBy(xpath = "//button[@id='cookieseal-banner-accept']")
	WebElement cerezKabulBtn;
	@FindBy(id = "user_1_")
	WebElement loginSayfasınaGitBtn;
	@FindBy(xpath = "//a[normalize-space()='ÇOCUK & BEBEK']")
	WebElement cocukVeBebekBtn;
	@FindBy(xpath = "//span[normalize-space()='KIZ ÇOCUK']")
	WebElement kızCocukBtn;
	@FindBy(xpath = "//section[contains(@class,'content-tab')]//a[contains(@class,'')][normalize-space()='Mont ve Kaban']")
	WebElement montVeKabanBtn;
	// Waitler ile kullanmak için By tanımlamaları, waitler element kabul etmiyor
	By cerezBtnBekle = By.xpath("//button[@id='cookieseal-banner-accept']");
	By cocukVeBebekBtnBekleme = By.xpath("//a[normalize-space()='ÇOCUK & BEBEK']");
	By kızCocukBtnBekleme = By.xpath("//span[normalize-space()='KIZ ÇOCUK']");
	By montVeKabanBtnBekleme = By.xpath(
			"//section[contains(@class,'content-tab')]//a[contains(@class,'')][normalize-space()='Mont ve Kaban']");

	// Test başında çerezlere izin ver butonuna tıklar
	public void cerezKabulEt() {
		scrollPageUp(0);
		waitForVisibilityOfElementLocated(cerezBtnBekle);
		waitForElementToBeClickable(cerezBtnBekle);
		cerezKabulBtn.click();
		waitForInvisibilityOfElementLocated(cerezBtnBekle);
	}

	// Login sayfasına gider
	public void loginSayfasınaGit() {
		loginSayfasınaGitBtn.click();
	}

	// Kategorilerden mont ve kabanı bulur ve tıklar
	public void kategoriSecim() {
		waitForVisibilityOfElementLocated(cocukVeBebekBtnBekleme);
		moveToElement(cocukVeBebekBtn);
		waitForVisibilityOfElementLocated(kızCocukBtnBekleme);
		moveToElement(kızCocukBtn);
		waitForVisibilityOfElementLocated(montVeKabanBtnBekleme);
		montVeKabanBtn.click();
	}

	// LCW sitesini açar navigate.to() ile
	public void goToLcwPage(String baseURL) {
		driver.navigate().to(baseURL);
	}
}
