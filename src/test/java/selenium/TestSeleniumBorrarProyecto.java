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
		
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
		driver = new ChromeDriver();
		
	}
	
	@AfterClass
	public static void exitDriver() {
		driver.quit();
	}

	//Se elimino temporalmente la posibilidad de borrar proyectos
	@Test(expected=NoSuchElementException.class)
	public void testBorrarProyecto() {
//		driver.get("http://localhost:8080/");
//		
//		WebElement usuario=driver.findElement(By.id("user"));
//		usuario.sendKeys("Aczero");
//		WebElement contrasenha=driver.findElement(By.id("pass"));
//		contrasenha.sendKeys("123");
//		WebElement btnAccept=driver.findElement(By.id("btn-signup"));
//		btnAccept.click();
//		
//		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("Vopros")));
//		WebElement proyecto= driver.findElement(By.id("Vopros"));
//		proyecto.click();
//		WebElement boton = driver.findElement(By.id("borrar"));
//		boton.click();
//		new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(By.id("Vopros")));
//		//Chequeo que elemento ya no exista y dispare excepcion
//		driver.findElement(By.id("Vopros"));

	}

}
