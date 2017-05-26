package springboot.example01;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


import util.page.grafana.GrafanaAddDataSource;
import util.page.grafana.GrafanaHomeDashboard;
import util.page.grafana.GrafanaMainPage;
import util.page.prometheus.MainGraphPage;
import util.page.springapplication.SpringApplicationMainPage;


public class Example00 {
	
	private static WebDriver driver;
	
	private static WebDriver getWebDriver() {
		System.setProperty(
				"webdriver.gecko.driver",
				"C:\\Users\\fr17649\\Desktop\\presentations\\Selenium-2016-11-29\\driver\\geckodriver-v0.16.1-win64\\geckodriver.exe");

		
		//DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		//FirefoxOptions options = new FirefoxOptions();
		
		//capabilities.setCapability("marionette", true);
		//capabilities.setCapability("moz:firefoxOptions", options);
		
		FirefoxProfile ff = new FirefoxProfile();
		ff.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
		
		
		//driver = new FirefoxDriver(capabilities);
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
		System.out.println("Start demo spring boot application 00");
		driver = getWebDriver();
		MainGraphPage page;
		
		//show spring boot main page
		new SpringApplicationMainPage(driver);
		sleep();		
		
		//show prometheus page
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
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
		//add prometheus console info
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page.fillGraphConsole("prometheus_build_info[40s]");
		sleep();
		
		//Add grafana page and adding datasource
		GrafanaMainPage grafanaMainPage = new GrafanaMainPage(driver);
		sleep();
		GrafanaHomeDashboard grafanaHomeDashboard = grafanaMainPage.login();
		sleep();
		GrafanaAddDataSource grafanaAddDataSource = grafanaHomeDashboard.addDataSource();
		sleep();		
		grafanaAddDataSource = grafanaAddDataSource.fillDataSourcePrometheus();
		sleep();
		grafanaAddDataSource = grafanaAddDataSource.getDashboardsTab();
		sleep();
		grafanaAddDataSource = grafanaAddDataSource.getDashboardInput();
		sleep();
		grafanaAddDataSource.getPrometheusStats();
		sleep();
		
		//end page
		driver.close();
		System.out.println("End demo spring boot application 00");
	}
	


}
