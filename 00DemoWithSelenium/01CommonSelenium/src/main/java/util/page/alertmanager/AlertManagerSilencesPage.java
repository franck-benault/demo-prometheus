package util.page.alertmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import util.page.Page;

public class AlertManagerSilencesPage extends Page {

	public AlertManagerSilencesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void checkPage() {
		assertEquals(driver.getTitle(),"AlertManager – Prometheus");
		System.out.println(driver.getPageSource());
		assertTrue(driver.getPageSource().contains("No silences configured"));
	}

}
