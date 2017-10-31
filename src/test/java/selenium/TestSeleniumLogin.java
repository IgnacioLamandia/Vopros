package selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TestSeleniumLogin {
	
	private static WebDriver driver= null;
	
	@BeforeClass
	//Driver gecko https://github.com/mozilla/geckodriver/releases
	public static void inicializarDirver() {
//		File file = new File("/usr/bin/google-chrome");
        System.setProperty("webdriver.gecko.driver","/home/gaston/Documentos/geckodriver"); 
//		System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/");

	}
	
	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}

	@Test
	public void testLoginCorrecto() {
		
		
		WebElement usuario=driver.findElement(By.id("user"));
		usuario.sendKeys("Gaston");
		WebElement contrasenha=driver.findElement(By.id("pass"));
		contrasenha.sendKeys("123");
		WebElement btnAccept=driver.findElement(By.id("btn-signup"));

		btnAccept.click();
		

		//Espero 20 seg para que cargue la pag
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		wait.until(ExpectedConditions.or(ExpectedConditions.urlMatches("http://localhost:8080/#!/main/home"),ExpectedConditions.presenceOfElementLocated(By.id("errorMsg"))));
		
		assertEquals(driver.getCurrentUrl(),"http://localhost:8080/#!/main/home");
	}
	
	@Test
	public void testLoginIncorrecto() {
		
		driver.get("http://localhost:8080/");
		
		WebElement usuario=driver.findElement(By.id("user"));
		usuario.sendKeys("Usuario random");
		WebElement contrasenha=driver.findElement(By.id("pass"));
		contrasenha.sendKeys("123");
		
		WebElement btnAccept=driver.findElement(By.id("btn-signup"));
		btnAccept.click();
		
		//Espero 5 seg para que cargue la pag
		
		  driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebElement error = driver.findElement(By.className("error"));
		assertEquals(error.findElement(By.id("errorMsg")).getText(),"Usuario o contraseña invalido/a");
	}

}