package com.qaprosoft.servicenow.gui.components;

import java.util.Map;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import static java.io.File.separator;
import static com.qaprosoft.servicenow.util.Constants.FILE_PATH;

public class AddAttachmentPopup extends AbstractUIObject {

	@FindBy(id = "attachFile")
	private ExtendedWebElement attachFileInput;
	
	@FindBy(id = "please_wait")
	private ExtendedWebElement uploadProgressBar;
	
	@FindBy(xpath = ".//button[contains(@class, 'close')]")
	private ExtendedWebElement closeButton;
	
	public AddAttachmentPopup(WebDriver driver, SearchContext searchContext) {
		super(driver, searchContext);
	}

	public void addAttachment(Map<String, String> data) {
		attachFileInput.getElement().sendKeys(FILE_PATH + "data" + separator + data.get("attachment"));
		if(isElementNotPresent(uploadProgressBar, 10)) {
			click(closeButton);
		}
		else
			Assert.fail("Can't upload file!!!");
	}
}
