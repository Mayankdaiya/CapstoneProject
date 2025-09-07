package com.demoblaze;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utilities.WaitUtils.*;

public class UserAccount extends  TestBase{
    private String username = "mayank#$9", password = "pass@123";

    @Test (priority = 0, groups = {"SignUpAccount"})
    public void UserSignUp() {
        // Locators
        By signupLocator = By.id("signin2"), usernameLocator = By.id("sign-username"), passwordLocator = By.id("sign-password"), signupBtnLocator = By.xpath("//button[normalize-space()='Sign up']"), closeLocator = By.xpath("//div[@id='signInModal']//button[@type='button'][normalize-space()='Close']");

        waitForElementClickable(driver, signupLocator).click();
        waitForElementVisible(driver, usernameLocator).sendKeys(username);
        waitForElementVisible(driver, passwordLocator).sendKeys(password);
        waitForElementClickable(driver, signupBtnLocator).click();
        // Accept alert
        waitForAlertVisible(driver).accept();
        // close window if already exists
        try {
            waitForElementClickable(driver, closeLocator).click();
        } catch (Exception ignored) {}
        System.out.println("Sign Up Successfully!");
    }

    @Test (priority = 1, groups = {"LoginAccount"})
    public void UserLogin(){
        //Locators
        By loginLocator = By.id("login2"), usernameLocator = By.id("loginusername"), passwordLocator = By.id("loginpassword"), loginBtnLocator = By.xpath("//button[normalize-space()='Log in']"), logoutLocator = By.xpath("//a[@id='logout2']");

        waitForElementClickable(driver, loginLocator).click();
        waitForElementVisible(driver, usernameLocator).sendKeys(username);
        waitForElementVisible(driver, passwordLocator).sendKeys(password);
        waitForElementClickable(driver, loginBtnLocator).click();
        // Accept alert
        try{
            driver.switchTo().alert().accept();
        } catch (Exception ignored) {}
        System.out.println("Login Successfully!");
//        waitForElementClickable(driver, logoutLocator).click();
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("\n ...User Account Execution Starts... \n");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("\n----------------------------------------");
        System.out.println("✅ UserAccount class executed successfully");
        System.out.println("----------------------------------------\n");
    }
}
