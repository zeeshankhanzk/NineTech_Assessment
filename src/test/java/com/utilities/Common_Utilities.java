package com.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.TestBase;

public class Common_Utilities extends TestBase {
	public final static String ScreenshotsPath_DuringScriptRun = ".//screenshots//";

	public void scrollToBottom() {
		//holdScriptForSomeTime(1);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
	}

	protected void holdScriptForSomeTime(long timeOutInSeconds) {
		waitForElementAndIgnoreException(timeOutInSeconds, By.xpath("abc132561def"));
	}

	public WebDriverWait getWait(long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait;
	}

	public static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		};
	}

	protected void waitForElementAndIgnoreException(long timeOutInSeconds, By by) {
		try {
			getWait(timeOutInSeconds).until(presenceOfElementLocated(by));
		} catch (Exception e) {

		}
	}

	public void captureScreenshot(String screenshotNumber) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotName = screenshotNumber + ".jpg";
		try {
			FileUtils.copyFile(srcFile, new File(ScreenshotsPath_DuringScriptRun + screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public boolean getWebElementStatus(WebElement element) {
		try {
			element.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		}
		return true;
	}

	public void navigateToURL(String url) {
		logger.info("Navigating to the URL: " + url);
		driver.get(url);
	}

}
