package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static utilities.WaitUtils.waitForElementClickable;
import static utilities.WaitUtils.waitForElementVisible;

public class ProductDetailsNavigation {

    WebDriver driver;
    // Locators
    By phonesLocator = By.xpath("//a[normalize-space()='Phones']");
    By laptopsLocator = By.xpath("//a[normalize-space()='Laptops']");
    By monitorsLocator = By.xpath("//a[normalize-space()='Monitors']");
    By itemListLocator = By.xpath("//div[@id='tbodyid']/div");
    By nameLocator = By.xpath("./div/div/h4/a");
    By priceLocator = By.xpath("./div/div/h5");
    By pageNameLocator = By.xpath("//div[@id='tbodyid']/h2");
    By pagePriceLocator = By.xpath("//div[@id='tbodyid']/h3");
    By pageDetailsLocator = By.xpath("//div[@id='more-information']/p");

    public ProductDetailsNavigation(WebDriver driver){
        this.driver = driver;
    }

    public void navigatesCategory(String category) throws Exception {
        Thread.sleep(1000);
        if(category.equals("Phones")) waitForElementClickable(driver, phonesLocator).click();
        else if(category.equals("Laptops")) waitForElementClickable(driver, laptopsLocator).click();
        else if(category.equals("Monitors")) waitForElementClickable(driver, monitorsLocator).click();
        else throw new Exception("Category Section Not found");
    }

    public void displayProductList() throws InterruptedException {
        Thread.sleep(2000);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(itemListLocator));
        List<WebElement> elements = driver.findElements(itemListLocator);
        for(WebElement el:elements){
            try {
                String name = el.findElement(nameLocator).getText();
                String price = el.findElement(priceLocator).getText();
                System.out.println("Name : " + name + " , Item price : " + price);
            } catch (Exception e) {
                System.out.println("Element went stale, skipping.");
            }
        }
    }

    public void selectsProduct(String productName) throws Exception {
        By itemLocator = By.xpath("//a[normalize-space()='"+productName+"']");
        try {
            waitForElementVisible(driver, itemLocator).click();
        } catch (Exception e) {
            throw new Exception("Product not found on page.");
        }
    }

    public boolean displayProductName(String productName) {
        String actualName = waitForElementVisible(driver, pageNameLocator).getText();
        System.out.println(actualName);
        return actualName.equalsIgnoreCase(productName);
    }

    public boolean displayProductPrice(String productPrice) {
        String actualPrice = waitForElementVisible(driver, pagePriceLocator).getText();
        System.out.println(actualPrice);
        return actualPrice.contains(productPrice);
    }

    public boolean displayProductDescription(String productDescription) {
        String actualDescription = waitForElementVisible(driver, pageDetailsLocator).getText();
        System.out.println(actualDescription);
        return actualDescription.contains(productDescription);
    }

}
