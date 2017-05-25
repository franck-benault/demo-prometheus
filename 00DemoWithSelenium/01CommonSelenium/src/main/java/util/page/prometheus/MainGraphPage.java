package util.page.prometheus;


import java.util.concurrent.TimeUnit;

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
	
	@FindBy(how=How.ID, using="expr0")
	private WebElement expr0;

	@FindBy(how=How.XPATH, using=
			"//div/input[@type='submit']")
	private WebElement submit;
	
	@FindBy(how=How.XPATH, using="//a[@href='#graph0']")
	//href="#graph0"
	private WebElement graph;
	
	

	public MainGraphPage(WebDriver driver, String url) {
		super(driver);
		driver.get(url);
		
		PageFactory.initElements(driver, this);

	}
	
	public MainGraphPage(WebDriver driver) {
		super(driver);
		
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
	
	public MainGraphPage fillAndDrawGraph(String request) {
		this.expr0.clear();
		this.expr0.sendKeys(request);
		expr0.submit();
		submit.click();

		
		
		graph.click();
		
		//submit.submit();
		//return  new MainGraphPage(driver);
		MainGraphPage page =new MainGraphPage(driver);
		return page;
	}
	
	public MainGraphPage fillGraphConsole(String request) {
		this.expr0.clear();
		this.expr0.sendKeys(request);
		expr0.submit();
		submit.click();	
		
		
		//submit.submit();
		//return  new MainGraphPage(driver);
		MainGraphPage page =new MainGraphPage(driver);
		return page;
	}
}
