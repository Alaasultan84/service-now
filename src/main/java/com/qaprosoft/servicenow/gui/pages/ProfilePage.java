package com.qaprosoft.servicenow.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

public class ProfilePage extends ServiceNowAbstractPage
{
	@FindBy(className = "navbar-title-caption")
	private ExtendedWebElement pageTitleCaptionText;
	
	public ProfilePage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getPageTitle() {
		driver.switchTo().frame("gsft_main");
		String text = pageTitleCaptionText.getText();
		driver.switchTo().defaultContent();
		return text;
	}
}
