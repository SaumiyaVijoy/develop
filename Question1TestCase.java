package testcasespom;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import page.Question1FooterPage;
import page.Question1Frames;
import page.Question1HomePage;
import page.Question1HotelDetailsPage;
import page.Question1ResultsPage;
import page.Question1SignUpLogin;

public class Question1TestCase {
	
	WebDriver driver;
	Question1HomePage homeObject;
	Question1ResultsPage resultObject;
	Question1HotelDetailsPage hotelDetailsObject;
	Question1FooterPage footerObject;
	Question1Frames frameObject;
	Question1SignUpLogin myAccountObject;
	static final ExtentReports extent = ExtentReports.get(Question1TestCase.class);
	
	
	@BeforeClass
	public void reportInitialisation(){
	
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy-HHmmss");
		Date date = new Date();
		String date1= dateFormat.format(date);
		System.out.println(date1);
		extent.init("C:\\Saumiya\\RESULTS\\report"+date1+".html", true);
		extent.startTest("Verify Testcase");
	}
	
	@BeforeMethod
	public void SetUp(){
		
		/*System.setProperty("webdriver.chrome.driver", "C:\\SW\\LATEST\\chromedriver.exe");
		driver = new ChromeDriver();*/
		System.setProperty("webdriver.gecko.driver", "C:\\SW\\LATEST\\geckodriver.exe");
		driver = new FirefoxDriver();
		
	}

	//@Test(priority=0)
	//@Test(enabled=true)
	@Test(enabled=false)
	public void searchFieldTest() throws Exception{
		
		homeObject= new Question1HomePage(driver);
		
		homeObject.launchUrl();
		homeObject.locationCheck();
		homeObject.inOutPrefillCheck();
		homeObject.inOutCheck();
		homeObject.locationSearchCheck();
		String titleResultsPage=homeObject.overallFieldCheck();
		Assert.assertTrue(titleResultsPage.contains("Search Results"));
		
		extent.log(LogStatus.INFO, "Result page title verified!");
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Saumiya\\RESULTS\\IMAGES\\image1.jpg"));
		extent.attachScreenshot("C:\\Saumiya\\RESULTS\\IMAGES\\image1.jpg");
	}
	
	//@Test(priority=1)
	//@Test(enabled=true)
	@Test(enabled=false)
	public void resultPageTest() throws Exception{
		
		
		homeObject= new Question1HomePage(driver);
		resultObject= new Question1ResultsPage(driver);
		
		homeObject.launchUrl();
		
		String titleResultsPage=homeObject.overallFieldCheck();
		Assert.assertTrue(titleResultsPage.contains("Search Results"));
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Saumiya\\RESULTS\\IMAGES\\image2.jpg"));
		extent.attachScreenshot("C:\\Saumiya\\RESULTS\\IMAGES\\image2.jpg");
		extent.log(LogStatus.INFO, "Result page title verified!");
		
		resultObject.filterCheck();
		resultObject.quickSearchCheck();
		resultObject.whishlistCheck();
		
		String titleHotelDetails = resultObject.hotelDetailsCheck();
		Assert.assertEquals(titleHotelDetails, "Hyatt Regency Perth");
		
		extent.log(LogStatus.INFO, "Hotel Page Details verified!");
		src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Saumiya\\RESULTS\\IMAGES\\image3.jpg"));
		 extent.attachScreenshot("C:\\Saumiya\\RESULTS\\IMAGES\\image3.jpg");
	
	}
	
