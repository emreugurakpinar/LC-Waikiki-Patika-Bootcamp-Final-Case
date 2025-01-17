package utils;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import tests.BaseTest;

//Listener burada tanımlandı. Listener ile herhangi bir hata olduğunda, test başarılı olduğunda
//test başladığında çalışması için özel tanımlanmış methodlar bulunuyor.
public class Listeners extends BaseTest implements ITestListener {
	// ExtentReport burada kullanılsın diye tanımlandı. Test başarısız olduğunda SS alabilmek için
	ExtentReports extent = ExtentReporter.getReportObject();
	ExtentTest test;

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	@Override
	public void onTestStart(ITestResult result) {
		// test başladığında çalışır ve Extent raporu başlatır
		test = extent.createTest(result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Test başarılı olduğunda passed logu görünür.
		test.log(Status.PASS, "Test Passed!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Test başarısız olduğunda çalışan method
		test.fail(result.getThrowable());
		// AllureReport ve ExtentReport için ScreenShot'lar burada alınır
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			Allure.addAttachment("screenshot", takeScreenShotForAllure(result.getMethod().getMethodName(), driver));
			saveTextLog(result.getMethod().getConstructorOrMethod().getName() + " failed and screenshot taken!");
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = takeScreenShotForExtentReports(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

	@Override
	public void onFinish(ITestContext context) {
		// Test bittiğinde ExtentReport oluşsun diye yazılan kod
		extent.flush();
	}

}
