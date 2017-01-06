package com.qaprosoft.servicenow.gui.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;

public class MainMenu extends AbstractUIObject {

	@FindBy(xpath = ".//a/span[text()='Incident']")
	private ExtendedWebElement incidentLink;
	
	@FindBy(id = "14641d70c611228501114133b3cc88a1")
	private ExtendedWebElement createNewLink;
	
	public MainMenu(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}
	
	public void createNewIncident() { 
		click(incidentLink);
		click(createNewLink);
	}
}
