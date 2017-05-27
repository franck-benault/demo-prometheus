package util.page.grokexporter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import util.page.Page;

public class GrokExporterMetricsPage extends Page{

	public GrokExporterMetricsPage(WebDriver driver) {
		super(driver);
		driver.get("http://localhost:9144/metrics");
	}
	
	
	public void checkPage() {
		assertEquals(driver.getTitle(),"");
		assertTrue(driver.getPageSource().contains(
		"# HELP go_gc_duration_seconds A summary of the GC invocation durations."));
		
	}

}
