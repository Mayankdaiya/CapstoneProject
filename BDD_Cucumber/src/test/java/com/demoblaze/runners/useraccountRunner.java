package com.demoblaze.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features/useraccount.feature",
        glue = {"com.demoblaze.stepdefinitions", "com.demoblaze.hooks"},
        tags = "@Positive",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
        }
)

public class useraccountRunner extends AbstractTestNGCucumberTests{
}
