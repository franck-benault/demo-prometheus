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
	
	@FindBy(how=How.ID, using="expr0")
	private WebElement expr0;
	
	@FindBy(how=How.ID, using="expr1")
	private WebElement expr1;

	@FindBy(how=How.XPATH, using=
			"//div/input[@type='submit'][1]")
	private WebElement submit0;
	
	@FindBy(how=How.XPATH, using=
			"//div/input[@type='submit'][last()]")
	private WebElement submit1;
	
	@FindBy(how=How.XPATH, using="//a[@href='#graph0']")
	private WebElement graph0;
	
	@FindBy(how=How.XPATH, using="//a[@href='#graph1']")
	private WebElement graph1;
	
	@FindBy(how=How.XPATH, using="//a[@href='#console1']")
	private WebElement console1;
	
	@FindBy(how=How.XPATH, using="//button[@name='dec_range'][1]")
	private WebElement graphMinus0;
	
	@FindBy(how=How.XPATH, using="//button[@name='dec_range'][last()]")
	WebElement graphMinus1;

	@FindBy(how=How.XPATH, using="//input[@id='range_input0']")
	WebElement rangeInput0;
	
	@FindBy(how=How.XPATH, using="//input[@id='range_input1']")
	WebElement rangeInput1;

	
	@FindBy(how=How.XPATH, using="//input[@value='Add Graph']")
	private WebElement addGraphButton;

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
		submit0.click();
	
		graph0.click();
		graphMinus0.click();
		graphMinus0.click();
		
		//rangeInput0.clear();
		//rangeInput0.sendKeys("15m");
		submit0.click();
		driver.navigate().refresh();
		
		//submit.submit();
		//return  new MainGraphPage(driver);
		MainGraphPage page =new MainGraphPage(driver);
		return page;
	}
	
	public MainGraphPage addGraph(String request) {
		this.addGraphButton.click();
		
		this.expr1.clear();
		this.expr1.sendKeys(request);
		expr1.submit();
		submit1.click();
	
		graph1.click();
		
		rangeInput1.clear();
		rangeInput1.sendKeys("15m");
		submit1.click();
		
		driver.navigate().refresh();
		
		MainGraphPage page =new MainGraphPage(driver);
		return page;
	}
	
	public MainGraphPage fillGraphConsole(String request) {
		this.expr0.clear();
		this.expr0.sendKeys(request);
		expr0.submit();
		submit0.click();	
		
		
		//submit.submit();
		//return  new MainGraphPage(driver);
		MainGraphPage page =new MainGraphPage(driver);
		return page;
	}
}
