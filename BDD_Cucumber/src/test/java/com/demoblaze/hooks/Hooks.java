package com.demoblaze.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class Hooks {
    protected static WebDriver driver;

    // Run before all scenario's
    @BeforeAll
    public static void beforeAll() {
        // Loads config like URL, Browser, etc.
        ConfigReader.loadConfig();
        // initialize driver and navigate to url using configReader get method
        driver = DriverFactory.initializeDriver(ConfigReader.get("browser"));
        driver.get(ConfigReader.get("url"));
    }

    // Run after all scenario's
    @AfterAll
    public static void afterAll() {
        // Quit driver after suit
        DriverFactory.quitDriver();
    }

    // Run after the scenario
    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = this.driver;
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failed Screenshot");
        }
    }

    // Get WebDriver
    public static WebDriver getDriver() {
        return driver;
    }
}
