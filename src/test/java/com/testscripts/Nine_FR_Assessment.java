package com.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.pageFactories.NineTech_HomePage_PF;
import com.utilities.Common_Utilities;

public class Nine_FR_Assessment extends Common_Utilities {

	@Test
	public void TC_01_EndOfSeasonSale_PopUp() throws InterruptedException {

		// Step 1 (Launch this URL)
		logger.info("Step-1 : Launch the application URL");
		String url = (String) properties.get("application.url");
		navigateToURL(url);

		// Step-2 (Check if the subscription prompt is popped up from the bottom of the
		// page)
		logger.info("Step-2 : Validating that the subscription prompt is popped up from the bottom of the page");
		NineTech_HomePage_PF homepage = new NineTech_HomePage_PF(driver);
		boolean popup_Status = homepage.getEofy_Popup().isDisplayed();
		Assert.assertTrue(popup_Status,
				"Validation for the subscription prompt is popped up from the bottom of the page");
		logger.info("Capture screenshot for Step-2");
		captureScreenshot("Screenshot_1");

		// Step-3 (Scroll down to the end of the page)
		logger.info("Step-3 : Scrolling down to the end of the page");
		scrollToBottom();
		logger.info("Capture screenshot for Step-3");
		captureScreenshot("Screenshot_2");

		// Step 4 - (Wait for 10 seconds (utmost))
		logger.info("Step-4 : Wait for 10 seconds (utmost)");
		holdScriptForSomeTime(4);
		logger.info("Capture screenshot for Step-4");
		captureScreenshot("Screenshot_3");

		// Step 5 - (Verify if the subscription pop up disappears on the same article)
		logger.info("Step-5 : Validating that the subscription prompt is disappeared");
		captureScreenshot("Screenshot_4");
		logger.info("Capture screenshot for Step-5");
		Assert.assertFalse(getWebElementStatus(homepage.getEofy_Popup()),
				"Validation for the subscription prompt is disappeared");

	}

}
