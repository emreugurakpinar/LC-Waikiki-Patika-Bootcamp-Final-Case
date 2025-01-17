package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketPage extends BasePage {

	WebDriver driver;

	// driver ortak olsun diye super kullandım
	public BasketPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement tanımlamaları PageFactory ile
	@FindBy(xpath = "//span[@class='rd-cart-item-color']/strong")
	WebElement urunRengi;
	@FindBy(xpath = "//span[@class='rd-cart-item-code']")
	WebElement urunTipi;
	@FindBy(xpath = "//span[@class='rd-cart-item-price mb-15']")
	WebElement urunTutarSepet;
	@FindBy(css = "div[class='price-info-area'] span[class='total-grand-box-amount']")
	WebElement urunTutarToplam;
	@FindBy(xpath = "//i[@class='fa fa-heart-o']")
	WebElement favoriyeEkleBtn;
	@FindBy(xpath = "//span[text()='Favorilerim']")
	WebElement favorilerimeGitBtn;
	@FindBy(xpath = "//input[@value='1']")
	WebElement urunAdedi;
	@FindBy(xpath = "(//a[normalize-space()='+'])[1]")
	WebElement urunAdediBirArtır;
	@FindBy(xpath = "(//a[normalize-space()='-'])[1]")
	WebElement urunAdediBirAzalt;
	private String urunDegeri;
	// Waitler ile kullanmak için By tanımlamaları, waitler element kabul etmiyor
	By favoriBtnDegisimi = By.cssSelector("i.fa.fa-heart");
	By favoriBtn = By.xpath("//span[text()='Favorilerim']");
	By urunAdediArtırBtn = By.xpath("(//a[normalize-space()='+'])[1]");
	By favoriyeEkleBtnDeneme = By.xpath("//i[@class='fa fa-heart-o']");
	By waitFavoriyeEkleBtn = By.xpath("//i[@class='fa fa-heart-o']");
	By waitIcinUrunDeğeriAl = By.cssSelector(".rd-cart-item-price.mb-15");

	// Ürünün rengini return ediyor Assert için
	public String urunRengi() {
		return urunRengi.getText();
	}

	// Ürün mont mu kaban mı onu return ediyor Assert için
	public String urunTipi() {
		return urunTipi.getText();
	}

	// Sepette yazan ürün tutarını return eder Assert için
	public String getUrunTutarSepet() {
		return urunTutarSepet.getText();
	}

	// Ödeme adımındaki ürün tutarını return eder Assert için
	public String getUrunTutarToplam() {
		urunDegeri = urunTutarToplam.getText();
		return urunTutarToplam.getText();

	}

	// Ürünü favorilere ekler
	public void favoriyeEkle() {
		waitForVisibilityOfElementLocated(waitFavoriyeEkleBtn);
		waitForElementToBeClickable(waitFavoriyeEkleBtn);
		moveToElementAndClick(favoriyeEkleBtn);
		waitForVisibilityOfElementLocated(favoriBtnDegisimi);
	}

	// Favoriler ekranına gitmemizi sağlar
	public void favorilerimeGit() {
		waitForElementToBeClickable(favoriBtn);
		favorilerimeGitBtn.click();
	}

	// Sepette ürün adedini return eder Assert için
	@SuppressWarnings("deprecation")
	public String urunAdedi() {
		return urunAdedi.getAttribute("value");
	}

	// Sepette ürünü 1 artırır
	public void urunAdetBirArtır() {
		waitForVisibilityOfElementLocated(urunAdediArtırBtn);
		urunAdediBirArtır.click();
		waitForTextToBePresentInElementLocated(waitIcinUrunDeğeriAl, fiyatDönüştürme(urunDegeri));
	}

	// Sepette ürünü 1 azaltır
	public void urunAdetBirAzalt() {
		urunAdediBirAzalt.click();
		waitForTextToBePresentInElementLocated(waitIcinUrunDeğeriAl, urunDegeri);
	}

}
