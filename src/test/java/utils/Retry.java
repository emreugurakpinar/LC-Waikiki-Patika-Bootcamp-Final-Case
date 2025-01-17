package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import tests.BaseTest;

//Test başarısız olursa belirlediğimiz sayı kadar testi yeniden koşmamızı sağlıyor.
public class Retry extends BaseTest implements IRetryAnalyzer {
	int count = 0;
    int maxDenemeSayısı = 1; //Test başarısız olursa 1 kere daha çalışsın dedim.

    @Override
    public boolean retry(ITestResult result) {
        if(count < maxDenemeSayısı) {
            // Testi yeniden çalıştırmadan önce mevcut driver'ı kapatıyoruz
            if (driver != null) {
                driver.quit();
            }
           
            
            count++;
            return true;  // Testi yeniden çalıştırıyoruz
        }
        return false;  // Yeniden denemek istemiyoruz
    }

}
