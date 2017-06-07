package springboot.example01;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import util.page.prometheus.MainGraphPage;
import util.page.springapplication.SpringApplicationMainPage;
import util.page.springapplication.SpringApplicationMetricsPage;

public class Example04 {
	
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
		System.out.println("Start demo spring boot application 04");
		driver = getWebDriver();
		
		//show spring boot main page
		new SpringApplicationMainPage(driver);
		sleep();	
		//show spring boot metrics page
		new SpringApplicationMetricsPage(driver);
		sleep();	
		
		
		//show prometheus page
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
		sleep();
		page.getTargetsPage();
		sleep();
		
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		sleep();
		page.getAlerts();
		sleep();
		
		//Add prometheus graph  and console
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("up");
		sleep();
		
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		//url http://localhost:9090/graph?g0.range_input=15m&g0.expr=jvm_memory_bytes_used%7Barea%3D%22heap%22%7D&g0.tab=0
		//url http://localhost:9090/graph?g0.range_input=15m&g0.expr=jvm_memory_bytes_used%7Barea%3D%22heap%22%7D&g0.tab=0
		//&g1.range_input=15m&g1.expr=jvm_memory_bytes_used%7Barea%3D%22heap%22%7D&g1.tab=0
		page = page.fillAndDrawGraph("jvm_memory_bytes_used{area=\"heap\"}");
		sleep();
		
		
		//end page
		driver.close();
		System.out.println("End demo spring boot application 04");
	}

}
