package selenium;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSeleniumBorrarProyecto {

private static WebDriver driver= null;
	
	@BeforeClass
	public static void inicializarDirver() {
//        System.setProperty("webdriver.gecko.driver","/home/gaston/Documentos/geckodriver"); 
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver","/home/gaston/Documentos/chromedriver");
		driver = new ChromeDriver();
		
	}
	
	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}

	@Test(expected=NoSuchElementException.class)
	public void testBorrarProyecto() {
		driver.get("http://localhost:8080/#!/main/proyectos");
		WebElement proyecto= driver.findElement(By.id("Vopros"));
		proyecto.click();
		WebElement boton = driver.findElement(By.id("borrar"));
		boton.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.id("Vopros")));
		//Chequeo que elemento ya no exista y dispare excepcion
		driver.findElement(By.id("Vopros"));

	}

}
