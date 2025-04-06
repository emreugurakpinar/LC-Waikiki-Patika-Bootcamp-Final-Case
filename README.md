
# LC Waikiki Selenium Web Otomasyon Projesi

#### PROJE AÇIKLAMASI
Bu proje **Java** ve **Selenium WebDriver** kullanarak **Page Object Model** yapısında, **TestNG Framework**, **ExtentReports**, **Allure Report** ve **Log4j 2** ile desteklenerek geliştirilmiş, **LC Waikiki** websitesinde bir kullanıcının alışveriş sürecini test eder ve belirli bir senaryoyu takip eder. Test senaryosu aşağıda sıralanan adımlarla ilerler.

#### PROJE SENARYOSU 
1. **Driver Başlatılır**: ChromeDriver başlatılır.
2. **Login İşlemi**: Kullanıcı sisteme giriş yapar.
3. **Ürün Kategorisi Seçilir**: ÇOCUK & BEBEK -> KIZ ÇOCUK -> Mont ve Kaban sırasıyla seçilir.
4. **Filtreleme Uygulanır**: Beden olarak 5-6, 6, 6-7 seçilir. Renk olarak Bej seçilir.
5. **En Çok Satanlar Seçilir**: "En Çok Satanlar" en üste gelecek şekilde listelenir.
6. **Ürün Seçimi**: Ürün listesinde 4. sıradaki ürüne tıklanır.
7. **Beden Seçimi**: Bedeni tükenmiş olmayan bir yaş grubu seçilir.
8. **Sepete Ekleme**: Ürün sepete eklenir.
9. **Sepet Doğrulama**: Sepete gidilir ve ürünün doğru bir şekilde eklenip eklenmediği kontrol edilir.
10. **Ürün Sayısı Arttırılır ve Azaltılır**: Sepetteki ürün sayısı önce bir artırılır, ardından bir azaltılır.
11. **Favoriye Ekleme**: Ürün favorilere eklenir.
12. **Favorilere Gitme**: Favoriler sayfasına gidilir ve eklenen ürünün doğruluğu kontrol edilir.
13. **Test Sonlandırılır**: Uygulama test edilip sonlandırılır.




## KURULUM

Bu projeyi yerel bilgisayarınızda çalıştırmak için aşağıdaki adımları takip edebilirsiniz.  

### Gereksinimler  
- **Java 11 veya üstü**: Bu proje Java 11 veya daha yeni bir sürümle çalışmaktadır. [JDK İndir](https://www.oracle.com/tr/java/technologies/downloads/)
- **IDE**: IntelliJ IDEA, Eclipse veya Visual Studio Code gibi Java destekleyen bir IDE kullanmanız tavsiye edilir. [IDE İndir](https://www.eclipse.org/downloads/packages/)

1. **Repository'yi Klonlayın**: Projeyi kendi bilgisayarınıza klonlamak için terminali açın ve şu komutu girin
  

```bash
  git clone https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case.git
```
2. **Projeyi Açın**: Proje clone'landıktan sonra proje klasörüne gidin ve kullandığınız IDE ile proje dosyasını açın.

   <img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/dosyalar.png" alt="Dosyalar" width="200"/>

4. **Bağımlılıkları indirin**: Projeyi başlatmadan önce pom.xml içinde bulunan bağımlılıkları indirin.
   
    <img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/dependencies.gif" width="500" alt="Dependency"/>

5. **Projeyi çalıştırın**: TestNG Suite ile testng.xml üzerinden projeyi başlatın.

   <img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/run.gif" width="500" alt="Run"/>


### Test raporları
Test çalıştıktan sonra **Allure Report** ve **ExtentReports** olarak iki farklı rapor oluşur. Eğer hata sonucu test sonlanmış ise her iki raporda da hatanın gerçekleştiği zamanın **Screenshot**'ı raporların içinde bulunur.

- **Allure Report** için rapor ***/allure-results*** dizininde bulunur. Test sonuçlarını görmek için proje dizini konsol üzerinde açılır ve şu komut yazılır:
```bash
  allure serve allure-results
```
<img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/allurereport.png" alt="AllureReport" width="400"/>

- **ExtentReports** için rapor ***/Extentreports*** dizininde bulunur.

  <img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/extentReport.png" alt="ExtentReport" width="400"/>

### Hata Ayıklama ve Loglama
Projede hata ayıklama ve loglama için **Log4j 2** kullanılmıştır. Testi çalıştırırken logları konsolda görebilir ve test sonunda Loglar, ***logs/application.log*** dizininde yer alır.

<img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/log.png" alt="Log" width="400"/>


## 🔗 Links
- Proje GitHub Sayfası: https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/  

- Maven Repository: https://mvnrepository.com/



## Ek Özellikler

- **ITestListener** ile test sürecinde herhangi bir hata olursa çalışacak olan method sayesinde projede bulunan her iki rapora Screenshot eklenir.
- **IRetryAnalyzer** sayesinde, test koşumu yapılırken herhangi bir nedenden dolayı test başarı ile sonlanmazsa proje otomatik olarak 1 kez daha çalışır.

  <img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/retry.png" alt="Retry" width="400"/>


## Screenshots/Gifs

<img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/4.gif" width="500" alt="Projenin çalışır görüntüsü 4"/>
<img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/5.gif" width="500" alt="Projenin çalışır görüntüsü 5"/>
<img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/1.gif" width="500" alt="Projenin çalışır görüntüsü 1"/>
<img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/3.gif" width="500" alt="Projenin çalışır görüntüsü 3"/>
<img src="https://github.com/Volvox1/LC-Waikiki-Patika-Bootcamp-Final-Case/blob/main/images/2.gif" width="500" alt="Projenin çalışır görüntüsü 2"/>



## Authors
**EMRE UĞUR AKPINAR** - [@emreugurakpinar](https://github.com/emreugurakpinar)

