package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
//Her page için kullanacağım methodları burada tanımladım
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	protected JavascriptExecutor jsExecutor;

	// Constructor sayesinde Driver ve gerekli nesneleri başlatıyorum.
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.actions = new Actions(driver);
		this.jsExecutor = (JavascriptExecutor) driver;

	}

	// Element görünür hale gelene kadar bekler
	public void waitForVisibilityOfElementLocated(By findBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	// Element tıklanabilir hale gelene kadar bekler
	public void waitForElementToBeClickable(By findBy) {

		wait.until(ExpectedConditions.elementToBeClickable((findBy)));
	}

	// Element kaybolana kadar bekler
	public void waitForInvisibilityOfElementLocated(By findBy) {

		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	// Seçtiğimiz sayı, elementin içinde görünene kadar bekler
	public void waitForTextToBe(By findBy, String number) {

		wait.until(ExpectedConditions.textToBe(findBy, number));
	}

	// Element üzerine fareyi hareket ettirir
	public void moveToElement(WebElement element) {
		actions.moveToElement(element).perform();
	}

	// Element üzerine fare gelir ve tıklar
	public void moveToElementAndClick(WebElement element) {
		actions.moveToElement(element).click().perform();
	}

	// Seçtiğimiz element görünene kadar kaydırma yapıyor
	public void scrollToElement(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// Yazdığımız pixel'e göre kaydırma işlemi yapıyor
	public void scrollPageUp(int kaydırmaMiktarı) {
		jsExecutor.executeScript("window.scrollTo(0, " + kaydırmaMiktarı + ");");
	}

	// Elementin 'value' attribute değeri belirtilen değeri içermediği durumda
	// bekler
	public void waitForNotAttributeContains(By findBy, String miktar) {
		wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(findBy, "value", miktar)));
	}

	// Elementin 'value' attribute değeri belirtilen değeri içerene kadar bekler
	public void waitForAttributeContains(By findBy, String miktar) {
		wait.until(ExpectedConditions.attributeContains(findBy, "value", miktar));
	}

	// İstediğimiz string'i görene kadar bekler
	public void waitForTextToBePresentInElementLocated(By findBy, String miktar) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(findBy, miktar));
	}

	// Fiyat karşılaştırması her zaman geçsin diye yaptığım işlemler 1000 ve üstü
	// farklı 500-1000 farklı 500 ve altı farklı işlemler içeriyor
	public String fiyatDönüştürme(String value) {
		String urunDegeriIkiKatı = "";
		String[] values = value.replace(",", "").replace(".", "").split(" ");
		if (Double.parseDouble(values[0]) / 100 >= 1000) {
			String[] parts = value.split(",");
			String[] parts2 = parts[1].split(" ");
			urunDegeriIkiKatı = Double
					.toString((Double.parseDouble(parts[0]) * 2) + (Double.parseDouble(parts2[0]) / 100000 * 2));
			System.out.println(urunDegeriIkiKatı);
			urunDegeriIkiKatı = urunDegeriIkiKatı.substring(0, 5);
		}
		if (Double.parseDouble(values[0]) / 100 >= 500 && Double.parseDouble(values[0]) / 100 < 1000) {
			String[] parts = value.split(",");

			String[] parts2 = parts[1].split(" ");

			urunDegeriIkiKatı = Double
					.toString((Double.parseDouble(parts[0]) * 2) + (Double.parseDouble(parts2[0]) / 100 * 2));
			urunDegeriIkiKatı = urunDegeriIkiKatı.substring(0, 4);
			urunDegeriIkiKatı = urunDegeriIkiKatı.substring(0, urunDegeriIkiKatı.length() - 3) + "."
					+ urunDegeriIkiKatı.substring(urunDegeriIkiKatı.length() - 3);
			System.out.println(urunDegeriIkiKatı);
		}
		if (Double.parseDouble(values[0]) / 100 < 500) {

			String[] parts = value.split(",");
			String[] parts2 = parts[1].split(" ");
			urunDegeriIkiKatı = Double
					.toString((Double.parseDouble(parts[0]) * 2) + (Double.parseDouble(parts2[0]) / 100 * 2));
			urunDegeriIkiKatı = urunDegeriIkiKatı.substring(0, 3);
			System.out.println(urunDegeriIkiKatı);

		}
		return urunDegeriIkiKatı;
	}

}
