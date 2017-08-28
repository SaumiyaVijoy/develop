package Questions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Question1Frames {


	WebDriver driver;
	
	public Question1Frames(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void launchUrl(){
		
		driver.navigate().to("http://way2automation.com/way2auto_jquery/frames-and-windows.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
	}
	
	public void initialRegistration() throws Exception {
		
		Thread.sleep(10000);
		
		driver.findElement(By.name("name")).sendKeys("Cathy");
		driver.findElement(By.name("phone")).sendKeys("1234567890");
		driver.findElement(By.name("email")).sendKeys("cathy@abc.com");
		Select country=new Select(driver.findElement(By.name("country")));
		country.selectByVisibleText("Austria");
		driver.findElement(By.name("city")).sendKeys("Vienna");
		driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/form/fieldset[6]/input")).sendKeys("Cathy");
		driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/form/fieldset[7]/input")).sendKeys("Cathy12");
		driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/form/div/div[2]/input")).click();
		
	}
	
	public void signIn(){
		
		driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/form/div/div[1]/p/a")).click();
		driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/form/fieldset[1]/input")).sendKeys("Cathy");
		driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/form/fieldset[2]/input")).sendKeys("Cathy12");
		driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/form/div/div[2]/input")).click();
		
	}
	public String newTabCheck() throws Exception{
		
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	  	jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("html/body/section/div[2]/div[2]/div[3]/ul/li/a/figure")).click();
		driver.findElement(By.xpath("html/body/section/div/div[1]/div[1]/ul/li[1]/a")).click();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@src='frames-windows/defult1.html']")));
		driver.findElement(By.xpath("html/body/div/p/a")).click();
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		String titleNewTab=driver.getTitle();
		Assert.assertEquals(titleNewTab, "jQuery UI Datepicker - Default functionality");
		driver.close();
		driver.switchTo().window(windowHandles.get(0));
		return driver.getTitle();	
		
	}
	
	public String newWindowCheck() throws Exception{
		
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	  	jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("html/body/section/div[2]/div[2]/div[3]/ul/li/a/figure")).click();
		driver.findElement(By.xpath("html/body/section/div/div[1]/div[1]/ul/li[2]/a")).click();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@src='frames-windows/defult2.html']")));
		String parentHandle = driver.getWindowHandle(); // get the current window handle
		driver.findElement(By.xpath("html/body/div/p/a")).click(); // click some link that opens a new window
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		String titleNewWindow=driver.getTitle();
		Assert.assertEquals(titleNewWindow, "Open New Seprate Window");
		driver.close(); // close newly opened window when done with it
		driver.switchTo().window(parentHandle); 
		return driver.getTitle();
		
	}
	
	/*public String[] frameSetCheck() throws Exception{
		
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	  	jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("html/body/section/div[2]/div[2]/div[3]/ul/li/a/figure")).click();
		driver.findElement(By.xpath("html/body/section/div/div[1]/div[1]/ul/li[3]/a")).click();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@src='frames-windows/defult3.html']")));
		driver.findElement(By.xpath("html/body/div/p/a")).click();
		Thread.sleep(10000);
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		String titleNewTab=driver.getTitle();
		Assert.assertEquals(titleNewTab, "HTML Frames - Example 1");
		
		
		driver.switchTo().frame("topFrame");
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//frame[@src='frame2.html']"))));
		//xpath(".//frame[@src='frame2.html']")
		//.name("topFrame")
		
		WebElement frame=driver.findElement(By.xpath(".//frame[@src='frame2.html']"));
		String colorTop = frame.getCssValue("background-color");
		System.out.println(colorTop);
		colorTop = frame.getAttribute("background-color");
		System.out.println(colorTop);
		colorTop = frame.getCssValue("background");
		System.out.println(colorTop);
		colorTop = frame.getAttribute("background");
		System.out.println(colorTop);
		colorTop = frame.getCssValue("color");
		System.out.println(colorTop);
		//http://way2automation.com/way2auto_jquery/frames-windows/frame2.html
		colorTop = frame.getAttribute("style");
		System.out.println(colorTop);
		
		Thread.sleep(10000);
		//String hexTop = Color.fromString(colorTop).asHex();
		System.out.println(colorTop);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//frame[@src='frame3.html']"))));
		//driver.switchTo().frame("contentFrame");
		String colorBottom = driver.findElement(By.name("contentFrame")).getCssValue("color");
		String hexBottom = Color.fromString(colorBottom).asHex();
		System.out.println(hexBottom);
		return new String[] {colorTop, hexBottom};
		
	}*/
	
	
	public void frameSetCheck() throws Exception{
		
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	  	jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("html/body/section/div[2]/div[2]/div[3]/ul/li/a/figure")).click();
		driver.findElement(By.xpath("html/body/section/div/div[1]/div[1]/ul/li[3]/a")).click();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@src='frames-windows/defult3.html']")));
		driver.findElement(By.xpath("html/body/div/p/a")).click();
		Thread.sleep(10000);
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		String titleNewTab=driver.getTitle();
		Assert.assertEquals(titleNewTab, "HTML Frames - Example 1");
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//frame[@src='frame2.html']"))));
		
		driver.switchTo().frame("topFrame");
		//WebElement frame1=driver.findElement(By.xpath("html/body/h2"));
		String content=driver.findElement(By.xpath("html/body/h2")).getText();
		System.out.println(content);
		
		WebElement styleValue=driver.findElement(By.xpath("/html/body"));
		/*String colorTop = frame1.getCssValue("background-color");
		System.out.println("6"+colorTop);
		colorTop = frame1.getCssValue("background");
		System.out.println("7"+colorTop);
		colorTop = frame1.getAttribute("background");
		System.out.println("8"+colorTop);
		colorTop = frame1.getAttribute("style");
		System.out.println("9"+colorTop);
		colorTop = frame1.getCssValue("style");
		System.out.println("1"+colorTop);*/
		String colorTop = styleValue.getCssValue("background");
		System.out.println("2"+colorTop);
		colorTop = styleValue.getAttribute("background");
		System.out.println("3"+colorTop);
		
	/*	String hexTop = Color.fromString(colorTop).asHex();
		System.out.println(hexTop);*/
	
	}
	public String multiWindowCheck() throws Exception{
		
		Thread.sleep(10000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	  	jse.executeScript("window.scrollBy(0,700)", "");
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("html/body/section/div[2]/div[2]/div[3]/ul/li/a/figure")).click();
		driver.findElement(By.xpath("html/body/section/div/div[1]/div[1]/ul/li[4]/a")).click();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@src='frames-windows/defult4.html']")));
		
		driver.findElement(By.xpath("html/body/div/p/a")).click();
		Thread.sleep(10000);
		
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); 
		}
		
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(2));
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("html/body/div/p/a"))));
		String content=driver.findElement(By.xpath("html/body/div/p/a")).getText();
		return content;
		
	}	
		
		
}
