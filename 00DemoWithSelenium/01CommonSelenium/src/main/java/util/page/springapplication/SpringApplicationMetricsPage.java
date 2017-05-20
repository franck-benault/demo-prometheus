package util.page.springapplication;

import org.openqa.selenium.WebDriver;

import util.page.Page;
import static org.junit.Assert.assertNull;

public class SpringApplicationMetricsPage extends Page {

	public SpringApplicationMetricsPage(WebDriver driver) {
		super(driver);
		driver.get("http://localhost:8080/metrics");
	}

	public void checkPage() {
		assertNull(driver.getTitle());
	}
}
