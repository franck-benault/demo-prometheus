package springboot.example01;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import util.page.springapplication.SpringApplicationMainPage;
import util.page.springapplication.SpringApplicationMetricsPage;

public class Example01 {
	
	private static WebDriver driver;
	
	private static WebDriver getWebDriver() {
		System.setProperty(
				"webdriver.gecko.driver",
				"C:\\Users\\fr17649\\Desktop\\presentations\\Selenium-2016-11-29\\driver\\geckodriver-v0.16.1-win64\\geckodriver.exe");

		FirefoxProfile ff = new FirefoxProfile();
		ff.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
		driver = new FirefoxDriver(ff);
		
		return driver;
	}
	
	public static void sleep() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Start demo spring boot application 01");
		driver = getWebDriver();
		
		//show spring boot main page
		new SpringApplicationMainPage(driver);
		sleep();		
		
		//show spring boot metric -> nothing
		new SpringApplicationMetricsPage(driver);
		sleep();
		
		//show prometheus page ??
		
		
		//end page
		driver.close();
		System.out.println("End demo spring boot application 01");
	}

}
