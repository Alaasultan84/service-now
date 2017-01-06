package com.qaprosoft.servicenow.gui.pages;

import static com.qaprosoft.servicenow.models.UserRoles.NO_ROLES;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.servicenow.models.User;

public class LoginPage extends AbstractPage
{
	@FindBy(id = "user_name")
	private ExtendedWebElement userNameInput;
	
	@FindBy(id = "user_password")
	private ExtendedWebElement passwordInput;
	
	@FindBy(id = "sysverb_login")
	private ExtendedWebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage(WebDriver driver, String url) {
		super(driver);
		setPageURL(url);
		open();
	}
	
	public ServiceNowAbstractPage login(User user) {
		driver.switchTo().frame("gsft_main");
		type(userNameInput, user.getUserName());
		type(passwordInput, user.getPassword());
		click(loginButton);
		driver.switchTo().defaultContent();
		pause(10);
		return user.getRole() == NO_ROLES ? new LandingPage(driver) : new LandingPage(driver);
	}
	
	public String getLoginButtonText() {
		driver.switchTo().frame("gsft_main");
		String text = loginButton.getText();
		driver.switchTo().defaultContent();
		return text;
	}
}
