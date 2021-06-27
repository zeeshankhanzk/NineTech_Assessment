package com.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	private static final String OBJECT_REPO_PROPERTIES = ".//src//test//resources//data.properties";
	private static final String LOG4J_Config_FILE = ".//src//test//resources//properties//log4j.properties";
	protected WebDriver driver;
	protected Properties properties;
	protected Logger logger;

	@BeforeMethod
	public void preSetup() {

		loadForReadingPropertiesFile();
		logger = doSetupForLogging();

		WebDriverManager.chromedriver().setup();
		// Launching Browser window
		logger.info("Opening Chrome Browser");
		driver = new ChromeDriver();

		logger.info("Maximizing window");
		driver.manage().window().maximize();

		int implicitWait = Integer.parseInt((String) properties.get("wait.implicit"));
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

	}

	private void loadForReadingPropertiesFile() {

		try {
			FileReader reader = new FileReader(OBJECT_REPO_PROPERTIES);
			properties = new Properties();
			properties.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Logger doSetupForLogging() {
		// We need to create a Logger instance, so we need to pass Class name
		// for which, we want to create Log file
		Logger logger = Logger.getLogger(TestBase.class);
		PropertyConfigurator.configure(LOG4J_Config_FILE);
		return logger;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		logger.info("Closing the Browser");
		logger.info("Driver instance is killed");
		driver.quit();
	}

}
