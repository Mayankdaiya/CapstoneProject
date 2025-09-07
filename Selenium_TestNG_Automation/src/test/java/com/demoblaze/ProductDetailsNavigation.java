package com.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static utilities.WaitUtils.waitForElementClickable;
import static utilities.WaitUtils.waitForElementVisible;

public class ProductDetailsNavigation extends TestBase{

//    @Test (groups = {"load"})
//    public void loadConfig(){
//        ConfigReader.loadConfig();
//        driver = DriverFactory.initializeDriver(ConfigReader.get("browser"));
//        driver.get(ConfigReader.get("url"));
//    }

    @Test (priority = 0, groups = {"CategoryNavigation"})
    public void CategoryNavigation() throws InterruptedException {
        // Locators
        By[] categoriesLocator = {By.xpath("//a[@id='cat']"), By.xpath("//a[normalize-space()='Phones']"), By.xpath("//a[normalize-space()='Laptops']"), By.xpath("//a[normalize-space()='Monitors']")};
        By itemListLocator = By.xpath("//div[@id='tbodyid']/div");
        By nameLocator = By.xpath("./div/div/h4/a");
        By priceLocator = By.xpath("./div/div/h5");

        Thread.sleep(1000);
        // checking navigation occurs properly through all categories and also printing items in console
        for (int i = 0; i < categoriesLocator.length; i++) {
            waitForElementClickable(driver, categoriesLocator[i]).click();
            Thread.sleep(2000);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(itemListLocator));
            List<WebElement> elements = driver.findElements(itemListLocator);
            System.out.println("On Categories Page : " + i);
            for (WebElement el : elements) {
                try {
                    String name = el.findElement(nameLocator).getText();
                    String price = el.findElement(priceLocator).getText();
                    System.out.println("Name : " + name + " , Item price : " + price);
                } catch (Exception e) {
                    System.out.println("Element went stale, skipping.");
                }
            }
        }
    }

    @Test (priority = 1, groups = {"CheckProductDetails"})
    public void VerifyProductDetails(){
        // Details to verify
        String itemName = "Samsung galaxy s6", category = "Phones";
        // Locators
        By categoryLocator = By.xpath("//a[normalize-space()='"+category+"']"), itemLocator = By.xpath("//a[normalize-space()='"+itemName+"']"), nameLocator = By.xpath("//div[@id='tbodyid']/h2"), priceLocator = By.xpath("//div[@id='tbodyid']/h3"), detailsLocator = By.xpath("//div[@id='more-information']/p");

        // click on category
        waitForElementVisible(driver, categoryLocator).click();
        // click on item
        waitForElementVisible(driver, itemLocator).click();
        // extracting name, description and price and verify the details
        String actualName = waitForElementVisible(driver, nameLocator).getText();
        String itemDetails = waitForElementVisible(driver, detailsLocator).getText();
        String itemPrice = waitForElementVisible(driver, priceLocator).getText();
        if(actualName.equalsIgnoreCase(itemName)){
            System.out.println("Product details verified successfully");
        } else System.out.println("Product details are not matching with expected details");
        // display all details
        System.out.println("Product description : "+itemDetails);
        System.out.println("Product price : "+itemPrice);
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("\n ...Product Details Navigation Execution Starts... \n");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("\n------------------------------------------------------");
        System.out.println("✅ ProductDetailsNavigation class executed successfully.");
        System.out.println("------------------------------------------------------\n");
    }
}
