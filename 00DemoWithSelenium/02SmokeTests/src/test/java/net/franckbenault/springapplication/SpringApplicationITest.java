package net.franckbenault.springapplication;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import util.page.springapplication.SpringApplicationMainPage;
import util.page.springapplication.SpringApplicationMetricsPage;

public class SpringApplicationITest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		//driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_45, true);
		System.setProperty(
				"webdriver.gecko.driver",
				"C:\\Users\\fr17649\\Desktop\\presentations\\Selenium-2016-11-29\\driver\\geckodriver-v0.16.1-win64\\geckodriver.exe");

		FirefoxProfile ff = new FirefoxProfile();
		ff.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
		driver = new FirefoxDriver(ff);

		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@After
	public void tearDown() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void testMainPage() {
		SpringApplicationMainPage mainPage = new SpringApplicationMainPage(driver);
		mainPage.checkPage();
			
	}

	@Test
	public void testMetricPage() {
		SpringApplicationMetricsPage metricsPage = new SpringApplicationMetricsPage(driver);
		metricsPage.checkPage();
			
	}

}
