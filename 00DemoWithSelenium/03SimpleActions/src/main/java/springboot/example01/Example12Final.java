package springboot.example01;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import util.page.alertmanager.AlertManagerMainPage;
import util.page.grafana.GrafanaAddDataSource;
import util.page.grafana.GrafanaHomeDashboard;
import util.page.grafana.GrafanaMainPage;
import util.page.grokexporter.GrokExporterMetricsPage;
import util.page.prometheus.MainGraphPage;
import util.page.springapplication.SpringApplicationEndPointAPage;
import util.page.springapplication.SpringApplicationEndPointBPage;
import util.page.springapplication.SpringApplicationEndPointCPage;
import util.page.springapplication.SpringApplicationEndPointDPage;
import util.page.springapplication.SpringApplicationMainPage;

public class Example12Final {
	
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
	
	public static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	


	public static void main(String[] args) {
		System.out.println("Start demo spring boot application 12");
		driver = getWebDriver();
		
		//TITLE
		//driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/01a-Prometheus.jpg");
		//sleep(1);
		driver.get("http://localhost:8080/01Monitoring.html");
		sleep(15);
		
		//ARCHITECTURE
		driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/01c-architecture.png");
		sleep(25);
		
		//MY SPRING BOOT APPLICATION
		driver.get("http://localhost:8080/02SpringBoot.html");
		sleep(25);
		new SpringApplicationMainPage(driver);
		sleep(1);	
		new SpringApplicationEndPointAPage(driver);
		sleep(1);	
		new SpringApplicationEndPointBPage(driver);
		sleep(1);	
		new SpringApplicationEndPointCPage(driver);
		sleep(1);	
		new SpringApplicationEndPointDPage(driver);
		sleep(1);
		
		//JMETER
		driver.get("http://localhost:8080/03JMeter.html");
		sleep(25);
		
		//PROMETHEUS
		driver.get("http://localhost:8080/04Prometheus.html");
		sleep(15);
		MainGraphPage page =new MainGraphPage(driver, "http://localhost:9090/graph");
		sleep(5);
		page.getConfigPage();
		sleep(5);
		page =new MainGraphPage(driver, "http://localhost:9090/graph");
		page.getTargetsPage();
		sleep(5);
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page.fillAndDrawGraph("up");
		sleep(5);
		
		//ALERT MANAGER
		driver.get("http://localhost:8080/05Alertmanager.html");
		new AlertManagerMainPage(driver);
		sleep(5);
		
		//GRAFANA
		driver.get("http://localhost:8080/06Grafana.html");
		sleep(25);
		GrafanaMainPage grafanaMainPage = new GrafanaMainPage(driver);
		sleep(3);
		GrafanaHomeDashboard grafanaHomeDashboard = grafanaMainPage.login();
		sleep(3);
		GrafanaAddDataSource grafanaAddDataSource = grafanaHomeDashboard.addDataSource();
		sleep(3);		
		grafanaAddDataSource = grafanaAddDataSource.fillDataSourcePrometheus();
		sleep(3);
		grafanaAddDataSource = grafanaAddDataSource.getDashboardsTab();
		sleep(3);
		grafanaAddDataSource = grafanaAddDataSource.getDashboardInput();
		sleep(3);
		grafanaAddDataSource.getPrometheusStats();
		sleep(10);
		
		//BLACK BOX MONITORING
		driver.get("http://localhost:8080/07BlackBoxMonitoring.html");
		sleep(25);
		
		//LOGGING
		driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/01c-architecture2.png");
		sleep(15);
		driver.get("file:///D:/workspace/demo-prometheus/90Docker/applispring/logs/mylogfiless.log");
		sleep(15);
		
		new GrokExporterMetricsPage(driver);
		sleep(15);			
		
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("grok_log_lines_total{class=\"hello.HelloController\"}");
		sleep(15);
		page = page.fillAndDrawGraph("100*sum(60*rate(grok_log_lines_total{class=\"hello.HelloController\", level=\"ERROR\", method=\"index\"}[1m])) / sum(60*rate(grok_log_lines_total{class=\"hello.HelloController\", method=\"index\"}[1m]))");
		sleep(15);
				
		//jvm
		//TODO
		
		//TODO create a page to check
		driver.get("http://localhost:8080/08WhiteBoxMonitoring.html");
		sleep(25);
		
		//TODO create a page to check
		driver.get("http://localhost:8080/09MetricCounter.html");
		sleep(15);
		
		//COUNTER
		driver.get("http://localhost:8080/metricsCounter");
		sleep(15);
		driver.get("http://localhost:8080/metricsCounter");
		sleep(5);
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("http_requests_total");
		sleep(15);
		
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawTwoGraphes("100*sum(60*rate(http_requests_total{ status=\"ERROR\" , method=\"index\"}[1m])) / sum(60*rate(http_requests_total{method=\"index\"}[1m]))",
		"100*sum(60*rate(grok_log_lines_total{class=\"hello.HelloController\", level=\"ERROR\", method=\"index\"}[1m])) / sum(60*rate(grok_log_lines_total{class=\"hello.HelloController\", method=\"index\"}[1m]))");
		sleep(15);

		//GAUGE
		//TODO create a page 
		driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/04Gauge.jpg");
		sleep(15);
		driver.get("http://localhost:8080/metricsGauge");
		sleep(15);
		driver.get("http://localhost:8080/metricsGauge");
		sleep(5);	
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("nb_temporary_files");
		sleep(15);
		
		
		//SUMMARY
		//TODO create a page 
		driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/05Summary.jpg");
		sleep(15);
		
		//TODO servlet metrics page to check
		driver.get("http://localhost:8080/metricsSummary");
		sleep(15);
		driver.get("http://localhost:8080/metricsSummary");
		sleep(5);
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("response_time_summary_sum");
		sleep(15);
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("response_time_summary_count");
		sleep(15);
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("response_time_summary*1000");
		sleep(15);
		
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("rate(response_time_summary_sum{method=\"endpointB\"}[1m])/rate(response_time_summary_count{method=\"endpointB\"}[1m])*1000");
		sleep(15);
		
		//HISTOGRAM
		//TODO create a page 
		driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/06Histogram.png");
		sleep(15);
		driver.get("http://localhost:8080/metricsHistogram");
		sleep(15);
		driver.get("http://localhost:8080/metricsHistogram");
		sleep(5);
		
		
		//TODO add example
		
		//response_time_histogram_bucket
		
		//TODO Guava ?
		
		//TODO Question image
		
		//end page
		driver.close();
		System.out.println("End demo spring boot application 12");
	}
}