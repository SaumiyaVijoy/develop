package Questions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Question1ResultsPage {

		WebDriver driver;
		
		public Question1ResultsPage(WebDriver driver)
		{
			this.driver=driver;
		}
	
		public void filterCheck() throws Exception{
		
			JavascriptExecutor jse = (JavascriptExecutor)driver;
		  	jse.executeScript("window.scrollBy(0,500)", "");
		  	Thread.sleep(9000);
		  	//star grade
		    driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[3]/div[1]/div/div[3]/div/ins")).click();	
		    //----slider----price range
		    int  x=10;
		    WebElement slider = driver.findElement(By.xpath("//div[@id='collapse2']/div/div/div/div[@class='slider-selection']"));
		    int width=slider.getSize().getWidth();
		    Actions move = new Actions(driver);
		    move.moveToElement(slider, ((width*x)/100), 0).click();
		    move.build().perform();
		    System.out.println("Slider moved");
		    jse.executeScript("window.scrollBy(0,500)", "");
		    //property types
		    driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[5]/div[1]/div[6]/div/ins")).click();
		    driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[5]/div[1]/div[14]/div/ins")).click();
		    //amenities
		    driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[6]/div[1]/div[10]/div/ins")).click();
		    driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[6]/div[1]/div[14]/div/ins")).click();
		    driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[6]/div[1]/div[30]/div/ins")).click();
		    driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[6]/div[1]/div[36]/div/ins")).click();
		    //search
		    driver.findElement(By.id("searchform")).click();
		    //result page
		    String result= driver.findElement(By.xpath("//div[@class='itemscontainer']/h1[@class='text-center']")).getText(); 
		   	if(!result.equals("No Results!!"))
		   		Assert.fail("Invalid Result.");
		    Thread.sleep(5000);
		}
		public void whishlistCheck() throws Exception{
			
			driver.findElement(By.id("38")).click();
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			String ActTitle = driver.getTitle();
			Assert.assertEquals(ActTitle, "Search Results");
			
		}
		
		public void quickSearchCheck() throws Exception{
			
			driver.findElement(By.xpath("//*[@id='s2id_autogen1']/a/span[@class='select2-chosen']")).click();
		    driver.findElement(By.xpath("html/body/div[13]/div/input")).sendKeys("dubai");
		    driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li/ul[@class='select2-result-sub']/li/div[@class='select2-result-label']")).click();
		    driver.findElement(By.name("checkin")).clear();
		    driver.findElement(By.name("checkin")).sendKeys("23/09/2017");
		    driver.findElement(By.name("checkin")).click();
		    driver.findElement(By.name("checkout")).clear();
		    driver.findElement(By.name("checkout")).sendKeys("08/10/2017");
		    driver.findElement(By.name("checkout")).click();
		    Select noOfAdlt=new Select(driver.findElement(By.id("adults")));
		    noOfAdlt.selectByVisibleText("2");
		    Select noOfCdrn=new Select(driver.findElement(By.id("child")));
		    noOfCdrn.selectByVisibleText("2");
		    driver.findElement(By.xpath("html/body/div[5]/div[2]/div/div[2]/form/div[6]/div/button")).click();
		    Thread.sleep(9000);
		    String summary=driver.findElement(By.xpath("html/body/div[3]/div/div[2]/span")).getText();
		    if(!summary.equals("Dubai , United Arab Emirates"))
		    	Assert.fail("Text not equal!!");
		    
		}
		
		
		public String hotelDetailsCheck() throws Exception{
			
			driver.findElement(By.xpath("html/body/div[5]/div[3]/div/table/tbody/tr[1]/td/div/div[2]/div/div[1]/a/button")).click();
			Thread.sleep(9000);
			return driver.getTitle();
		}
		
		
}
