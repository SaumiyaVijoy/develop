package Questions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Question1HomePage {
	
	WebDriver driver;
	
	public Question1HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void launchUrl(){
		
		driver.navigate().to("http://www.phptravels.net/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
	}
	public void locationCheck(){
		
	    driver.findElement(By.xpath("//*[@id='s2id_autogen3']/a/span[@class='select2-chosen']")).click();
	    driver.findElement(By.xpath("html/body/div[@id='select2-drop']/div/input[contains(@class, 'select2-input') and contains(@class, 'select2-focused')]")).sendKeys("dub");
	    List<WebElement> autoSuggest = driver.findElements(By.xpath("//*[@id='select2-drop']/ul/li/ul[@class='select2-result-sub']/li"));
	    // print the auto suggest
	    for (WebElement a : autoSuggest){
	        System.out.println("Values are = " + a.getText());
	        if(!a.getText().toLowerCase().contains("dub"))
	        	Assert.fail("Values in the list do not match the typed text!!");
	    }
	    driver.findElement(By.xpath("//*[@id='s2id_autogen3']/a/span[@class='select2-chosen']")).click();
	}
	
	public void inOutPrefillCheck(){
		
		//check-in 
		String chkin_date= driver.findElement(By.name("checkin")).getAttribute("value");
		System.out.println("Check-inDate "+chkin_date);
		if(chkin_date.isEmpty())
			Assert.fail("Value not found in the check-in textbox!!");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		String currentDate = dateFormat.format(calendar.getTime());
		System.out.println("CurrentDate "+currentDate);
		if(!currentDate.equals(chkin_date))
			Assert.fail("The prefilled check-in date is not system date!!");
		//check-out
		String chkout_date= driver.findElement(By.name("checkout")).getAttribute("value");
		System.out.println("Check-outDate "+chkout_date);
		if(chkout_date.isEmpty())
			Assert.fail("Value not found in the check-out textbox!!");
		 calendar.add(Calendar.DAY_OF_YEAR, 1);
		 String nextDay = dateFormat.format(calendar.getTime());
		 System.out.println("Next Day "+nextDay);
		if(!nextDay.equals(chkout_date))
			Assert.fail("The prefilled check-out date is not system date+1!!");
		
	}
	
	public void inOutCheck() throws Exception{
		
		driver.findElement(By.name("checkin")).clear();
		driver.findElement(By.name("checkin")).sendKeys("23/09/2017");
		Thread.sleep(5000);
		String checkInDate=driver.findElement(By.xpath("html/body/div[4]/div[3]/div/div[1]/div[1]/div/form/div[2]/div/input")).getAttribute("value");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = new GregorianCalendar(2017,8,23);
		String enteredDate = dateFormat.format(calendar.getTime());
		System.out.println("EnteredDate "+enteredDate);
		if(!checkInDate.equals(enteredDate))
			Assert.fail("Entered Date not entered!");
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		String nextDay = dateFormat.format(calendar.getTime());
		System.out.println("Next Day "+nextDay);
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.name("adults"))).click().perform();
		driver.findElement(By.name("checkout")).clear();
		driver.findElement(By.name("checkout")).sendKeys("24/09/2017");
		Thread.sleep(5000);
		WebElement value= driver.findElement(By.xpath("html/body/div[4]/div[3]/div/div[1]/div[1]/div/form/div[3]/div/input"));
		String chkout_date=value.getAttribute("value");
		value.click();
		System.out.println("Check-outDate "+chkout_date);
		if(!nextDay.equals(chkout_date))
			Assert.fail("The check-out date is not automatically changed to one day more!!");
		 
	}
	
	public void locationSearchCheck() throws Exception{
		
		driver.findElement(By.xpath("//*[@id='s2id_autogen3']/a/span[@class='select2-chosen']")).click();
		driver.findElement(By.xpath("html/body/div[@id='select2-drop']/div/input[contains(@class, 'select2-input') and contains(@class, 'select2-focused')]")).clear();
		//driver.findElement(By.xpath("//*[@id='s2id_autogen3']/a/span[@class='select2-chosen']")).click();
	    driver.findElement(By.xpath("html/body/div[@id='select2-drop']/div/input[contains(@class, 'select2-input') and contains(@class, 'select2-focused')]")).sendKeys("dubai");
	    driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li/ul[@class='select2-result-sub']/li/div[@class='select2-result-label']")).click();
	    Thread.sleep(9000);
	}

	
	public String overallFieldCheck() throws Exception{
		
	 	driver.findElement(By.xpath("//*[@id='s2id_autogen3']/a/span[@class='select2-chosen']")).click();
	   // driver.findElement(By.xpath("html/body/div[@id='select2-drop']/div/input[@class='select2-input' or @class='firepath-matching-node']")).sendKeys("dubai");
	    
	    //[contains(@class, 'class1') and contains(@class, 'class2')]
	    driver.findElement(By.xpath("html/body/div[@id='select2-drop']/div/input[contains(@class, 'select2-input') and contains(@class, 'select2-focused')]")).sendKeys("dubai");
	    
	    
	    driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li/ul[@class='select2-result-sub']/li/div[@class='select2-result-label']")).click();
	    driver.findElement(By.name("checkin")).clear();
	    driver.findElement(By.name("checkin")).sendKeys("23/09/2017");
	    driver.findElement(By.name("checkout")).clear();
	    driver.findElement(By.name("checkout")).sendKeys("08/10/2017");
	    Select noOfAdlt=new Select(driver.findElement(By.id("adults")));
	    noOfAdlt.selectByVisibleText("2");
	    Select noOfCdrn=new Select(driver.findElement(By.id("child")));
	    noOfCdrn.selectByVisibleText("2");
	    driver.findElement(By.xpath("html/body/div[4]/div[3]/div/div[1]/div[1]/div/form/div[6]/div/button")).click();
	    Thread.sleep(9000);
	   /* String summary=driver.findElement(By.xpath("html/body/div[3]/div/div[2]/span")).getText();
	    	if(!summary.equals("Dubai , United Arab Emirates"))
	    		Assert.fail("Text not equal!!");
	    */	
	    return driver.getTitle();
	}
		

}
