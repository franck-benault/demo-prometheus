package util.page.prometheus;

import org.openqa.selenium.WebDriver;

import util.page.Page;
import static org.junit.Assert.assertTrue;

public class PrometheusTargetsPage extends Page {

	public PrometheusTargetsPage(WebDriver driver) {
		super(driver);
	}

	public void checkPage() {
		assertTrue(
		driver.getTitle().contains("Prometheus Time Series Collection and Processing Server")
		);
		
		assertTrue(
		driver.getPageSource().contains("http://applispring:8080/metrics")
		);
		
	}

}
