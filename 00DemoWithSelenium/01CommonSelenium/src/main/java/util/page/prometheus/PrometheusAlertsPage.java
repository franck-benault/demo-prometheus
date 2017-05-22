package util.page.prometheus;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import util.page.Page;

public class PrometheusAlertsPage extends Page {

	public PrometheusAlertsPage(WebDriver driver) {
		super(driver);

	}
	
	
	public void checkPage() {
		assertTrue(
		driver.getTitle().contains("Prometheus Time Series Collection and Processing Server")
		);
		
		assertTrue(
		driver.getPageSource().contains("(0 active)")
		);
		
		assertTrue(
				driver.getPageSource().contains("service_down")
				);
	}

}
