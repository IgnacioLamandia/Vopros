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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSeleniumCrearProyecto {

private static WebDriver driver= null;
	
	@BeforeClass
	public static void inicializarDirver() {
		//System.setProperty("webdriver.chrome.driver","/home/gaston/Documentos/chromedriver");
		System.setProperty("webdriver.chrome.driver","/home/matias/Escritorio/chromedriver");
		driver = new ChromeDriver();
	}

	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}
	
	@Test
	public void testCrearUnProyecto() {
		driver.get("http://localhost:8080/");
		
		WebElement usuario=driver.findElement(By.id("user"));
		usuario.sendKeys("Aczero");
		WebElement contrasenha=driver.findElement(By.id("pass"));
		contrasenha.sendKeys("123");
		WebElement btnAccept=driver.findElement(By.id("btn-signup"));
		btnAccept.click();
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.id("Vopros")));
		
		WebElement botonCrear = driver.findElement(By.className("btn-primary"));
		botonCrear.click();
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.className("ng-valid")));
		
		WebElement nombrePr = driver.findElement(By.className("ng-valid"));
		nombrePr.sendKeys("Taiga");
		WebElement botonGuardarPr = driver.findElements(By.className("btn-primary")).get(1);
		botonGuardarPr.click();
		new WebDriverWait(driver, 5);
		WebElement botonVolver = driver.findElements(By.className("btn-primary")).get(3);
		botonVolver.click();
		//new WebDriverWait(driver, 15).until(ExpectedConditions.urlToBe("http://localhost:8080/#!/proyectos"));
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.id("Taiga")));
		WebElement idPr = driver.findElement(By.id("Taiga"));
//		assertEquals(driver.findElements(By.className("ng-binding")).size(), 3);
		assertTrue(idPr!=null);
		
		
	}

}
