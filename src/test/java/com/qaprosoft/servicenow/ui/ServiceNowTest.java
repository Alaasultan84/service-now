package com.qaprosoft.servicenow.ui;

import static com.qaprosoft.servicenow.models.UserRoles.ITIL;
import static com.qaprosoft.servicenow.models.UserRoles.NO_ROLES;
import static com.qaprosoft.servicenow.util.Constants.SERVICE_NOW_URL;
import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.UITest;
import com.qaprosoft.servicenow.gui.pages.AllIncedentsPage;
import com.qaprosoft.servicenow.gui.pages.LoginPage;
import com.qaprosoft.servicenow.gui.pages.NewIncidentPage;
import com.qaprosoft.servicenow.gui.pages.ProfilePage;
import com.qaprosoft.servicenow.gui.pages.ServiceNowAbstractPage;
import com.qaprosoft.servicenow.models.User;
import com.qaprosoft.servicenow.util.AppFactory;

/**
 * Service now login tests
 * 
 * @author Andrey Nazarenko
 */
public class ServiceNowTest extends UITest
{
	private final static String NEW_INCIDENT_DATA = "data/new_incident.properties";
	
	@Test()
	public void testNoRolesLogin()
	{
		User user = User.getUserInstance(NO_ROLES);
		LoginPage loginPage = new LoginPage(getDriver(), SERVICE_NOW_URL);
		ServiceNowAbstractPage landingPage = loginPage.login(user);
		assertEquals(landingPage.getUserName(), "System Administrator", "Can't login with 'No Roles' user!");
		ProfilePage profile = landingPage.openProfilePage();
		assertEquals(profile.getPageTitle(), "User", "Page title is wrong!");
		loginPage = landingPage.logout(); 
		assertEquals(loginPage.getLoginButtonText(), "Login", "Login page is not displayed!");
	}
	
	@Test()
	public void testItilLogin()
	{
		User user = User.getUserInstance(ITIL);
		LoginPage loginPage = new LoginPage(getDriver(), SERVICE_NOW_URL);
		ServiceNowAbstractPage landingPage = loginPage.login(user);
		assertEquals(landingPage.getUserName(), "ITIL User", "Can't login with 'Itil' user!");
		ProfilePage profile = landingPage.openProfilePage();
		assertEquals(profile.getPageTitle(), "User", "Page title is wrong!");
		loginPage = landingPage.logout();
		assertEquals(loginPage.getLoginButtonText(), "Login", "Login page is not displayed!");
	}
	
	@Test()
	public void testIncident()
	{
		Map<String, String> data = AppFactory.getInstance(NEW_INCIDENT_DATA).create();
		User user = User.getUserInstance(ITIL);
		LoginPage loginPage = new LoginPage(getDriver(), SERVICE_NOW_URL);
		ServiceNowAbstractPage landingPage = loginPage.login(user);
		NewIncidentPage incidentForm = landingPage.openNewIncedentPage();
		String incidentNumber = incidentForm.createNewIncident(data);
		AllIncedentsPage allIncidentsPage = new AllIncedentsPage(getDriver());
		data.put("priority", "5 - Planning"); data.put("state", "In Progress"); data.put("updated.by", "itil"); 
		checkIncidentRow(allIncidentsPage.getRowDataByIncidentNumber(incidentNumber), data);
	    incidentForm = allIncidentsPage.openIncidentPage(incidentNumber);
	    checkIncidentForm(incidentForm.getIncidentFormData(), data);
	    incidentForm.addAttachment(data);
	    assertEquals(incidentForm.getAttachmentLabel(), "Manage Attachments (1):", "Wrong attachment label!");
	    incidentForm.updateIncident();
	    checkIncidentRow(allIncidentsPage.getRowDataByIncidentNumber(incidentNumber), data);
	    incidentForm = allIncidentsPage.openIncidentPage(incidentNumber);
	    incidentForm.resolveIncident(data);
	    data.put("state", "Resolved");
	    checkIncidentRow(allIncidentsPage.getRowDataByIncidentNumber(incidentNumber), data);
	}
	
	private void checkIncidentForm(Map<String, String> actual, Map<String, String> expected) {
		assertEquals(actual.get("caller"), expected.get("caller"), "Wrong 'caller' in the incident form!");
		assertEquals(actual.get("category"), expected.get("category"), "Wrong 'category' in the incident form!");
		assertEquals(actual.get("subcategory"), expected.get("subcategory"), "Wrong 'subcategory' in the incident form!");
		assertEquals(actual.get("business.service"), expected.get("business.service"), "Wrong 'business.service' in the incident form!");
		assertEquals(actual.get("configuration"), expected.get("configuration"), "Wrong 'configuration' in the incident form!");
		assertEquals(actual.get("contact"), expected.get("contact"), "Wrong 'contact' in the incident form!");
		assertEquals(actual.get("assignment.group"), expected.get("assignment.group"), "Wrong 'assignment.group' in the incident form!");
		assertEquals(actual.get("assigned.to"), expected.get("assigned.to"), "Wrong 'assigned.to' in the incident form!");
		assertEquals(actual.get("short.description"), expected.get("short.description"), "Wrong 'short.description' in the incident form!");
		assertEquals(actual.get("watcher"), expected.get("watcher"), "Wrong 'watcher' in the incident form!");
		assertEquals(actual.get("additional.comment"), expected.get("additional.comment"), "Wrong 'additional.comment' in the incident form!");
		assertEquals(actual.get("work.notes"), expected.get("work.notes"), "Wrong 'work.notes' in the incident form!");
	}
	
	private void checkIncidentRow(Map<String, String> actual, Map<String, String> expected) {
		assertEquals(actual.get("short.description"), expected.get("short.description"), "Short description is wrong!");
		assertEquals(actual.get("caller"), expected.get("caller"), "Caller is wrong!");
		assertEquals(actual.get("priority"), expected.get("priority"), "Priority is wrong!");
		assertEquals(actual.get("state"), expected.get("state"), "State is wrong!");
		assertEquals(actual.get("category"), expected.get("category"), "Category is wrong!");
		assertEquals(actual.get("assignment.group"), expected.get("assignment.group"), "Assignment group is wrong!");
		assertEquals(actual.get("assigned.to"), expected.get("assigned.to"), "Assigned to is wrong!");
		assertEquals(actual.get("updated.by"), expected.get("updated.by"), "Updated by is wrong!");
	}
}
