package springboot.example01;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import util.page.prometheus.MainGraphPage;
import util.page.springapplication.SpringApplicationEndPointAPage;
import util.page.springapplication.SpringApplicationEndPointBPage;
import util.page.springapplication.SpringApplicationMainPage;
import util.page.springapplication.SpringApplicationMetricsPage;

public class Example08 {
	
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
		System.out.println("Start demo spring boot application 08");
		driver = getWebDriver();
		
		//show spring boot main page and endpointA endpointB
		new SpringApplicationMainPage(driver);
		sleep();	
		
		new SpringApplicationEndPointAPage(driver);
		sleep();	
		
		new SpringApplicationEndPointBPage(driver);
		sleep();	
		
		//show spring boot metrics page
		new SpringApplicationMetricsPage(driver);
		sleep();	
		
		
		//show prometheus page
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
		sleep();
		page.getTargetsPage();
		sleep();
		
		//Pometheus alert page
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		sleep();
		page.getAlerts();
		sleep();
		
		//Pometheus graph and console
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		sleep();
		page = page.fillAndDrawGraph("up");
		sleep();
		
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("rate(response_time_summary_sum{method=\"endpointB\"}[1m])/rate(response_time_summary_count{method=\"endpointB\"}[1m])*1000");
		sleep();
		
		//end page
		driver.close();
		System.out.println("End demo spring boot application 08");
	}

}
