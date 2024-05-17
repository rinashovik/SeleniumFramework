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

public class Reporting extends TestListenerAdapter{

	
	//ExtentReports extent = ExtentReports("file-path", NetworkMode.OFFLINE);
	
	ExtentReports extent;// = new ExtentReports();

	ExtentSparkReporter sparkReporter;// = new ExtentSparkReporter("Spark.html");
	ExtentTest test;

	//private ExtentTest test;
	
	@BeforeMethod
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.hr.mm.ss").format(new Date());
		sparkReporter = new ExtentSparkReporter(".\\extent-reports\\report.html");
		 //sparkReporter = new ExtentSparkReporter(".\\Extented-Reports\\reports.html");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host Name", "local Host");
		extent.setSystemInfo("Envoronment", "QA");
		extent.setSystemInfo("user", "Rina S.");
		//extent.setSystemInfo(timeStamp, timeStamp);



		
		
	}
	
public void onTestSucess(ITestResult tr) {
	
	test= extent.createTest(tr.getName());
	
	test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		
	}

public void onTestFailure(ITestResult tr) {
	
	test= extent.createTest(tr.getName());	
	test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
	String screenShotPath = System.getProperty("./screenShot.png");
	File fi = new File(screenShotPath);
	if(fi.exists()){
		
		try {
			test.fail("ScreenShot is below: " + test.addScreenCaptureFromPath(screenShotPath));
		}
		catch(Exception e) {
			
			e.getStackTrace();
		}
		
	}
	
}

public void onTestSkipped(ITestResult tr) {
	test= extent.createTest(tr.getName());	
	test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	
}

public void onFinished(ITestContext testContext) {
	
	extent.flush();
	
}

}
