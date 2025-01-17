package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {
	WebDriver driver;

	// Ortak driver tanımı super ile
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement tanımlamaları PageFactory ile
	@FindBy(xpath = "//div[normalize-space()='Beden']")
	WebElement bedenFiltre;
	@FindBy(xpath = "//span[@class='filter-option__text' and text()='5-6 Yaş']")
	WebElement bedenBesVeAltıYas;
	@FindBy(xpath = "//span[@class='filter-option__text' and text()='6 Yaş']")
	WebElement bedenAltıYas;
	@FindBy(xpath = "//span[@class='filter-option__text' and text()='6-7 Yaş']")
	WebElement bedenAltıVeYediYas;
	@FindBy(xpath = "//img[@src='https://img-lcwaikiki.mncdn.com//resource/images/icon/bej.png']")
	WebElement renkFiltreBej;
	@FindBy(xpath = "//button[@class='dropdown-button__button']")
	WebElement dropDownBtn;
	@FindBy(xpath = "//a[normalize-space()='En çok satanlar']")
	WebElement enCokSatanlarBtn;
	@FindBy(css = ".product-card.product-card--one-of-4")
	List<WebElement> urunler;
	// Waitler ile kullanmak için By tanımlamaları, waitler element kabul etmiyor
	By sayfaYukleniyor = By.cssSelector(".product-grid product-grid--fetching");
	By bedenBesVeAltıYasBekleme = By.xpath("//span[@class='filter-option__text' and text()='5-6 Yaş']");

	// Renk filtrelemesi yapar Bej için
	public void renkFiltrele() {
		renkFiltreBej.click();
		waitForInvisibilityOfElementLocated(sayfaYukleniyor);
	}

	// En çok satanları seçer
	public void enCokSatanlaraGit() {
		dropDownBtn.click();
		enCokSatanlarBtn.click();
	}

	// İndex ile sayfadaki 4.ürünü seçer
	public void sayfadakiDorduncuUrunuSec(int urunIndex) {
		urunler.get(urunIndex).click();
	}

	// Beden filtre kısmını görene kadar scroll yapar
	public void scrollDown() {
		scrollToElement(bedenFiltre);
		scrollToElement(bedenFiltre);
	}

	// 5-6,6,6-7 yaş için beden seçimi yapar
	public void bedenSecim() {

		waitForVisibilityOfElementLocated(bedenBesVeAltıYasBekleme);
		bedenBesVeAltıYas.click();
		waitForInvisibilityOfElementLocated(sayfaYukleniyor);
		bedenAltıYas.click();
		waitForInvisibilityOfElementLocated(sayfaYukleniyor);
		bedenAltıVeYediYas.click();
		waitForInvisibilityOfElementLocated(sayfaYukleniyor);
	}

	// Sayfanın en üstüne scroll yapar
	public void scrollToTop() {
		scrollPageUp(0);
	}

	// Sayfayı biraz aşağı kaydırır
	public void altaKaydırma() {
		scrollPageUp(250);
	}

	// Sayfadaki yüklenme animasyonunu bekler
	public void sayfayıBekle() {
		waitForInvisibilityOfElementLocated(sayfaYukleniyor);
	}
}
