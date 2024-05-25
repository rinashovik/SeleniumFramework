package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{

	
	//ExtentReports extent = ExtentReports("file-path", NetworkMode.OFFLINE);
	
	ExtentReports extent;// = new ExtentReports();

	ExtentSparkReporter sparkReporter;// = new ExtentSparkReporter("Spark.html");
	ExtentTest test;

	//private ExtentTest test;
	
	//@BeforeMethod
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		
//		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
//
//		repName = "Test-Report-"+"timeStamp"+".html";
//		sparkReporter = new ExtentSparkReporter(".\\extent-reports\\"+repName);		
		sparkReporter = new ExtentSparkReporter(".\\reports\\Test-Report-"+"timeStamp"+".html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
		sparkReporter.config().setReportName("Regrassion Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent.setSystemInfo("Application", "Guro99 Bank Application");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Host Name", "local Host");
		extent.setSystemInfo("Envoronment", "QA");
		extent.setSystemInfo("User", "Rina S.");
		extent.setSystemInfo("TimeStamp", timeStamp);		
		
	}

	
public void onTestSuccess(ITestResult result) {

	test = extent.createTest(result.getName());

	test.assignCategory(result.getMethod().getGroups());
	test.createNode(result.getName());
	//test.log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
	test.log(Status.PASS,"Test Passed");
	
	}

public void onTestFailure(ITestResult result) {
	
	test = extent.createTest(result.getName());
	test.createNode(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.FAIL,"Test Failed");
	test.log(Status.FAIL, result.getThrowable().getMessage());
	test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

	
	//String screenShotPath = System.getProperty(".//screenShot.png");
	String screenShotPath = (".//screenShot.png");
	File fi = new File(screenShotPath);
	if(fi.exists()){
		
		try{
			test.fail("ScreenShot is below: " + test.addScreenCaptureFromPath(screenShotPath));
		}
		catch(Exception e) {
			
			e.getStackTrace();
		}
		
	}
	
}

public void onTestSkipped(ITestResult result) {
	test = extent.createTest(result.getName());
	test.createNode(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP, "Test Skipped");
	test.log(Status.SKIP, result.getThrowable().getMessage());

}

public void onFinish(ITestContext testContext) {
	
	extent.flush();
	
}

}
