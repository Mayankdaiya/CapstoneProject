package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static utilities.WaitUtils.*;

public class CartCheckoutProcess{

    WebDriver driver;
    int beforeDeletionListSize = 0;
    // Locators
    By addToCartLocator = By.xpath("//a[normalize-space()='Add to cart']");
    By cartLocator = By.id("cartur");
    By itemListLocator = By.cssSelector("#tbodyid>tr");
    By nameLocator = By.cssSelector("td:nth-child(2)");
    By itemLocator = By.cssSelector("tbody>tr");
    By deleteLocator = By.cssSelector("td:nth-child(4)>a");
    By tableLocator = By.cssSelector("tbody");
    By placeOrderLocator = By.xpath("//button[normalize-space()='Place Order']");
    By formNameLocator = By.id("name");
    By formCountryLocator = By.id("country");
    By formCityLocator = By.id("city");
    By formCardLocator = By.id("card");
    By formMonthLocator = By.id("month");
    By formYearLocator = By.id("year");
    By formPurchaseBtnLocator = By.xpath("//button[normalize-space()='Purchase']");
    By formResponseLocator = By.cssSelector("body > div:nth-child(19) > h2:nth-child(6)");
    By formDetailsLocator = By.xpath("//p[contains(@class,'lead text-muted')]");
    By formOkLocator = By.xpath("//button[normalize-space()='OK']");


    public CartCheckoutProcess(WebDriver driver){
        this.driver = driver;
    }

    public void addToCart() {
        waitForElementClickable(driver, addToCartLocator).click();
        waitForAlertVisible(driver).accept();
    }

    public void navigatesCart() {
        waitForElementVisible(driver, cartLocator).click();
    }

    public boolean containsProduct(String prodName) {
        boolean contains = false;
        try {
            waitForElementVisible(driver, itemListLocator);
            List<WebElement> list = driver.findElements(itemListLocator);
            for(WebElement el:list){
                if(el.findElement(nameLocator).getText().equalsIgnoreCase(prodName)){
                    contains = true;
                    break;
                }
            }
        } catch (Exception ignored) {}
        return contains;
    }

    public void confirmationMessage() {
        System.out.println("Product Added to Cart Successfully");
    }

    public int getCartListSize() {
        int size = 0;
        try {
            waitForElementVisible(driver, tableLocator);
            waitForElementVisible(driver, itemLocator);
            size = driver.findElements(itemLocator).size();
        } catch (Exception ignored) {}
        return size;
    }

    public void cartItemListDisplayed() {
        int items = getCartListSize();
        beforeDeletionListSize = items;
        System.out.println(items+" items displays in cart");
    }

    public void deleteFirstItem() {
        waitForElementClickable(driver, deleteLocator).click();
    }

    public boolean itemDeleted() throws InterruptedException {
        Thread.sleep(2000);
        int currentItems = getCartListSize();
        return beforeDeletionListSize!=currentItems;
    }

    public void clickPlaceOrder() {
        waitForElementVisible(driver, placeOrderLocator).click();
    }

    public void fillOrderDetails(Map<String, String> data) {
        waitForElementVisible(driver, formNameLocator).sendKeys(data.get("Name"));
        waitForElementVisible(driver, formCountryLocator).sendKeys(data.get("Country"));
        waitForElementVisible(driver, formCityLocator).sendKeys(data.get("City"));
        waitForElementVisible(driver, formCardLocator).sendKeys(data.get("Card"));
        waitForElementVisible(driver, formMonthLocator).sendKeys(data.get("Month"));
        waitForElementVisible(driver,formYearLocator).sendKeys(data.get("Year"));
    }

    public void clicksPurchase() {
        waitForElementVisible(driver, formPurchaseBtnLocator).click();
    }

    public void displayResponseDetails() {
        String response = waitForElementVisible(driver, formResponseLocator).getText();
        String details = waitForElementVisible(driver, formDetailsLocator).getText();
        System.out.println("------------------------------");
        System.out.println(response);
        System.out.println(details);
        System.out.println("------------------------------");
    }

    public void clicksOk() throws InterruptedException {
        waitForElementVisible(driver, formOkLocator).click();
        Thread.sleep(2500);
    }
}
