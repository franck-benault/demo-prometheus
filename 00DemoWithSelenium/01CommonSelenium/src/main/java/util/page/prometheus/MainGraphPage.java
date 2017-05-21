package util.page.prometheus;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import util.page.Page;

public class MainGraphPage  extends Page {

	@FindBy(how=How.PARTIAL_LINK_TEXT, using="Status")
	private WebElement statusLink;
	
	@FindBy(how=How.LINK_TEXT, using="Configuration")
	private WebElement configurationLink;
	
	@FindBy(how=How.LINK_TEXT, using="Rules")
	private WebElement rulesLink;
	
	@FindBy(how=How.LINK_TEXT, using="Alerts")
	private WebElement alertsLink;

	@FindBy(how=How.LINK_TEXT, using="Targets")
	private WebElement targetsLink;
	

	public MainGraphPage(WebDriver driver) {
		super(driver);
		driver.get("http://localhost:9090/graph");
		
		PageFactory.initElements(driver, this);

	}

	public void checkPage() {
		driver.getTitle().contains("Prometheus Time Series Collection and Processing Server");
	}

	public ConfigPage getConfigPage() {
		
		statusLink.click();
		configurationLink.click();
		return new ConfigPage(driver);
	}
	
	public PrometheusTargetsPage getTargetsPage() {
		
		statusLink.click();
		targetsLink.click();
		return new PrometheusTargetsPage(driver);
	}
	
	public PrometheusRulesPage getRulesPage() {
		
		statusLink.click();
		rulesLink.click();
		return new PrometheusRulesPage(driver);
	}

	public PrometheusAlertsPage getAlerts() {
		alertsLink.click();
		return  new PrometheusAlertsPage(driver);
	}
}
