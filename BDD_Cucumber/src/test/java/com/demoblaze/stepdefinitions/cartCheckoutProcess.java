package com.demoblaze.stepdefinitions;

import com.demoblaze.hooks.Hooks;
import io.cucumber.java.en.*;
import pages.CartCheckoutProcess;
import pages.ProductDetailsNavigation;

import java.util.Map;

public class cartCheckoutProcess {
    CartCheckoutProcess ccp = new CartCheckoutProcess(Hooks.getDriver());
    ProductDetailsNavigation pdn = new ProductDetailsNavigation(Hooks.getDriver());

    @When("the user clicks on Add to cart")
    public void the_user_clicks_on_Add_to_cart() {
        ccp.addToCart();
    }
    @When("the user navigates to the Cart page")
    public void the_user_navigates_to_the_cart_page() {
        ccp.navigatesCart();
    }
    @Then("the cart should contain the product {string}")
    public void the_cart_should_contain_the_product(String productName) {
        if(ccp.containsProduct(productName)){
            System.out.println("Cart contains the product");
        } else System.out.println("Cart does not contains the product");
    }
    @Then("a confirmation message should be displayed")
    public void a_confirmation_message_should_be_displayed() {
        ccp.confirmationMessage();
    }
    @When("the cart item list is displayed")
    public void the_cart_item_list_is_displayed() {
        ccp.cartItemListDisplayed();
    }

    @When("the user deletes the first item from the cart")
    public void the_user_deletes_the_first_item_from_the_cart() {
        ccp.deleteFirstItem();
    }
    @Then("the item deletes from the cart item list")
    public void the_item_deletes_from_the_cart_item_list() throws InterruptedException {
        if(ccp.itemDeleted()) System.out.println("Item deleted from cart successfully");
        else System.out.println("Cart item deletion failed");
    }
    @Then("the deleted item should no longer be in the cart")
    public void the_deleted_item_should_no_longer_be_in_the_cart() {
        int size = ccp.getCartListSize();
        System.out.println(size+" items present in cart");
    }

    @Given("the user has added {string} from the {string} to the cart")
    public void the_user_has_added_from_the_to_the_cart(String productName, String category) throws Exception {
        pdn.navigatesCategory(category);
        pdn.selectsProduct(productName);
        ccp.addToCart();
    }

    @When("the user clicks on Place Order")
    public void the_user_clicks_on_Place_Order() {
        ccp.clickPlaceOrder();
    }

    @When("the user fills in the order details with:")
    public void the_user_fills_in_the_order_details_with(Map<String, String> map) {
        ccp.fillOrderDetails(map);
    }

    @When("the user clicks on Purchase")
    public void the_user_clicks_on_Purchase() {
        ccp.clicksPurchase();
    }

    @Then("the order confirmation message should be displayed")
    public void the_order_confirmation_message_should_be_displayed() {
        ccp.displayResponseDetails();
    }
    @Then("the user clicks on OK")
    public void the_user_clicks_on_OK() throws InterruptedException {
        ccp.clicksOk();
    }



}
