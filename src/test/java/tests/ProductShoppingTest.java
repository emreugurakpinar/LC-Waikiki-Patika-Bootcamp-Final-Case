package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.BasketPage;
import pages.FavoritesPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.ProductPage;
import utils.Retry;

@Epic("Kullanıcı Ürün Alışveriş Süreci")
@Feature("Login, Ürün Arama ve Sepet/Favori İşlemleri ")
public class ProductShoppingTest extends BaseTest {
	private String urunAdı;

	// Verilen senaryoya uygun test burada koşuluyor
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Description: Ürün araması yaparak, filtreleme seçenekleri ile arama sonuçlarını daraltabilmesi, ürünleri sepete ekleyebilmesi ve favorilere ekleyebilmesi")
	@Test(retryAnalyzer = Retry.class, priority = 1, description = "Lcw web sitesi ürün filtreleme,sepete ekleme, favorilere ekleme testi")
	@Story("Çocuk kaban sepete eklenir, doğruluğu kontrol edilir ardından favoriye eklenir ve test sonlanır")
	public void urunAramaSepeteVeFavorilereEkleTesti() {
		// Testi küçük parçalara böldüm kolay yönetilebilsin diye
		logger.info("Test başlıyor...");
		lcwAnaSayfasinaGitVeÇerezleriKabulEt(homePage);
		girisVeUrunFiltrelemesiYap(homePage, loginPage, productPage);
		urunuSepeteEkleSepetiDogrulaVeSepeteGit(productPage, productDetailPage);
		sepettekiÜrünüDoğrulaVeFavorilereGonder(favoritesPage, basketPage);
		logger.info("Test başarı ile sonlandı.");

	}

	// Testin login sayfasına gitme ve çerezleri kabul etmeye kadarki kısım
	private void lcwAnaSayfasinaGitVeÇerezleriKabulEt(HomePage homePage) {
		logger.info("LCW sitesine gidiliyor.");
		homePage.goToLcwPage(baseURL);
		homePage.cerezKabulEt();
	}

	// Giriş yapılıp ürün filtrelemeye kadarki kısım
	private void girisVeUrunFiltrelemesiYap(HomePage homePage, LoginPage loginPage, ProductPage productPage) {
		logger.info("Login sayfasına gidiliyor.");
		homePage.loginSayfasınaGit();
		logger.info("Giriş yapılıyor.");
		loginPage.loginToAccount(email, password);
		homePage.goToLcwPage(baseURL);
		logger.info("Kategori seçimi yapılıyor.");
		homePage.kategoriSecim();
		productPage.scrollDown();
		logger.info("Filtreleme yapılıyor.");
		productPage.bedenSecim();
		productPage.renkFiltrele();
		productPage.scrollToTop();
	}

	// En çok satanlardaki 4.Ürünün seçildiği, sepete eklendiği ve sepete gidilen kısım
	private void urunuSepeteEkleSepetiDogrulaVeSepeteGit(ProductPage productPage, ProductDetailPage productDetailPage) {
		productPage.enCokSatanlaraGit();
		productPage.altaKaydırma();
		productPage.sayfayıBekle();
		productPage.sayfadakiDorduncuUrunuSec(3);
		logger.info("Sayfadaki 4. ürün seçildi.");
		productDetailPage.skipBlackScreen();
		productPage.altaKaydırma();
		productDetailPage.stokOlanYasGrubuSec();
		logger.info("Stokta olan herhangi bir yaş grubu seçildi.");
		urunAdı = productDetailPage.urunAdıAl();
		productDetailPage.sepeteEkle();
		logger.info("Ürün sepete eklendi.");
		productDetailPage.sepetDoğrulama();
		productDetailPage.sepeteGit();
	}

	// Sepette istenilen Assertler yapılır, 1 artır 1 eksilt işlemi sonrası favoriye eklenir
	// Favorilerde kontrol yapıldıktan sonra test sonlanır
	private void sepettekiÜrünüDoğrulaVeFavorilereGonder(FavoritesPage favoritesPage, BasketPage basketPage) {
		logger.info("Ürün adedinin 1 olduğu kontrol ediliyor.");
		//Sepetteki ürün sayısı kontrolü
		Assert.assertEquals(basketPage.urunAdedi(), "1");
		//Sepetteki ürünün renk kontrolü
		Assert.assertTrue(basketPage.urunRengi().contains("Bej"));
		logger.info("Renk kontrolü yapıldı.");
		//Sepetteki ürünün isim kontrolü
		Assert.assertTrue(basketPage.urunTipi().contains("Mont") || basketPage.urunTipi().contains("Kaban"));
		logger.info("Ürünün isim kontrolü yapıldı.");
		//Sepetteki ve ödeme kısmındaki fiyat eşitliği kontrolü
		Assert.assertEquals(basketPage.getUrunTutarSepet(), basketPage.getUrunTutarToplam());
		logger.info("Ürünün tutarı doğrulandı.");
		basketPage.urunAdetBirArtır();
		logger.info("Ürün adedi 1 arttı.");
		basketPage.urunAdetBirAzalt();
		logger.info("Ürün adedi 1 azaldı.");
		basketPage.favoriyeEkle();
		logger.info("Ürün favoriye eklendi.");
		basketPage.favorilerimeGit();
		favoritesPage.scrollDown();
		//Favorilerde ürünün doğru olduğunun kontrolü
		Assert.assertTrue(urunAdı.contains(favoritesPage.getUrunAdı()));
		logger.info("Ürünün adının doğrulaması yapıldı.");
	}

}
