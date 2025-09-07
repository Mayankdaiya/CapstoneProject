package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utilities.WaitUtils.*;

public class UserAccount{

    WebDriver driver;
    // Locators
    By homeLocator = By.xpath("//a[contains(.,'Home')]");
    By signupLocator = By.id("signin2");
    By usernameLocator = By.id("sign-username");
    By passwordLocator = By.id("sign-password");
    By signupBtnLocator = By.xpath("//button[normalize-space()='Sign up']");
    By closeSignupLocator = By.xpath("//div[@id='signInModal']//button[@type='button'][normalize-space()='Close']");
    By loginLocator = By.id("login2");
    By loginusernameLocator = By.id("loginusername");
    By loginpasswordLocator = By.id("loginpassword");
    By loginBtnLocator = By.xpath("//button[normalize-space()='Log in']");
    By closeLoginLocator = By.xpath("//div[@id='logInModal']//button[@type='button'][normalize-space()='Close']");
    By logoutLocator = By.xpath("//a[@id='logout2']");


    public UserAccount(WebDriver driver) {
        this.driver = driver;
    }

    public void getHomePageDetails() {
        try { driver.findElement(homeLocator).click(); } catch (Exception ignored) {}
        System.out.println("Title of the page : " + driver.getTitle());
        System.out.println("URL of the page : " + driver.getCurrentUrl());
    }

    public void clickOnSignup() {
        waitForElementClickable(driver, signupLocator).click();
    }

    public void enterSignupDetails(String username, String password) {
        waitForElementVisible(driver, usernameLocator).clear();
        waitForElementVisible(driver, usernameLocator).sendKeys(username);
        waitForElementVisible(driver, passwordLocator).clear();
        waitForElementVisible(driver, passwordLocator).sendKeys(password);
    }

    public void submitSignup() {
        waitForElementClickable(driver, signupBtnLocator).click();
    }

    public void clickOnLogin() {
        try { driver.findElement(logoutLocator).click(); } catch (Exception ignored) {}  // logout first
        waitForElementClickable(driver, loginLocator).click();
    }

    public void enterLoginDetails(String username, String password) {
        waitForElementVisible(driver, loginusernameLocator).clear();
        waitForElementVisible(driver, loginusernameLocator).sendKeys(username);
        waitForElementVisible(driver, loginpasswordLocator).clear();
        waitForElementVisible(driver, loginpasswordLocator).sendKeys(password);
    }

    public void submitLogin() {
        waitForElementClickable(driver, loginBtnLocator).click();
    }

    public String loginSuccess() {
        String message = "";
        try{
            Alert alert = waitForAlertVisible(driver);
            message = alert.getText();
            alert.accept();
            waitForElementClickable(driver, closeLoginLocator).click();
        } catch (Exception ignored) {}
        return message;
    }

    public String signupSuccess() {
        String message = "";
        try{
            Alert alert = waitForAlertVisible(driver);
            message = alert.getText();
            alert.accept();
            if(message.contains("exist")) waitForElementClickable(driver, closeSignupLocator).click();
        } catch (Exception ignored) {}
        return message;
    }
}
