package util.page.grafana;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import util.page.Page;

public class GrafanaAddDataSource extends Page {
	
	@FindBy(how=How.XPATH, using="(//input)[1]")
	private WebElement nameInput;
	

	
	@FindBy(how=How.XPATH, using="(//select)[1]")
	private WebElement sourceType;
	
	@FindBy(how=How.LINK_TEXT, using="Dashboards")
	private WebElement dashboardsTab;
	

	@FindBy(how=How.XPATH, using="//button[@ng-show='!dash.imported']")
	private WebElement importButton;

	@FindBy(how=How.LINK_TEXT, using="Prometheus Stats")
	private WebElement prometheusStatsLink;
	
	

	public GrafanaAddDataSource(String url, WebDriver driver) {
		super(driver);
		driver.get(url);
		
		//System.out.println(driver.getPageSource());
		
		PageFactory.initElements(driver, this);

	}
	
	public GrafanaAddDataSource(WebDriver driver) {
		super(driver);
	
		PageFactory.initElements(driver, this);

	}

	public GrafanaAddDataSource fillDataSourcePrometheus() {
		
		
		Select dropdown = new Select(sourceType);
		dropdown.selectByVisibleText("Prometheus");
		nameInput.clear();
		
		
		nameInput.sendKeys("prometheus");
		//urlInput.clear();
		//urlInput.sendKeys("http://localhost:9090");
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement element = driver.findElement(By.xpath("(//input)[3]"));
		element.clear();
		element.sendKeys("http://localhost:9090");
		
		WebElement access = driver.findElement(By.xpath("(//select)[2]"));
		
		Select selectAccess = new Select(access);
		selectAccess.selectByVisibleText("direct");
		
		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();
		
		return new GrafanaAddDataSource(driver);
		
	}
	
	public GrafanaAddDataSource getDashboardsTab() {
		dashboardsTab.click();
		return new GrafanaAddDataSource(driver);
	}
	
	
	public GrafanaAddDataSource getDashboardInput() {
		importButton.click();
		return new GrafanaAddDataSource(driver);
	}
	
	public GrafanaAddDataSource getPrometheusStats() {
		prometheusStatsLink.click();
		return new GrafanaAddDataSource(driver);
	}
	
	

}
