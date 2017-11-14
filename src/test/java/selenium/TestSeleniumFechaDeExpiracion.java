package selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSeleniumFechaDeExpiracion {

private static WebDriver driver= null;
	
	@BeforeClass
	public static void inicializarDirver() {
//		File file = new File("/usr/bin/google-chrome");
        System.setProperty("webdriver.chrome.driver","/home/ignacio/Descargas/chromedriver"); 
//		System.setProperty("webdriver.chrome.driver","/home/matias/Escritorio/chromedriver");
		driver = new ChromeDriver();
		
	}
	
	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}
	
	@Test
	public void testInputDeFechaIncorrectaNuevoIssue() {
		driver.get("http://localhost:8080/#!/main/1/Aczero/issue/nuevo");
		WebElement fechaInput = driver.findElement(By.name("first_date"));
		fechaInput.sendKeys(Keys.TAB);		fechaInput.sendKeys(Keys.TAB);
		fechaInput.sendKeys("2017");
		fechaInput.sendKeys(Keys.TAB);
		fechaInput.sendKeys("01");
		fechaInput.sendKeys("01");
		WebElement errormsg=driver.findElement(By.id("errorFecha"));
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		assertEquals(errormsg.getText(),"La fecha debe ser mayor o igual a la actual");
	}
	
	@Test
	public void testGuardarIssueCreadoConFechaCorrecta() {
		
		driver.get("http://localhost:8080/#!/main/1/Aczero/issue/nuevo");

		
		WebElement titulo= driver.findElement(By.id("tituloIssue"));
		titulo.sendKeys("Issue test");
		WebElement tipo= driver.findElement(By.id("tipoIssue"));
		tipo.sendKeys("PREGUNTA");
		WebElement gravedad= driver.findElement(By.id("gravedadIssue"));
		gravedad.sendKeys("MENOR");
		WebElement prioridad= driver.findElement(By.id("prioridadIssue"));
		prioridad.sendKeys("BAJA");
		WebElement fechaInput = driver.findElement(By.name("first_date"));
		fechaInput.sendKeys(Keys.TAB);		fechaInput.sendKeys(Keys.TAB);
		fechaInput.sendKeys("2018");
		fechaInput.sendKeys(Keys.TAB);
		fechaInput.sendKeys("01");
		fechaInput.sendKeys("01");
		WebElement user= driver.findElement(By.id("usuarioAsignado"));
		user.sendKeys("Aczero");
		
		WebElement botonGuardar=driver.findElement(By.id("save"));
		botonGuardar.click();
		
		new WebDriverWait(driver, 10)
		  .until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return driver.findElement(By.id("feedback")).getText().length() != 0;
	            }
	        });
		WebElement errormsg=driver.findElement(By.id("feedback"));
		assertEquals(errormsg.getText(),"Problema creado con exito");
		
		
		//Chequea que efectivamente el issue esta en la lista de issues
		driver.get("http://localhost:8080/#!/main/1/Aczero/issues");

		new WebDriverWait(driver, 10)
		  .until(ExpectedConditions.visibilityOfElementLocated(By.id("Issue test")));
		
		assertTrue(driver.findElement(By.id("Issue test")) != null);


	}
	
	@Test
	public void testInputDeFechaIncorrectaNuevoTask() {
		driver.get("http://localhost:8080/#!/main/1/Aczero/task/nuevo");
		WebElement fechaInput = driver.findElement(By.name("first_date"));
		fechaInput.sendKeys(Keys.TAB);		fechaInput.sendKeys(Keys.TAB);
		fechaInput.sendKeys("2017");
		fechaInput.sendKeys(Keys.TAB);
		fechaInput.sendKeys("01");
		fechaInput.sendKeys("01");
		WebElement errormsg=driver.findElement(By.id("errorFecha"));
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		assertEquals(errormsg.getText(),"La fecha debe ser mayor o igual a la actual");
	}
	
	@Test
	public void testGuardarTaskCreadoConFechaCorrecta() {
		driver.get("http://localhost:8080/#!/main/1/Aczero/task/nuevo");

		
		new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.id("nombreTask")));
		WebElement titulo= driver.findElement(By.id("nombreTask"));
		titulo.sendKeys("Task test");
		WebElement descripcion= driver.findElement(By.id("descripcionTask"));
		descripcion.sendKeys("dummy string");
		WebElement dificultad= driver.findElement(By.id("dificultadTask"));
		dificultad.sendKeys("XXL");
		WebElement prioridad= driver.findElement(By.id("prioridadTask"));
		prioridad.sendKeys("BAJA");
		WebElement estado= driver.findElement(By.id("estadoTask"));
		estado.sendKeys("CERRADO");
		WebElement fechaInput = driver.findElement(By.name("first_date"));
		fechaInput.sendKeys(Keys.TAB);		fechaInput.sendKeys(Keys.TAB);
		fechaInput.sendKeys("2018");
		fechaInput.sendKeys(Keys.TAB);
		fechaInput.sendKeys("01");
		fechaInput.sendKeys("01");
		WebElement user= driver.findElement(By.id("usuarioAsignado"));
		user.sendKeys("Aczero");
		
		WebElement botonGuardar=driver.findElement(By.id("save"));
		botonGuardar.click();
		
		new WebDriverWait(driver, 10)
		  .until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return driver.findElement(By.id("feedback")).getText().length() != 0;
	            }
	        });
		WebElement errormsg=driver.findElement(By.id("feedback"));
		assertEquals(errormsg.getText(),"Tarea creada con exito");
		
		
		//Chequea que efectivamente el task esta en la lista de tasks
		driver.get("http://localhost:8080/#!/main/1/Aczero/tasks");
		new WebDriverWait(driver, 10)
		  .until(ExpectedConditions.visibilityOfElementLocated(By.id("Task test")));
		assertTrue(driver.findElement(By.id("Task test")) != null);


	}

}
