package base;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.AfterSuite;

public class BaseTest {

  public static WebDriver driver;

	public static ExtentReports report;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test;
	public static String filename;
	
  @Parameters("browsername")
  @BeforeSuite
  public void beforeSuite(@Optional("chrome")String browsername) {
	  System.setProperty("webdriver.chrome.driver","D:\\PALLU-QA-SOFTWARE\\chromedriver_win32\\chromedriver.exe");
	  System.setProperty("webdriver.gecko.driver","D:\\PALLU-QA-SOFTWARE\\geckodriver-v0.26.0-win64\\geckodriver.exe" );
	
	  if(browsername.equals("chrome"))
	  {
			driver = new ChromeDriver();		  
	  }
	  else if (browsername.equals("firefox"))
	  {
		  driver=new FirefoxDriver();
	  }
		driver.manage().window().maximize();
		driver.get("http://zero.webappsecurity.com/");
		
    	Date date= new Date();
		long time = date.getTime();
		System.out.println(time);
		Timestamp ts = new Timestamp(time);
		filename = ts.toString().replace('-', '_').replace(' ', '_').replace(':','_').replace('.','_');
		filename = System.getProperty("user.dir")+"\\src\\main\\java\\reports\\"+filename;
		File file = new File(filename);
		file.mkdir();
		
		htmlReporter = new ExtentHtmlReporter(filename+"/TestReport.html");
		htmlReporter.config().setDocumentTitle("Test Report"); 
		htmlReporter.config().setReportName("Zero Bank- Search Scenraio Test"); 
		htmlReporter.config().setTheme(Theme.DARK);	
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		report.setSystemInfo("User Name", "Naveen S");
        report.setSystemInfo("Environment", "Production");
		report.setSystemInfo("OS", "Windows 10");	
  }

  public static String takeScreenShot(WebDriver driver, String screenshotName) throws IOException {
		//Capture the screenshot and return the path of the screenshot.
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = filename+"/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
  
  @AfterMethod
  public void afterMethod(ITestResult result) throws IOException {
	  if(result.getStatus() == ITestResult.FAILURE){
			//MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			//To capture screenshot path and store the path of the screenshot in the string "screenshotpath"
			//Calling takeScreenShot method to take screenshot
			String screenshotpath = takeScreenShot(driver, result.getName());
			//Add it in report using fail method and add screenshot using addScreenCaptureFromPath method
			test.addScreenCaptureFromPath(screenshotpath);
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
  }

  @AfterSuite
  public void afterSuite() {
	  //driver.quit();
	  report.flush();
  }

}
