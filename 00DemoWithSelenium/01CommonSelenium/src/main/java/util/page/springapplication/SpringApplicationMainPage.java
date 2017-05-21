package util.page.springapplication;

import org.openqa.selenium.WebDriver;

import util.page.Page;
import static org.junit.Assert.assertTrue;

public class SpringApplicationMainPage extends Page {

	public SpringApplicationMainPage(WebDriver driver) {
		super(driver);
		driver.get("http://localhost:8080/");
	}

	public void checkPage() {
		assertTrue(driver.getPageSource().contains("Greetings from Spring Boot!"));
	}
}
