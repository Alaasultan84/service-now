package com.qaprosoft.servicenow.gui.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

public class AllIncedentsPage extends ServiceNowAbstractPage
{
	public AllIncedentsPage(WebDriver driver)
	{
		super(driver);
	}

	public NewIncidentPage openIncidentPage(String number) {
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//a[text()='" + number + "']")).click();
		driver.switchTo().defaultContent();
		return new NewIncidentPage(driver);
	}
	
	public Map<String, String> getRowDataByIncidentNumber(String number)
	{
		Map<String, String> result = new HashMap<String, String>();
		driver.switchTo().frame("gsft_main");
		ExtendedWebElement row = new ExtendedWebElement(driver.findElement(By.xpath("//a[text()='" + number + "']/../..")));
		result.put("short.description", row.findExtendedWebElement(By.xpath("./td[5]")).getText());
		result.put("caller", row.findExtendedWebElement(By.xpath("./td[6]")).getText());
		result.put("priority", row.findExtendedWebElement(By.xpath("./td[7]")).getText());
		result.put("state", row.findExtendedWebElement(By.xpath("./td[8]")).getText());
		result.put("category", row.findExtendedWebElement(By.xpath("./td[9]")).getText());
		result.put("assignment.group", row.findExtendedWebElement(By.xpath("./td[10]")).getText());
		result.put("assigned.to", row.findExtendedWebElement(By.xpath("./td[11]")).getText());
		result.put("updated.by", row.findExtendedWebElement(By.xpath("./td[13]")).getText());
		driver.switchTo().defaultContent();
		return result;
	}
}
