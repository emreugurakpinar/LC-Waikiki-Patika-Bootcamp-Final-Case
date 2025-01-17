package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends BasePage {

	WebDriver driver;

	// Ortak driver tanımı super ile
	public ProductDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement tanımlamaları PageFactory ile
	@FindBy(xpath = "//h1[@class='product-title']")
	WebElement urunAdı;
	@FindBy(xpath = "//button[normalize-space()='SEPETE EKLE']")
	WebElement sepeteEkleBtn;
	@FindBy(xpath = "\"//span[@class='badge-circle']\"")
	WebElement sepetteUrunSayısı;
	@FindBy(xpath = "//a[@href='https://www.lcw.com/sepetim']//*[name()='svg']")
	WebElement sepetimBtn;
	@FindBy(css = ".evam-first-screenControl.hidden-mobile.evam-first-screen-click.evam-first-screen")
	List<WebElement> siyahEkran;
	// Waitler ile kullanmak için By tanımlamaları, waitler element kabul etmiyor
	By sepettekiUrunSayısı = By.xpath("//span[@class='badge-circle']");

	// İlk olarak 1.yi dener eğer Stok yoksa 2.yi dener.
	// örn: 5-6 yaş stok varsa devam yoksa 6-7 yaş... gibi
	public void stokOlanYasGrubuSec() {
		for (int i = 5; i <= 13; i++) {
			String ageGroup = i + "-" + (i + 1) + " Yaş";
			WebElement ageButton = driver.findElement(By.xpath("//button[@data-label='" + ageGroup + "']"));
			ageButton.click();
			String pageSource = driver.getPageSource();
			String textToFind = "GELİNCE HABER VER";
			if (pageSource.contains(textToFind)) {
			} else {
				break;
			}
		}
	}

	// Ürün adını return eder Assert için
	public String urunAdıAl() {
		return urunAdı.getText();
	}

	// Ürünü sepete ekler
	public void sepeteEkle() {
		sepeteEkleBtn.click();
	}

	// Sepetim'e gitmeyi sağlar
	public void sepeteGit() {
		sepetimBtn.click();
	}

	// Sepetteki ürün sayısını doğrular
	public void sepetDoğrulama() {
		waitForTextToBe(sepettekiUrunSayısı, "1");
	}

	// Bazı ürünlere tıklayınca ekran siyah geliyor
	// Eğer siyah gelirse tıkla geç, siyah gelmezse hiçbir şey yapma devam et.
	public void skipBlackScreen() {
		if (siyahEkran.size() > 0) {
			siyahEkran.get(0).click();
		}
	}

}
