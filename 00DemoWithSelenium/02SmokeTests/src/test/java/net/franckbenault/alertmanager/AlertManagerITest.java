package net.franckbenault.alertmanager;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import util.page.alertmanager.AlertManagerMainPage;
import util.page.alertmanager.AlertManagerSilencesPage;


public class AlertManagerITest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		//driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
		System.setProperty(
				"webdriver.gecko.driver",
				"C:\\Users\\fr17649\\Desktop\\presentations\\Selenium-2016-11-29\\driver\\geckodriver-v0.16.1-win64\\geckodriver.exe");

		FirefoxProfile ff = new FirefoxProfile();
		ff.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
		//ff.addExtension(extensionToInstall);
		
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
		AlertManagerMainPage mainPage = new AlertManagerMainPage(driver);
		mainPage.checkPage();
	}
	
	@Test
	public void testSilencesPage() throws InterruptedException {
		AlertManagerMainPage mainPage = new AlertManagerMainPage(driver);
		TimeUnit.SECONDS.sleep(2);
		AlertManagerSilencesPage silencesPage = mainPage.getSilencesPage();
		//driver.get("http://localhost:9093/#/silences");
		
		//AlertManagerSilencesPage silencesPage = new AlertManagerSilencesPage(driver);
		silencesPage.checkPage();

	}

}
