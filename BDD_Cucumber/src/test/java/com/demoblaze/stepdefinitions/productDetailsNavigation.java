package com.demoblaze.stepdefinitions;
import pages.ProductDetailsNavigation;
import com.demoblaze.hooks.Hooks;
import io.cucumber.java.en.*;

public class productDetailsNavigation {
    ProductDetailsNavigation pdn = new ProductDetailsNavigation(Hooks.getDriver());

    @When("the user navigates to {string} category")
    public void the_user_navigates_to_category(String category) throws Exception {
        pdn.navigatesCategory(category);
    }

    @Then("the {string} product list should be displayed")
    public void the_product_list_should_be_displayed(String itemName) throws InterruptedException {
        System.out.println("User is on "+itemName+" category page.");
        pdn.displayProductList();
    }

    @When("the user selects the product {string}")
    public void the_user_selects_the_product(String itemName) throws Exception {
        pdn.selectsProduct(itemName);
    }

    @Then("the product detail page should display {string}")
    public void the_product_detail_page_should_display(String name) {
        if(pdn.displayProductName(name)){
            System.out.println("Product name matches correctly ✔");
        } else System.out.println("Product name does not matches X");
    }

    @Then("the product price should be {string}")
    public void the_product_price_should_be(String price) {
        if(pdn.displayProductPrice(price)){
            System.out.println("Product price matches correctly ✔");
        } else System.out.println("Product price does not matches X");
    }

    @Then("the product description should contain {string}")
    public void the_product_description_should_contain(String description) {
        if(pdn.displayProductDescription(description)){
            System.out.println("Product details verified successfully ✔");
        } else System.out.println("Product details are not matching with expected details X");
    }
}
