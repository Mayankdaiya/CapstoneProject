package com.demoblaze.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features/cartcheckoutprocess.feature",
        glue = {"com.demoblaze.stepdefinitions", "com.demoblaze.hooks"},
        tags = "",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
        }
)

public class cartcheckoutRunner extends AbstractTestNGCucumberTests {

}
