package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FavoritesPage extends BasePage {

	WebDriver driver;

	// Ortak driver tanımı super ile
	public FavoritesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement tanımlamaları PageFactory ile
	@FindBy(xpath = "//h5[@class='product-card__title']")
	WebElement urunAdı;

	// Favoriler sayfasındaki ürünün adını return eder Assert için
	public String getUrunAdı() {
		return urunAdı.getText();
	}

	// Sayfayı aşağı kaydırır
	public void scrollDown() {
		scrollPageUp(150);
	}
}
