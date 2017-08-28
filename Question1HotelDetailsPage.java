package Questions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class Question1HotelDetailsPage {
	
	WebDriver driver;
	
	public Question1HotelDetailsPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void imageCarouselCheck() throws Exception{
		
		Thread.sleep(10000);
		driver.findElement(By.xpath("html/body/div[4]/div/div[2]/div[1]/div[3]/div[2]/div/div[1]/div[3]")).click();
	    //carousel image done with mouse hover
		Actions action = new Actions(driver);
        for(int i=2;i<14;i++){
			
			 Thread.sleep(4000);
			 WebElement element=driver.findElement(By.xpath("html/body/div[4]/div/div[2]/div[1]/div[3]/div[2]/div/div[1]/div[3]"));
			 WebElement arrow=driver.findElement(By.xpath("html/body/div[4]/div/div[2]/div[1]/div[3]/div[2]/div/div[1]/div[3]"));
			 action.moveToElement(element);
			 action.moveToElement(arrow);
			 action.click().build().perform();
		} 
	}

	
	public void currencyChangeCheck() throws Exception{	
		
		Select currency=new Select(driver.findElement(By.id("currency")));
		currency.selectByVisibleText("INR");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	  	jse.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(15000);
		WebElement value=driver.findElement(By.xpath("html/body/div[4]/div/div[2]/section/form[1]/div/div[2]/div[2]/h4/span/span[1]/b/small"));
		String verifyCurrency=value.getText();
		if(!verifyCurrency.equalsIgnoreCase("INR")){
			Assert.fail("Currency not changed!");
		}
	}
	
	public void languageChangeCheck() throws Exception{
		
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[1]/a")).click();
		driver.findElement(By.id("pt")).click();
		Thread.sleep(9000);
		String ptDescription=driver.findElement(By.xpath("html/body/div[4]/div/div[1]/div[1]/div[2]/div/p[1]")).getText();
		if(!ptDescription.equalsIgnoreCase("Descrição")){
			Assert.fail("Language not changed!");
		}
		
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[1]/a")).click();
		driver.findElement(By.id("en")).click();
		String enDescription=driver.findElement(By.xpath("html/body/div[4]/div/div[1]/div[1]/div[2]/div/p[1]")).getText();
		if(!enDescription.equalsIgnoreCase("Description")){
			Assert.fail("Language not changed!");
		}
	}
	
	public String bookHotelCheck() throws Exception{
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	  	jse.executeScript("window.scrollBy(0,750)", "");
		Thread.sleep(15000);
		driver.findElement(By.xpath("html/body/div[4]/div/div[2]/section/form[1]/div/div[2]/div[1]/button")).click();
		Thread.sleep(9000);
		String ActTitle = driver.getTitle();
		Assert.assertEquals(ActTitle, "Hyatt Regency Perth");
		driver.findElement(By.id("guesttab")).click();
	  	Thread.sleep(12000);
		driver.findElement(By.name("firstname")).sendKeys("Albert");
		driver.findElement(By.name("lastname")).sendKeys("thomas");
		driver.findElement(By.name("email")).sendKeys("albert@abc.com");
		driver.findElement(By.name("confirmemail")).sendKeys("albert@abc.com");
		driver.findElement(By.name("phone")).sendKeys("123456789");
		driver.findElement(By.name("address")).sendKeys("Kochi");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div[1]/div/div[2]/div[2]/div[2]/div/div[3]/div[1]/form/div[8]/div/div/a/span[2]")).click();
		driver.findElement(By.xpath("html/body/div[8]/ul/li[79]/div")).click();
		driver.findElement(By.name("guest")).click();
		Thread.sleep(9000);
		String unpaid=driver.findElement(By.xpath("html/body/div[3]/div/div[1]/div/center")).getText();
	  	if(!unpaid.equalsIgnoreCase("unpaid"))
	  		Assert.fail("Not Booked!");
	  	
	  	return driver.getTitle();
		
	}
}
