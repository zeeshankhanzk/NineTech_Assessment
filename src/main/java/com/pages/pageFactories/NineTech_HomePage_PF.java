package com.pages.pageFactories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NineTech_HomePage_PF {

	WebDriver driver;

	public NineTech_HomePage_PF(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//div[@data-testid='SubscriptionPrompt-true']")
	private WebElement Eofy_Popup;

	public WebElement getEofy_Popup() {
		return Eofy_Popup;
	}

}
