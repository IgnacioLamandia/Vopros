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

public class TestSeleniumEditarYBorrarTask {

	private static WebDriver driver= null;
	
	@BeforeClass
	public static void inicializarDirver() {
		System.setProperty("webdriver.chrome.driver","/home/matias/Escritorio/chromedriver");
		driver = new ChromeDriver();
	}

	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}
	
	@Test
	public void testEditarUnTask() {

		driver.get("http://localhost:8080/");
		
		WebElement usuario=driver.findElement(By.id("user"));
		usuario.sendKeys("Aczero");
		WebElement contrasenha=driver.findElement(By.id("pass"));
		contrasenha.sendKeys("123");
		WebElement btnAccept=driver.findElement(By.id("btn-signup"));
		btnAccept.click();
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.id("Vopros")));
		
		driver.get("http://localhost:8080/#!/main/1/Aczero/tasks");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Task 4")));
		WebElement task = driver.findElement(By.id("Task 4"));
		task.click();
		WebElement botonEditar = driver.findElements(By.className("btn-primary")).get(0);
		botonEditar.click();
		
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.id("nombreTask")));
		WebElement nombreTask = driver.findElement(By.id("nombreTask"));
		nombreTask.sendKeys("2");
		WebElement botonUpdate = driver.findElement(By.id("update"));
		botonUpdate.click();
		
		new WebDriverWait(driver, 10)
		  .until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return driver.findElement(By.id("feedback")).getText().length() != 0;
	            }
	        });
		
		WebElement msg=driver.findElement(By.id("feedback"));
		assertEquals(msg.getText(),"Tarea editada con exito");
		
		driver.get("http://localhost:8080/#!/main/1/Aczero/tasks");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Task 42")));	
		assertTrue(driver.findElement(By.id("Task 42")) != null);
	}
	
	@Test
	public void testBorrarUnTask() {
		
		driver.get("http://localhost:8080/");
		
		WebElement usuario=driver.findElement(By.id("user"));
		usuario.sendKeys("Aczero");
		WebElement contrasenha=driver.findElement(By.id("pass"));
		contrasenha.sendKeys("123");
		WebElement btnAccept=driver.findElement(By.id("btn-signup"));
		btnAccept.click();
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.id("Vopros")));
		
		driver.get("http://localhost:8080/#!/main/1/Aczero/tasks");
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Task 1")));
		WebElement task = driver.findElement(By.id("Task 1"));
		task.click();
		WebElement botonElim = driver.findElements(By.className("btn-primary")).get(1);
		botonElim.click();
		
		assertTrue(driver.findElement(By.id("Task 1")) == null);
	}


}
