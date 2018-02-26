package StandardLibraries;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest implements IAutoConst{

	public static WebDriver driver;
	static
	{
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		//System.setProperty(GECKO_KEY, GECKO_VALUE);
	}
	ExtentReports report;
	ExtentTest test;

	@BeforeSuite
	public void init()
	{
		String path="./ExtentReport/myreport.html";
		report=new ExtentReports(path);
	}


	@BeforeMethod
	public void openapp(Method testname){
		
		String name=testname.getName();
		test = report.startTest(name);
		driver=new ChromeDriver();
		String AUT = AUL.getProperty(SETTING_PATH, "AUT");
		driver.get(AUT);
		String STITO = AUL.getProperty(SETTING_PATH, "ITO");
		long ITO = Long.parseLong(STITO);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		

	}	

	@AfterMethod
	public void closeapp(ITestResult res)
	{

		String testname = res.getTestName();
		int status=res.getStatus();
//		if(status==2)
//		{
//			AUL.takePhoto(PHOTO_PATH, testname, driver);
//		}
		if(status==1)
		{
			test.log(LogStatus.PASS, "pass");
		}
		else
		{
			test.log(LogStatus.FAIL, "pass");
			AUL.takePhoto(PHOTO_PATH, testname, driver);
		}
		report.endTest(test);

		driver.quit();
	}
	
	@AfterSuite
	public void end()
	{
		report.flush();
	}
}

