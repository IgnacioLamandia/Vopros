package selenium;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class TestSeleniumLogin {
	
	private static WebDriver driver= null;
	
	@BeforeClass
	public static void inicializarDirver() {
		File file = new File("/usr/bin/google-chrome");
		//File file = new File("/home/ignacio/Escritorio/facu/ingenieria/chromedriver");
		System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
		driver = new ChromeDriver();
		
	}
	
	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}

	@Test
	public void testLoginCorrecto() {
		
		driver.get("http://localhost:9000/");
		
		WebElement usuario=driver.findElement(By.id("user"));
		usuario.sendKeys("Gaston");
		WebElement contrasenha=driver.findElement(By.id("pass"));
		contrasenha.sendKeys("123");
		
		WebElement btnAccept=driver.findElement(By.id("btn-signup"));
		btnAccept.click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		WebElement content = driver.findElement(By.id("wrapper"));
		wait.until(ExpectedConditions.visibilityOf(content));
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:9000/#!/main/home");
	}

}
