package util.page.prometheus;

import org.openqa.selenium.WebDriver;

import util.page.Page;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrometheusMetricsPage extends Page {

	public PrometheusMetricsPage(WebDriver driver) {
		super(driver);
		driver.get("http://localhost:9090/metrics");
	}

	public void checkPage() {
		assertEquals(driver.getTitle(),"");
		assertTrue(driver.getPageSource().contains("# HELP"));
		assertTrue(driver.getPageSource().contains("# TYPE"));

		
		
	}

}
