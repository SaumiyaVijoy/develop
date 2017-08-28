package Questions;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Question1FooterPage {
	
	WebDriver driver;
	
	public Question1FooterPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void supplierRegCheck() throws Exception{
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	  	jse.executeScript("window.scrollBy(0,2800)", "");
	  	Thread.sleep(9000);
	  	driver.findElement(By.xpath("html/body/footer/div[4]/div/div/a[1]")).click();
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(4000);
		driver.findElement(By.xpath("html/body/div[5]/div/div/h3/a")).click();
		String supReg=driver.findElement(By.xpath("html/body/div[4]/h2")).getText();
		if(!supReg.equalsIgnoreCase("PHPTRAVELS helps grow your business"))
			Assert.fail("Not pointing to the specified page!");
		driver.findElement(By.id("hotels")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[1]/div/input")).sendKeys("Hyatt Regency Perth");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[2]/div/input")).sendKeys("Albert");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[3]/div/input")).sendKeys("Thomas");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[4]/div/input")).sendKeys("albert21@abc.sa");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[5]/div/input")).sendKeys("1234567890");
		Select country=new Select(driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[6]/div/select")));
		country.selectByVisibleText("India");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[7]/div/input")).sendKeys("Kerala");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[8]/div/input")).sendKeys("Kochi");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[9]/div/input")).sendKeys("Edapalli");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[1]/div/fieldset/div[10]/div/input")).sendKeys("P.O-68752");
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/form/div[2]/button")).click();
		Thread.sleep(25000);
		String text="You have registered successfully. Once your account is approved you will receive further details";
		//String text="Email Address is Already in Use.";
		Assert.assertTrue(driver.getPageSource().contains(text));
		Thread.sleep(5000);
	}

	public String tabCheck() throws Exception{
		
		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		//CLOSE CURRENT TAB AND GO TO PREVIOUS TAB
		driver.close();
		driver.switchTo().window(windowHandles.get(0));
		driver.findElement(By.id("footer")).click();
		return driver.getTitle();		
			
	}
}
