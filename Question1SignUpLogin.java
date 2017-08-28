package Questions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Question1SignUpLogin {

	WebDriver driver;
	
	public Question1SignUpLogin(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String SignUp(){
		
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[2]/a")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("login"))));
		
		driver.findElement(By.name("firstname")).sendKeys("Cathy");
		driver.findElement(By.name("lastname")).sendKeys("Simon");
		driver.findElement(By.name("phone")).sendKeys("1234567890");
		driver.findElement(By.name("email")).sendKeys("cathy10@abc.com");
		driver.findElement(By.name("password")).sendKeys("Cathy12");
		driver.findElement(By.name("confirmpassword")).sendKeys("Cathy12");

		driver.findElement(By.xpath("html/body/section/div/div/div/div/div[2]/div/form/div[9]/button")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bookings"))));
		String titleAfterSignIn=driver.getTitle();
		Assert.assertEquals(titleAfterSignIn, "My Account");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/a"))));
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/a")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[2]/a"))));
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[2]/a")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("remember-me"))));
		return driver.getTitle();
			
	}
	
	public String validLogin(){
		
		driver.findElement(By.name("username")).sendKeys("cathy10@abc.com");
		driver.findElement(By.name("password")).sendKeys("Cathy12");
		
		driver.findElement(By.xpath("html/body/div[3]/div[1]/form/div[4]/button")).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bookings"))));
		String titleAfterSignIn=driver.getTitle();
		Assert.assertEquals(titleAfterSignIn, "My Account");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/a"))));
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/a")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[2]/a"))));
		driver.findElement(By.xpath("html/body/div[2]/div/div/div[2]/ul/li[2]/ul/li[2]/a")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("remember-me"))));
		return driver.getTitle();
		
	}
	
	public String invalidLogin(){
		
		driver.findElement(By.name("username")).sendKeys("Abcd@abc.com");
		driver.findElement(By.name("password")).sendKeys("Abcd12");
		
		driver.findElement(By.xpath("html/body/div[3]/div[1]/form/div[4]/button")).click();
		String validate=driver.findElement(By.xpath("html/body/div[3]/div[1]/form/div[1]/div")).getText();
		return validate;
		
	}
		
}
