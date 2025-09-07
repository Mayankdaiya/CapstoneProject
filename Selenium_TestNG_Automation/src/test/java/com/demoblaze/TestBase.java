package com.demoblaze;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class TestBase {
    protected static WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        // load details from resources file includes url and browser before suite
        ConfigReader.loadConfig();
    }

    @BeforeTest
    public void setup() {
        // initialize driver and get url and browser from config reader before test
        driver = DriverFactory.initializeDriver(ConfigReader.get("browser"));
        driver.get(ConfigReader.get("url"));
    }

    @AfterSuite
    public void teardown() {
        // quit the driver after suit
        DriverFactory.quitDriver();
    }
}
