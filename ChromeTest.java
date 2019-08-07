package FirstTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver","E:/Software/chromedriver_win32/chromedriver.exe");
		WebDriver dr= new ChromeDriver();
		dr.manage().window().maximize();
		dr.navigate().to("https://www.ing.com/Home.htm");
		List<WebElement>li = dr.findElements(By.xpath("//img[@alt='ING Logo']"));
		int count= li.size();
		System.out.println("size of log elements are: "+count);
		highlight(dr,li.get(0));
		WebElement lnk1= dr.findElement(By.xpath("//a[@class='search-toggle' and @title='Search ING.com']"));
		highlight(dr,lnk1);
	}

	public static void highlight(WebDriver wdr, WebElement ele ) throws Exception
	{
		JavascriptExecutor js=(JavascriptExecutor) wdr;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border:2px solid red;');", ele);
		try
		{
			Thread.sleep(1000);
			
		}
		catch (InterruptedException e)
		{
			System.out.println(e.getMessage());
			
		}
		GetScreenShot(wdr);
		//js.executeScript("arguments[0].setAttribute('style','border:solid 2px red');", ele);
		js.executeScript("arguments[0].setAttribute('style','border:solid 2px white');", ele);
		
	}
	
	public static String GetScreenShot(WebDriver dr) throws Exception 
	{
		try 
		{
		File screenshot = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		if(screenshot.exists())
		{
			String screenshotfilename = GetCurrentDateTimeStamp()+".png";
			FileUtils.copyFile(screenshot, new File("TestLogs\\"+screenshotfilename));
			
			return screenshotfilename;	
		}
		else
		{
			return "Window Closed";
		}
		}catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception:Unable to capture the screen shot");
			return "Window Closed";
		}
	}
	
	public static String GetCurrentDateTimeStamp() throws Exception
	{
        
       	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        return (sdf.format(cal.getTime()));                     
              
    }
}