	//@Test(priority=2)
	//@Test(enabled=true)
	@Test(enabled=false)
	public void hotelDetailsPageTest() throws Exception{
		
		homeObject= new Question1HomePage(driver);
		hotelDetailsObject =new Question1HotelDetailsPage(driver);
		resultObject= new Question1ResultsPage(driver);
		
		homeObject.launchUrl();
		
		String titleResultsPage=homeObject.overallFieldCheck();
		Assert.assertTrue(titleResultsPage.contains("Search Results"));
		String titleHotelDetails = resultObject.hotelDetailsCheck();
		Assert.assertEquals(titleHotelDetails, "Hyatt Regency Perth");
		
		hotelDetailsObject.imageCarouselCheck();
		hotelDetailsObject.currencyChangeCheck();
		hotelDetailsObject.languageChangeCheck();
		
		String titleHptelBooking = hotelDetailsObject.bookHotelCheck();
		Assert.assertEquals(titleHptelBooking, "Invoice");
		
		extent.log(LogStatus.INFO, "Booking a hotel verified!");
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Saumiya\\RESULTS\\IMAGES\\image4.jpg"));
		extent.attachScreenshot("C:\\Saumiya\\RESULTS\\IMAGES\\image4.jpg");
		
	}
	
	//@Test(priority=3)
	//@Test(enabled=true)
	@Test(enabled=false)
	public void footerTest() throws Exception{
	
		homeObject= new Question1HomePage(driver);
		footerObject =new Question1FooterPage(driver);
		
		homeObject.launchUrl();
		footerObject.supplierRegCheck();
		
		String titlePreviousPage = footerObject.tabCheck();
		Assert.assertEquals(titlePreviousPage, "PHPTRAVELS | Travel Technology Partner");
		
		extent.log(LogStatus.INFO, "Closing current tab verified!");
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Saumiya\\RESULTS\\IMAGES\\image5.jpg"));
		extent.attachScreenshot("C:\\Saumiya\\RESULTS\\IMAGES\\image5.jpg");
		
	}
	
	@Test(enabled=false)
	public void frameTest() throws Exception{
		
		frameObject =new Question1Frames(driver);
		frameObject.launchUrl();
		//frameObject.initialRegistration();
		frameObject.signIn();
		/*String titleOldTab=frameObject.newTabCheck();
		Assert.assertEquals(titleOldTab, "Welcome");
		String titleOldWindow=frameObject.newWindowCheck();
		Assert.assertEquals(titleOldWindow, "Welcome");*/
		/*String[] colours =frameObject.frameSetCheck();
		System.out.println("The top frame colour code is "+colours[0]+" and bottom frame colour code is "+colours[1]);*/
		frameObject.frameSetCheck();
		/*String multiWindowContent=frameObject.multiWindowCheck();
		Assert.assertEquals(multiWindowContent, "Open Window-2");*/
	}
	//@Test(priority=4)
	//@Test(enabled=false)
	@Test(enabled=true)
	public void signUpLoginTest() throws Exception{
	
		homeObject= new Question1HomePage(driver);
		myAccountObject =new Question1SignUpLogin(driver);
		
		homeObject.launchUrl();
		String titleAfterLogout = myAccountObject.SignUp();
		Assert.assertEquals(titleAfterLogout, "Login");
		extent.log(LogStatus.INFO, "Successful signin verified!");
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Saumiya\\RESULTS\\IMAGES\\image6.jpg"));
		extent.attachScreenshot("C:\\Saumiya\\RESULTS\\IMAGES\\image6.jpg");
		
		String titleAfterValidLogout = myAccountObject.validLogin();
		Assert.assertEquals(titleAfterValidLogout, "Login");
		extent.log(LogStatus.INFO, "Successful login verified!");
		src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Saumiya\\RESULTS\\IMAGES\\image7.jpg"));
		extent.attachScreenshot("C:\\Saumiya\\RESULTS\\IMAGES\\image7.jpg");
		
		String titleAfterInValidLogout = myAccountObject.invalidLogin();
		Assert.assertEquals(titleAfterInValidLogout, "Invalid Email or Password");
		extent.log(LogStatus.INFO, "Invalid login verified!");
		src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Saumiya\\RESULTS\\IMAGES\\image8.jpg"));
		extent.attachScreenshot("C:\\Saumiya\\RESULTS\\IMAGES\\image8.jpg");
	
	}
	
	@AfterMethod
	public void Closing(){
		
		driver.close();
	}

	
}
