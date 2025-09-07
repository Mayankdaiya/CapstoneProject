package com.demoblaze.stepdefinitions;
import com.demoblaze.hooks.Hooks;
import pages.UserAccount;

import io.cucumber.java.en.*;

public class userAccount {
    UserAccount ua = new UserAccount(Hooks.getDriver());

    @Given("the user is on the Home page")
    public void the_user_is_on_the_home_page() {
        ua.getHomePageDetails();
    }

    @When("the user clicks on Sign up")
    public void the_user_clicks_on_sign_up() {
        ua.clickOnSignup();
    }
    @When("the user enters {string} and {string} in the signup form")
    public void the_user_enters_and_in_the_signup_form(String username, String password) {
        ua.enterSignupDetails(username, password);
    }
    @When("submits the registration form")
    public void submits_the_registration_form() {
        ua.submitSignup();
    }
    @When("the user clicks on Log in")
    public void the_user_clicks_on_log_in() {
        ua.clickOnLogin();
    }
    @When("the user enters {string} and {string} in the login form")
    public void the_user_enters_and_in_the_login_form(String username, String password) {
        ua.enterLoginDetails(username, password);
    }
    @When("submit the login button")
    public void submit_the_login_button() {
        ua.submitLogin();
    }

    @Then("the user should see a Home page")
    public void the_user_should_see_a_home_page() {
        if(ua.loginSuccess().isEmpty()){
            System.out.println("Login successfully");
        } else System.out.println("Login failed");
    }
    @Then("the user should see a {string} alert for login")
    public void the_user_should_see_a_alert_for_login(String expected) {
        String message = ua.loginSuccess();
        if(message.equalsIgnoreCase(expected)){
            System.out.println("Alert message : "+message);
        } else System.out.println("Alert message :"+message);
    }
    @Then("the user should see a {string} alert for signup")
    public void the_user_should_see_a_alert_for_signup(String expected) {
        String message = ua.signupSuccess();
        if(message.equalsIgnoreCase(expected)){
            System.out.println("Alert message : "+message);
        } else System.out.println("Alert message : "+message);
    }
}
