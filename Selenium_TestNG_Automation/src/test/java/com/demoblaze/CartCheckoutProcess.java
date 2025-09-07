package com.demoblaze;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utilities.WaitUtils.*;

public class CartCheckoutProcess extends TestBase{

    @Test (priority = 0, groups = {"AddToCart"})
    public void VerifyAddToCart() throws InterruptedException {
        String itemName = "Samsung galaxy s6", category = "Phones";
        // Locators
        By homeLocator = By.xpath("//a[text()[normalize-space()='Home']]"), categoryLocator = By.xpath("//a[normalize-space()='"+category+"']"), itemLocator = By.xpath("//a[normalize-space()='"+itemName+"']"), addToCartLocator = By.xpath("//a[normalize-space()='Add to cart']"), cLocator = By.id("cartur"), nameLocator = By.cssSelector("td:nth-child(2)");

        // Go to home page
        try{
            waitForElementClickable(driver, homeLocator).click();
        } catch (Exception e){
            System.out.println("User already on home page");
        }
        // Select category and click on item
        waitForElementClickable(driver, categoryLocator).click();
        Thread.sleep(2000);
        waitForElementClickable(driver, itemLocator).click();
        // Click add to cart
        waitForElementClickable(driver, addToCartLocator).click();
        waitForAlertVisible(driver).accept();
        // Go to cart
        waitForElementVisible(driver, cLocator).click();
        // Verify the product details same as the selected product
        String actualName = waitForElementVisible(driver, nameLocator).getText();
        if(actualName.equalsIgnoreCase(itemName)){
            System.out.println("Item added successfully");
        } else System.out.println("Item failed to add in cart");
    }

    @Test (dependsOnMethods = {"VerifyAddToCart"}, priority = 1, groups = {"DeleteFromCart"})
    public void VerifyDeletion() throws InterruptedException {
        //Locators
        By cartLocator = By.xpath("//a[normalize-space()='Cart']"), itemLocator = By.cssSelector("tbody>tr"), deleteLocator = By.cssSelector("td:nth-child(4)>a"), tableLocator = By.cssSelector("tbody");

        // Go to cart
        waitForElementClickable(driver, cartLocator).click();
        // Wait for element visible and get the number of items in cart
        waitForElementVisible(driver, itemLocator);
        int items = driver.findElements(itemLocator).size();
        System.out.println("Number of items present in cart : "+items);
        // Click delete
        waitForElementClickable(driver, deleteLocator).click();
        Thread.sleep(2000);
        // Verify deletion successfully or not
        int actualItems = 0;
        try {
            waitForElementVisible(driver, tableLocator);
            actualItems = driver.findElements(itemLocator).size();
        } catch (Exception e) {
            System.out.println("No elements in cart");
        }
        if(items!=actualItems) System.out.println("Item deleted from cart successfully");
        else System.out.println("Item deletion from cart failed");

    }

    @Test (priority = 2, groups = {"CheckoutProcess"})
    public void VerifyCheckoutProcess() throws InterruptedException {
        // first add to cart again
        VerifyAddToCart();
        // Locators
        By cartLocator = By.xpath("//a[normalize-space()='Cart']"), placeOrderLocator = By.xpath("//button[normalize-space()='Place Order']"), nameLocator = By.id("name"), countryLocator = By.id("country"), cityLocator = By.id("city"), cardLocator = By.id("card"), monthLocator = By.id("month"), yearLocator = By.id("year"), purchaseBtnLocator = By.xpath("//button[normalize-space()='Purchase']"), responseLocator = By.cssSelector("body > div:nth-child(19) > h2:nth-child(6)"), detailsLocator = By.xpath("//p[contains(@class,'lead text-muted')]"), OkLocator = By.xpath("//button[normalize-space()='OK']");

        // Go to cart and click on place order to open checkout form
        System.out.println("User on Checkout form page");
        waitForElementVisible(driver, cartLocator).click();
        waitForElementVisible(driver, placeOrderLocator).click();
        // Enter details required
        waitForElementVisible(driver, nameLocator).sendKeys("Mayank");
        waitForElementVisible(driver, countryLocator).sendKeys("India");
        waitForElementVisible(driver, cityLocator).sendKeys("Mathura");
        waitForElementVisible(driver, cardLocator).sendKeys("1234567890");
        waitForElementVisible(driver, monthLocator).sendKeys("January");
        waitForElementVisible(driver,yearLocator).sendKeys("2030");
        // Click on Purchase and record the purchase details to show
        waitForElementVisible(driver, purchaseBtnLocator).click();
        String response = waitForElementVisible(driver, responseLocator).getText();
        String details = waitForElementVisible(driver, detailsLocator).getText();
        waitForElementVisible(driver, OkLocator).click();
        System.out.println("------------------------------");
        System.out.println(response);
        System.out.println(details);
        System.out.println("------------------------------");
        Thread.sleep(2500);
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("\n ...Cart Checkout Process Execution Starts... \n");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("✅ CartCheckoutProcess class executed successfully.");
        System.out.println("-------------------------------------------------\n");
    }
}
