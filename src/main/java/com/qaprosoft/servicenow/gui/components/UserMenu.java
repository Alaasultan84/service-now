package com.qaprosoft.servicenow.gui.components;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;

public class UserMenu extends AbstractUIObject {

	@FindBy(linkText  = "Profile")
	private ExtendedWebElement profileLink;
	
	@FindBy(linkText = "Impersonate User")
	private ExtendedWebElement impersonateUserLink;
	
	@FindBy(linkText = "Elevate Roles")
	private ExtendedWebElement elevateRolesLink;
	
	@FindBy(linkText = "Logout")
	private ExtendedWebElement logoutLink;
	
	public UserMenu(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	public void profilePage() {
		click(profileLink);
	}
	
	public void logout() { 
		click(logoutLink);
	}
}
