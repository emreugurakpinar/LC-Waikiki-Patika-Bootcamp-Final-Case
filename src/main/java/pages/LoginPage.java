package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
	WebDriver driver;
	// Ortak driver tanımı super ile
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// WebElement tanımlamaları PageFactory ile
	@FindBy(xpath = "//input[@placeholder='E-posta Adresi veya Telefon Numarası']")
	WebElement userEmail;
	@FindBy(xpath = "//input[@placeholder='Şifreniz']")
	WebElement userPassword;
	@FindBy(xpath = "//button[normalize-space()='Devam Et']")
	WebElement devamEtBtn;
	@FindBy(xpath = "//button[contains(text(),'Giriş Yap')]")
	WebElement girisYapBtn;
	@FindBy(xpath = "//div[@class='spinner']")
	WebElement tıklanmışGirisBtn;
	// Waitler ile kullanmak için By tanımlamaları, waitler element kabul etmiyor
	By sifreAlanıBekleme = By.xpath("//input[@placeholder='Şifreniz']");
	By girisButonuBekleme = By.xpath("//button[@class='login-form__button login-form__button--bg-blue']");
	By tıklanmısGirisBtn = By.xpath("//div[@class='spinner']");
	// E-mail ve şifre girer, sonra giriş yap butonuna tıklar
	public void loginToAccount(String email, String password) {
		userEmail.sendKeys(email);
		devamEtBtn.click();
		waitForVisibilityOfElementLocated(sifreAlanıBekleme);
		userPassword.sendKeys(password);
		waitForElementToBeClickable(girisButonuBekleme);
		girisYapBtn.click();
		waitForVisibilityOfElementLocated(tıklanmısGirisBtn);
	}

}
