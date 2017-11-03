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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSeleniumFechaDeExpiracion {

private static WebDriver driver= null;
	
	@BeforeClass
	public static void inicializarDirver() {
//		File file = new File("/usr/bin/google-chrome");
//        System.setProperty("webdriver.gecko.driver","/home/gaston/Documentos/geckodriver"); 
		System.setProperty("webdriver.chrome.driver","/home/gaston/Documentos/chromedriver");
		driver = new ChromeDriver();
		
	}
	
	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}
	@Test
	public void testInputDeFechaIncorrectaNuevoIssue() {
		driver.get("http://localhost:8080/#!/main/issue/nuevo");
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
		driver.get("http://localhost:8080/#!/main/issue/nuevo");
		
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
		user.sendKeys("Gaston");
		
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
		driver.get("http://localhost:8080/#!/main/issues");
		assertTrue(driver.findElement(By.id("Issue test")) != null);


	}

}
