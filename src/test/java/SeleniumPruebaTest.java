

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumPruebaTest {

	@Before
	public void setUp() throws Exception {
		// Instantiate a webDriver implementation
        
        
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://github.com");
		//List<WebElement> webElements = webDriver.findElements(By.id("start-of-content"));
		//Assert.assertEquals(1, webElements.size());
        List<WebElement> webElements = webDriver.findElements(By.cssSelector("ul.nav li"));
        Assert.assertEquals(5, webElements.size()); 
	}

}