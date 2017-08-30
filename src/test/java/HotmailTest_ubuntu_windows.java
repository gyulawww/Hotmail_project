
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import static org.junit.Assert.fail;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HotmailTest_ubuntu_windows {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private String os;

	  @BeforeTest
	  public void setUp() throws Exception {
		System.out.println(System.getProperty("os.name"));
		os=System.getProperty("os.name");
//		System.getProperties().list(System.out);
		
		if (os.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver",  "web-drivers\\chromedriver.exe");
		    driver = new ChromeDriver(); 
		    System.out.println(driver);
		} else if (os.contains("Linux")){
	        //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			System.setProperty("webdriver.chrome.driver", "/var/lib/jenkins/tools/chromedriver/chromedriver");
	        driver = new ChromeDriver();

		}
		baseUrl = "https://outlook.live.com/owa/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testHotmail() throws Exception {
		 System.out.println(baseUrl);
	     driver.get(baseUrl);
	     Thread.sleep(2000);
   
	     driver.findElement(By.className("buttonLargeBlue")).click();
	     Thread.sleep(3000);
	     driver.findElement(By.cssSelector("#i0116")).sendKeys("gyulawww@hotmail.com");
	    // driver.findElement(By.xpath("html/body/div/form[1]/div/div/section/div/div[1]/div/div/div/div[2]/div/div/div[3]/div[2]/div/input[1]")).sendKeys("gyulawww@hotmail.com");
	     driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();
	     Thread.sleep(3000);
	     driver.findElement(By.xpath("//*[@id=\"i0118\"]")).sendKeys("Fold1111");
	     driver.findElement(By.id("idSIButton9")).click();
	     Thread.sleep(3000);
	     
	     // Explicit wait example:
	     //WebDriverWait wait = new WebDriverWait(driver, 30/*timeout in seconds*/);
	     //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"_ariaId_26\"]")));
	     FluentWait wait = new FluentWait<>(driver) .withTimeout(30,TimeUnit.SECONDS ) .pollingEvery(5, TimeUnit.SECONDS) .ignoring(NoSuchElementException.class);
	     driver.findElement(By.xpath("//*[@id=\"_ariaId_26\"]")).click();
	     Thread.sleep(3000);
	  }

	  @AfterTest	  
	  public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
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
