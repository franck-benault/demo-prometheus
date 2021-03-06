package util.page.springapplication;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import util.page.Page;
import static org.junit.Assert.assertTrue;

public class SpringApplicationMainPage extends Page {
	
	private static final String url = "http://localhost:8080/";

	public SpringApplicationMainPage(WebDriver driver) {
		super(driver);
		driver.get(url);
		
		int nbTries =1;
		//springboot
		while(driver.getPageSource().contains("404") && nbTries<4) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.get(url);
			nbTries++;
		}
	}

	public void checkPage() {
		assertTrue(driver.getPageSource().contains("Greetings from Spring Boot!"));
	}
}
