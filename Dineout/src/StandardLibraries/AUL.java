package StandardLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class AUL {

		public static String getProperty(String path,String key)
		{
			
			String v="";
			
			Properties p=new Properties();
			try {
				p.load(new FileInputStream(path));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			v=p.getProperty(key);
			return v;
		}
		
		
		public static void takePhoto(String folder,String testname,WebDriver driver)
		{
			String datetime=new Date().toString().replaceAll(":","_");
			TakesScreenshot t=(TakesScreenshot) driver;
			File src = t.getScreenshotAs(OutputType.FILE);
	          String desc = folder+testname+datetime+".png";
		try 
		{
			
			FileUtils.copyFile(src, new File(desc));
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
			}
	}

