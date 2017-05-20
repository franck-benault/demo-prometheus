package util.page.prometheus;

import org.openqa.selenium.WebDriver;

import util.page.Page;
import static org.junit.Assert.assertTrue;

public class PrometheusRulesPage extends Page {

	public PrometheusRulesPage(WebDriver driver) {
		super(driver);
	}

	public void checkPage() {
		assertTrue(
		driver.getTitle().contains("Prometheus Time Series Collection and Processing Server")
		);
		
		assertTrue(
		driver.getPageSource().contains("ALERT")
		);
		
	}

}
