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
	
	private static void sleep(int seconds) {
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		/*try {
			reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void showTitle() {
		//TITLE
		//driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/01a-Prometheus.jpg");
		//sleep(1);
		driver.get("http://localhost:8080/01Monitoring.html");
		sleep(15);
	}
	
	private static void showArchitecture() {
		//ARCHITECTURE
		driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/01c-architecture.png");
		sleep(25);
	}
	
	private static void showSpringBootApplication() {
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
	}
	
	private static void showPrometheus() {
		//PROMETHEUS
		driver.get("http://localhost:8080/04Prometheus.html");
		sleep(15);
		MainGraphPage page =new MainGraphPage(driver, "http://localhost:9090/graph");
		sleep(5);
		page.getConfigPage();
		sleep(25);
		page =new MainGraphPage(driver, "http://localhost:9090/graph");
		page.getTargetsPage();
		sleep(25);
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page.fillAndDrawGraph("up");
		sleep(25);
	}
	
	private static void showJMeter() {
		//JMETER
		driver.get("http://localhost:8080/03JMeter.html");
		sleep(25);
	}
	
	private static void showAlertManager() {
		//ALERT MANAGER
		driver.get("http://localhost:8080/05Alertmanager.html");
		new AlertManagerMainPage(driver);
		sleep(25);
	}
	
	private static void showGrafana() {
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
		sleep(25);
	}
	
	private static void showBlackBoxMonitoring() {
		//BLACK BOX MONITORING
		driver.get("http://localhost:8080/07BlackBoxMonitoring.html");
		sleep(25);
	}
	
	private static void showLogExporter() {
		//LOGGING
		driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/01c-architecture2.png");
		sleep(15);
		driver.get("file:///D:/workspace/demo-prometheus/90Docker/applispring/logs/mylogfiless.log");
		sleep(15);
		
		new GrokExporterMetricsPage(driver);
		sleep(15);			
		
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("grok_log_lines_total{class=\"hello.HelloController\"}");
		sleep(15);
		page = page.fillAndDrawGraph("100*sum(60*rate(grok_log_lines_total{class=\"hello.HelloController\", level=\"ERROR\", method=\"index\"}[1m])) / sum(60*rate(grok_log_lines_total{class=\"hello.HelloController\", method=\"index\"}[1m]))");
		sleep(15);
	}
	
	private static void showJVMExporter() {
		//jvm
		driver.get("http://localhost:8080/07bisJavaMemory.html");
		sleep(15);
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("jvm_memory_bytes_used{area=\"heap\"}");
		sleep(25);
	}
	
	private static void showWhiteBoxMonitoring() {
		//WHITE BOX MONITORING
		driver.get("http://localhost:8080/08WhiteBoxMonitoring.html");
		sleep(25);
	}
	
	private static void showMetricCounter() {
		//COUNTER
		driver.get("http://localhost:8080/09MetricCounter.html");
		sleep(15);
		driver.get("http://localhost:8080/metricsCounter");
		sleep(15);
		driver.get("http://localhost:8080/metricsCounter");
		sleep(5);
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("http_requests_total");
		sleep(15);
		
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawTwoGraphes("100*sum(60*rate(http_requests_total{ status=\"ERROR\" , method=\"index\"}[1m])) / sum(60*rate(http_requests_total{method=\"index\"}[1m]))",
		"100*sum(60*rate(grok_log_lines_total{class=\"hello.HelloController\", level=\"ERROR\", method=\"index\"}[1m])) / sum(60*rate(grok_log_lines_total{class=\"hello.HelloController\", method=\"index\"}[1m]))");
		sleep(15);
	}
	
	private static void showMetricGauge() {
		//GAUGE
		driver.get("http://localhost:8080/10MetricGauge.html");
		sleep(15);
		driver.get("http://localhost:8080/metricsGauge");
		sleep(15);
		driver.get("http://localhost:8080/metricsGauge");
		sleep(5);	
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("nb_temporary_files");
		sleep(15);
	}
	
	private static void showMetricSummary() {
		//SUMMARY
		driver.get("http://localhost:8080/11MetricSummary.html");
		sleep(15);
		driver.get("http://localhost:8080/metricsSummary");
		sleep(15);
		driver.get("http://localhost:8080/metricsSummary");
		sleep(5);
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
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
	}
	
	private static void showMetricHistogram() {
		//HISTOGRAM
		driver.get("http://localhost:8080/12MetricHistogram.html");
		sleep(15);
		//driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/06Histogram.png");
		//sleep(15);
		driver.get("http://localhost:8080/metricsHistogram");
		sleep(15);
		driver.get("http://localhost:8080/metricsHistogram");
		sleep(5);
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawGraph("rate(response_time_histogram_sum[1m]) / rate(response_time_histogram_count[1m]) * 1000");
		sleep(15);
		
		page = new MainGraphPage(driver, "http://localhost:9090/graph");
		page = page.fillAndDrawTwoGraphes("histogram_quantile(0.95, sum(rate(response_time_histogram_bucket[2m])) by (job, le)) * 1000",
				"histogram_quantile(0.85, sum(rate(response_time_histogram_bucket[2m])) by (job, le)) * 1000");
		sleep(15);
	}
	
	private static  void showQuestion() {
		driver.get("file:///D:/workspace/demo-prometheus/prometheus-images/ask-question.jpg");
		sleep(30);
		
	}
	
	private static void showMetricGuavaCache() {
		driver.get("http://localhost:8080/13MetricOthers.html");
		sleep(30);
		driver.get("http://localhost:8080/metricsGuavaCache");
		sleep(15);
		
		MainGraphPage page = new MainGraphPage(driver, "http://localhost:9090/graph");
		sleep(5);
		page = page.fillAndDrawTwoGraphes("rate(guava_cache_hit_total[1m])/rate(guava_cache_requests_total[1m])*100",
				"rate(guava_cache_miss_total[1m])/rate(guava_cache_requests_total[1m])*100 ");
		sleep(30);
	}
	
	public static void main(String[] args) {
		System.out.println("Start demo spring boot application 12");
		driver = getWebDriver();
		
		showTitle();
		
		showArchitecture();
		showSpringBootApplication();
		showJMeter();
		showPrometheus();
		showAlertManager();
		showGrafana();
		
		showBlackBoxMonitoring();
		showLogExporter();
		showJVMExporter();
		
		showWhiteBoxMonitoring();
		showMetricCounter();		
		showMetricGauge();		
		showMetricSummary();
		showMetricHistogram();
		
		showMetricGuavaCache();
		showQuestion();

		//end page
		driver.close();
		System.out.println("End demo spring boot application 12");
	}
}