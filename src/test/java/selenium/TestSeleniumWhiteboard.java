package selenium;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSeleniumWhiteboard {

private static WebDriver driver= null;
	
	@BeforeClass
	public static void inicializarDirver() {
		//System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
		System.setProperty("webdriver.chrome.driver","/home/matias/Escritorio/chromedriver");
		driver = new ChromeDriver();
		
	}
	
	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}
	
	@Test
	public void testGuardarUnDibujo() {

		driver.get("http://localhost:8080/");
		
		WebElement usuario=driver.findElement(By.id("user"));
		usuario.sendKeys("Aczero");
		WebElement contrasenha=driver.findElement(By.id("pass"));
		contrasenha.sendKeys("123");
		WebElement btnAccept=driver.findElement(By.id("btn-signup"));
		btnAccept.click();
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.id("Vopros")));
		
		driver.get("http://localhost:8080/#!/main/1/Aczero/whiteboard");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("btn")));
		WebElement btnGuardar = driver.findElement(By.id("btn"));
		btnGuardar.click();
		
		driver.get("http://localhost:8080/#!/main/1/Aczero/whiteboard");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("gallery_product")));
		assertTrue(driver.findElement(By.className("gallery_product")) != null);
	}

}
