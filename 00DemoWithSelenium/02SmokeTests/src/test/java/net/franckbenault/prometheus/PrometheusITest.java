package net.franckbenault.prometheus;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import util.page.prometheus.ConfigPage;
import util.page.prometheus.MainGraphPage;
import util.page.prometheus.PrometheusAlertsPage;
import util.page.prometheus.PrometheusRulesPage;


public class PrometheusITest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		//driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
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
		MainGraphPage page = new MainGraphPage(driver);
		page.checkPage();
	}
	
	@Test
	public void testConfigPage() throws InterruptedException {
		MainGraphPage page = new MainGraphPage(driver);
		TimeUnit.SECONDS.sleep(2);
		
		ConfigPage page2 =page.getConfigPage();
		TimeUnit.SECONDS.sleep(2);
		
		page2.checkPage();
	}
	
	@Test
	public void testRulesPage() throws InterruptedException {
		MainGraphPage page = new MainGraphPage(driver);
		TimeUnit.SECONDS.sleep(2);
		
		PrometheusRulesPage page2 =page.getRulesPage();
		TimeUnit.SECONDS.sleep(2);
		
		page2.checkPage();
	}
	
	
	@Test
	public void testAlertsPage() throws InterruptedException {
		MainGraphPage page = new MainGraphPage(driver);
		TimeUnit.SECONDS.sleep(2);
		
		PrometheusAlertsPage page2 =page.getAlerts();
		TimeUnit.SECONDS.sleep(2);
		
		page2.checkPage();
	}

}