package com.qaprosoft.servicenow.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.servicenow.gui.components.MainMenu;
import com.qaprosoft.servicenow.gui.components.UserMenu;

public abstract class ServiceNowAbstractPage extends AbstractPage {

	@FindBy(xpath = "//nav[@class='navpage-nav']")
	private MainMenu mainMenu;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu']")
	private UserMenu userMenu;
	
	@FindBy(id = "user_info_dropdown")
	private ExtendedWebElement userInfoButton;
	
	@FindBy(className = "user-name")
	private ExtendedWebElement userNameText;
	
	public ServiceNowAbstractPage(WebDriver driver) {
		super(driver);
	}

	public String getUserName() {
		return userNameText.getText();
	}
	
	public ProfilePage openProfilePage() {
		click(userInfoButton);
		userMenu.profilePage();
		return new ProfilePage(driver);
	}
	
	public NewIncidentPage openNewIncedentPage() {
		mainMenu.createNewIncident();
		return new NewIncidentPage(driver);
	}
	
	public LoginPage logout() {
		click(userInfoButton);
		userMenu.logout();
		return new LoginPage(driver);
	}
}
