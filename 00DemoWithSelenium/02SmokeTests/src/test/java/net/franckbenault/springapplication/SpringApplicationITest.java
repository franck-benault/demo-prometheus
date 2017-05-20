package net.franckbenault.springapplication;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import util.page.springapplication.SpringApplicationMetricsPage;

public class SpringApplicationITest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_45, true);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testMetricPage() {
		SpringApplicationMetricsPage metricsPage = new SpringApplicationMetricsPage(driver);
		metricsPage.checkPage();
			
	}

}
