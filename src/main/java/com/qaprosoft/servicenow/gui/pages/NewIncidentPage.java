package com.qaprosoft.servicenow.gui.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.servicenow.gui.components.AddAttachmentPopup;

public class NewIncidentPage extends ServiceNowAbstractPage
{
	private final static String SELECTED_XPATH = "./option[@selected='SELECTED']";
	
	@FindBy(xpath = "//div[@id='attachment']")
	private AddAttachmentPopup attachmentPopup;
	
	@FindBy(id = "sys_readonly.incident.number")
	private ExtendedWebElement incidentNumberInput;
	
	@FindBy(id = "sys_display.incident.caller_id")
	private ExtendedWebElement callerIdInput;
	
	@FindBy(id = "incident.category")
	private ExtendedWebElement categoryDropdown;
	
	@FindBy(id = "incident.subcategory")
	private ExtendedWebElement subcategoryDropdown;
	
	@FindBy(id = "sys_display.incident.business_service")
	private ExtendedWebElement businessServiceInput;
	
	@FindBy(id = "sys_display.incident.cmdb_ci")
	private ExtendedWebElement configItemInput;
	
	@FindBy(id = "incident.contact_type")
	private ExtendedWebElement contactTypeDropdown;
	
	@FindBy(id = "sys_display.incident.assignment_group")
	private ExtendedWebElement assignmentGroupInput;
	
	@FindBy(id = "sys_display.incident.assigned_to")
	private ExtendedWebElement assignedToInput;
	
	@FindBy(id = "incident.short_description")
	private ExtendedWebElement shortDescriptionInput;
	
	@FindBy(id = "incident.watch_list_unlock")
	private ExtendedWebElement watcherUnclockButton;
	
	@FindBy(id = "sys_display.incident.watch_list")
	private ExtendedWebElement watcherListInput;
	
	@FindBy(id = "incident.comments")
	private ExtendedWebElement commentsInput;
	
	@FindBy(id = "incident.work_notes")
	private ExtendedWebElement workNotesInput;
	
	@FindBy(xpath = "//div[@id='tabs2_section']/span[3]/span")
	private ExtendedWebElement closeInformationTab;
	
	@FindBy(id = "incident.close_code")
	private ExtendedWebElement closeCodeDropdown;
	
	@FindBy(id = "incident.close_notes")
	private ExtendedWebElement closeNotesInput;
	
	@FindBy(id = "sysverb_insert")
	private ExtendedWebElement submitButton;
	
	@FindBy(xpath = "//ul[contains(@class, 'activities-form')]/li//span[contains(@class, 'sn-widget-textblock-body')]")
	private List<ExtendedWebElement> activities;
	
	@FindBy(id = "incident.watch_list_nonedit")
	private ExtendedWebElement watcherList;
	
	@FindBy(id = "header_add_attachment")
	private ExtendedWebElement addAttachmentButton;
	
	@FindBy(id = "sysverb_update")
	private ExtendedWebElement updateIncidentButton;
	
	@FindBy(id = "resolve_incident")
	private ExtendedWebElement resolveIncidentButton;
	
	@FindBy(id = "header_attachment_list_label")
	private ExtendedWebElement attachmentListLabel;
	
	public NewIncidentPage(WebDriver driver)
	{
		super(driver);
	}

	public String createNewIncident(Map<String, String> data)
	{
		driver.switchTo().frame("gsft_main");
		String incidentnumber = incidentNumberInput.getAttribute("value");
		type(callerIdInput , data.get("caller"));
		select(categoryDropdown , data.get("category"));
		select(subcategoryDropdown , data.get("subcategory"));
		type(businessServiceInput , data.get("business.service"));
		type(configItemInput , data.get("configuration"));
		select(contactTypeDropdown , data.get("contact"));
		type(assignmentGroupInput , data.get("assignment.group"));
		type(assignedToInput , data.get("assigned.to"));
		type(shortDescriptionInput , data.get("short.description"));
		click(watcherUnclockButton);
		type(watcherListInput , data.get("watcher"));
		type(commentsInput , data.get("additional.comment"));
		type(workNotesInput , data.get("work.notes"));
		pause(5);
		click(submitButton);
		driver.switchTo().defaultContent();
		return incidentnumber;
	}
	
	public Map<String, String> getIncidentFormData() {
		driver.switchTo().frame("gsft_main");
		Map<String, String> result = new HashMap<String, String>();
		result.put("incidentNumber", incidentNumberInput.getAttribute("value"));
		result.put("caller", callerIdInput.getAttribute("value"));
		result.put("category", categoryDropdown.findExtendedWebElement(By.xpath(SELECTED_XPATH)).getText());
		result.put("subcategory", subcategoryDropdown.findExtendedWebElement(By.xpath(SELECTED_XPATH)).getText());
		result.put("business.service", businessServiceInput.getAttribute("value"));
		result.put("configuration", configItemInput.getAttribute("value"));
		result.put("contact", contactTypeDropdown.findExtendedWebElement(By.xpath(SELECTED_XPATH)).getText());
		result.put("assignment.group", assignmentGroupInput.getAttribute("value"));
		result.put("assigned.to", assignedToInput.getAttribute("value"));
		result.put("short.description", shortDescriptionInput.getAttribute("value"));
		result.put("watcher", watcherList.getText());
		result.put("additional.comment", activities.get(0).getText());
		result.put("work.notes", activities.get(1).getText());
		driver.switchTo().defaultContent();
		return result;
	}
	
	public void addAttachment(Map<String, String> data) {
		driver.switchTo().frame("gsft_main");
		click(addAttachmentButton);
		attachmentPopup.addAttachment(data);
		driver.switchTo().defaultContent();
	}
	
	public void updateIncident() {
		driver.switchTo().frame("gsft_main");
		click(updateIncidentButton);
		driver.switchTo().defaultContent();
	}
	
	public String getAttachmentLabel() {
		driver.switchTo().frame("gsft_main");
		String result = attachmentListLabel.getText();
		driver.switchTo().defaultContent();
		return result;
	}
	
	public void resolveIncident(Map<String, String> data) {
		driver.switchTo().frame("gsft_main");
		click(closeInformationTab);
		select(closeCodeDropdown , data.get("close.code"));
		type(closeNotesInput, data.get("close.notes"));
		click(resolveIncidentButton);
		driver.switchTo().defaultContent();
	}
}
