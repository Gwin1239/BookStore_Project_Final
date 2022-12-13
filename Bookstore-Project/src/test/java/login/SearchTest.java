package login;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchTest {
	
	   private WebDriver driver;
	   private boolean acceptNextAlert = true;
	   private StringBuffer verificationErrors = new StringBuffer();
	   
	   @Before
	   public void setUp() throws Exception {
	      //System.setProperty("webdriver.chrome.driver", //
	    	//	  "/Users/godwin/Applications/chromedriver");
	      driver = new ChromeDriver();
	      // driver = new FirefoxDriver();
	      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   }

		
		  @Test 
		  public void testClassSearch() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/index.html"); 
			  driver.manage().window().maximize();
		  
		  
		  }
		 
	   
		
		  @Test 
		  public void getStartedButton() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/index.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.id("start")).click(); Thread.sleep(3000);
		  
		  
		  }
		 
	   
		
		
		  @Test 
		  public void priceTool() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/priceTool.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.name("title")).sendKeys("Spare"); Thread.sleep(3000);
			  driver.findElement(By.id("submit")).click(); Thread.sleep(3000);
		  
		  
		  }
		 
		  
		
		  @Test 
		  public void searchBooks() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/search.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.name("title")).sendKeys("Spare"); Thread.sleep(3000);
			  driver.findElement(By.id("submit")).click(); Thread.sleep(3000);
		  
		  
		  }
		 
	   
	   
		
		  @Test 
		  public void Cart() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/cart.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.id("name")).sendKeys("GSmoove"); Thread.sleep(3000);
			  driver.findElement(By.id("submit")).click(); Thread.sleep(3000);
		  
		  
		  }
		 
		  
		  
		
		  @Test 
		  public void goSearch() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/index.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.id("search")).sendKeys(Keys.ENTER); Thread.sleep(3000);
		  
		  
		  }
		 
	   
	   
		
		  @Test 
		  public void goHome() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/priceTool.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.id("home")).sendKeys(Keys.ENTER); Thread.sleep(3000);
		  
		  
		  }
		 
		  
		
		  @Test 
		  public void goPriceCompare() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/index.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.id("price")).sendKeys(Keys.ENTER); Thread.sleep(3000);
		  
		  
		  }
		 
		  
		
		  @Test 
		  public void goCart() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/priceTool.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.id("cart")).sendKeys(Keys.ENTER); Thread.sleep(3000);
		  
		  
		  }
		 
		  
		  
		
		  @Test 
		  public void bookDisplay() throws Exception { 
			  driver.get("http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/search.html"); 
			  driver.manage().window().maximize();
		  
			  driver.findElement(By.id("first")).sendKeys("Colleen"); Thread.sleep(3000);
		  
			  driver.findElement(By.id("submit")).click(); Thread.sleep(3000);
		  
			  driver.findElement(By.id("book")).sendKeys(Keys.ENTER); Thread.sleep(3000);
		  
		  
		  }
		 
		 
		 
		 @Test public void login() throws Exception { driver.get(
				  "http://ec2-54-162-128-216.compute-1.amazonaws.com:8080/Bookstore-Project/login.html"
				  ); driver.manage().window().maximize();
				  
				  driver.findElement(By.id("login")).click(); 
				  Thread.sleep(3000);
				  
				  driver.findElement(By.id("user")).sendKeys("Godwin"); 
				  Thread.sleep(3000);
				  
				  driver.findElement(By.id("pass")).sendKeys("testing123456789"); 
				  Thread.sleep(3000);
				  
				  driver.findElement(By.id("submit")).click(); 
				  Thread.sleep(3000);
				  
				  
		}
		  
		 
		 

	   @After
	   public void tearDown() throws Exception {
	      driver.quit();
	      String verificationErrorString = verificationErrors.toString();
	      if (!"".equals(verificationErrorString)) {
	         fail(verificationErrorString);
	      }
	   }

	   private boolean isElementPresent(By by) {
	      try {
	         driver.findElement(by);
	         return true;
	      } catch (NoSuchElementException e) {
	         return false;
	      }
	   }

	   private boolean isAlertPresent() {
	      try {
	         driver.switchTo().alert();
	         return true;
	      } catch (NoAlertPresentException e) {
	         return false;
	      }
	   }

	   private String closeAlertAndGetItsText() {
	      try {
	         Alert alert = driver.switchTo().alert();
	         String alertText = alert.getText();
	         if (acceptNextAlert) {
	            alert.accept();
	         } else {
	            alert.dismiss();
	         }
	         return alertText;
	      } finally {
	         acceptNextAlert = true;
	      }
	   }

}