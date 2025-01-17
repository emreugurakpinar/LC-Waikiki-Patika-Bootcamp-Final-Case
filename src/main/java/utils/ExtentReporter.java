package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	// ExtentReport için configuration'lar
	public static ExtentReports getReportObject() {
		//Raporun kaydedileceği path
		String path = System.getProperty("user.dir") + "\\ExtentReports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Sonuçları");
		reporter.config().setReportName("Web Otomasyon Sonuçları");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Emre Uğur Akpınar");
		extent.createTest(path);
		return extent;
	}
}
