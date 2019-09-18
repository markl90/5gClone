package com.netbuilder.steps;

import com.netbuilder.pageobjects.*;
import com.netbuilder.test.common.session.TestBase;
import com.netbuilder.utilities.Waits;
import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OrderJourneySteps extends TestBase {

    private HomePage homePage;
    private PhoneSelectionPage phonePage;
    private ContractSelectionPage contractSelectionPage;
    private ProductSetupPage productSetupPage;
    private BasketSummaryPage basketSummaryPage;
    private CustomerDetailsPage customerDetailsPage;
    private OrderConfirmationPage orderConfirmationPage;

    private Waits waits;

    @Before
    public void setUp(){
        homePage = new HomePage(driver);
        phonePage = new PhoneSelectionPage(driver);
        contractSelectionPage = new ContractSelectionPage(driver);
        productSetupPage = new ProductSetupPage(driver);
        waits = new Waits(driver);
        basketSummaryPage = new BasketSummaryPage(driver);
        customerDetailsPage = new CustomerDetailsPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
    }

    @After
    public void tearDown(){
        session.stopDriver();
    }


    @Given("^the user is on the landing page$")
    public void the_user_is_on_the_landing_page() {
        driver.get(session.getBaseUrl());
        page.waitForLoad(driver);
        homePage.waitAndClickCloseCookies();

    }

    @When("^the the user selects the phones link$")
    public void the_the_user_selects_the_phones_link()  {
        homePage.hoverOverProductsAndServicesDropDown();
        homePage.clickPhonesLink();
    }

    @When("^the user specifies a search selecting a result$")
    public void the_user_specifies_a_search_selecting_a_result(DataTable params){
        List<String> list = params.asList(String.class);
        String brand = list.get(4).trim();
        String capacity = list.get(5).trim();
        String colour = list.get(6).trim();
        String product = list.get(7).trim();
        phonePage.findBrandAndSelect(brand);
        phonePage.findCapacityAndSelect(capacity);
        phonePage.findColourAndSelect(colour);
        waits.waitForProductsToLoad();
        phonePage.findAndSelectProduct(product);
        page.waitForLoad(driver);
        String productDetails = String.format("%s %s %s", product, colour, capacity);
        session.addData("product", productDetails);
    }


    @Then("^selects their contract details$")
    public void selects_their_contract_details(DataTable contractDetails){
        List<String> list = contractDetails.asList(String.class);
        String contractLength = list.get(2).trim();
        String dataPlan = list.get(3).trim();
        contractSelectionPage.findAndClickContractRadioSelector(contractLength);
        contractSelectionPage.findAndSelectDataPlan(dataPlan);
        page.waitForLoad(driver);
        session.addData("data", dataPlan);
    }

    int count = 0;
    @Then("^specifies details on the product setup section$")
    public void specifies_details_on_the_product_setup_section(DataTable productSetup){
        List<String> setupDetails = productSetup.asList(String.class);
        String currentUrl = driver.getCurrentUrl();
        count++;
        int productQuanity = Integer.parseInt(setupDetails.get(3).trim());
        String switchingProviderAnswer = setupDetails.get(4).trim();
        String spendingCap = setupDetails.get(5).toLowerCase().trim();
        if(productQuanity>1){
            productSetupPage.addToProductQuantity(productQuanity-1);
        }
        productSetupPage.switchingMobileProviderSelection(switchingProviderAnswer);

        // Experimental solution to selenium sometimes not finding the checkbox
        //      will repeat this method up to a maximum of 3 times.
        try{
            productSetupPage.waitForVisibilityOfSpendingToggle();
        } catch (Exception e){
            if(count<3){
                driver.navigate().to(currentUrl);
                specifies_details_on_the_product_setup_section(productSetup);
            }
        }


        if(spendingCap.equals("none") || spendingCap.isEmpty()){
            productSetupPage.monthlySpendingCap(false, null);
        } else {
            productSetupPage.monthlySpendingCap(true, spendingCap);
        }
    }

    @Then("^selects option extras$")
    public void selects_option_extras(DataTable optionExtras) {
        List<String> extras = optionExtras.asList(String.class);
        String extraMinutes = extras.get(2).trim();
        String extraData = extras.get(3).trim();
        productSetupPage.seeOptionalExtras(true);
        if(extraMinutes.isEmpty() || extraMinutes.equals("none")){
            productSetupPage.showExtraMinutes(false);
        } else {
            productSetupPage.selectExtraMinutesOption(extraMinutes);
        }
        if(extraData.isEmpty() || extraData.equals("none")){
            productSetupPage.showExtraData(false);
        } else {
            productSetupPage.selectExtraDataOption(extraData);
        }
        productSetupPage.selectAddToBasket();
        page.waitForLoad(driver);
        session.addData("extra_minutes", extraMinutes);
        session.addData("extra_data", extraData);
    }

    @Then("^the basket contains the phone with the chosen plan$")
    public void the_basket_contains_the_phone_with_the_chosen_plan()  {
       assertEquals("verify product selected is shown in basket details", session.getData("product"), basketSummaryPage.getProductdetails().split("£")[0].trim());
       assertEquals("verify data selected is shown in basket details", true, basketSummaryPage.getDataPlan().contains(session.getData("data")));
       basketSummaryPage.selectGoToCheckout();
       page.waitForLoad(driver);
    }

    @When("^the user enters generated user details$")
    public void the_user_enters_generated_user_details(){
        customerDetailsPage.sendDetails();
        page.waitForLoad(driver);
        customerDetailsPage.sendBusinessTypeDetails();
        page.waitForLoad(driver);
        customerDetailsPage.sendDirectoryListingsInformation();
        page.waitForLoad(driver);
        customerDetailsPage.sendCreditCheckInformation();
        page.waitForLoad(driver);
        customerDetailsPage.sendPaymentDetails();
    }

    @Then("^the order is successful and confirmation of the details is presented$")
    public void the_order_is_successful_and_confirmation_of_the_details_is_presented(){
        orderConfirmationPage.getProductDetails();
        orderConfirmationPage.getHeaderMessage();
        orderConfirmationPage.getHeaderTitle();

        String orderDetails = session.getData("product");

        assertEquals("verifying the header title of the order confirmation page", "Thank You", orderConfirmationPage.getHeaderTitle());
        assertEquals("verifying the header message of the order confirmation page", "You’ve placed your order – thank you", orderConfirmationPage.getHeaderMessage());
        assertEquals("verifying the product details on the order confirmation page", orderDetails, orderConfirmationPage.getProductDetails());
    }





    @Given("^the helper step$")
    public void the_helper_step() throws Throwable {
//      driver.get("https://business-bt-com-test-ams.digital-ent-int.bt.com/product-configuration/?productPath=/content/bt/business/en/products/mobile/phones/apple/iphone-xs&capacityPath=/etc/commerce/products/bt-business/mobile/capacities/_256gb&planPath=/etc/commerce/products/bt-business/mobile/plans/business_12_mth/solo_extra_16gb_58&colourPath=/etc/commerce/products/bt-business/mobile/colours/space_grey&m=1");
//        homePage.waitAndClickCloseCookies();
//        productSetupPage.addToProductQuantity(3);
//
//        productSetupPage.lowerProductQuantity(3);
//
//        productSetupPage.switchingMobileProviderSelection("yes, i want a");
//
//        productSetupPage.monthlySpendingCap(true, "£200");
//
//        productSetupPage.monthlySpendingCap(true, "£100");
//
//        productSetupPage.monthlySpendingCap(false, null);
//
//        productSetupPage.seeOptionalExtras(true);
//
//        productSetupPage.seeOptionalExtras(false);
//
//        productSetupPage.seeOptionalExtras(true);
//
//        productSetupPage.showExtraMinutes(false);
//
//        productSetupPage.showExtraData(false);
//
//        productSetupPage.seeOptionalExtras(true);
//
//        productSetupPage.selectExtraMinutesOption("helpline");
//
//        productSetupPage.selectExtraDataOption("data 4gb");
//
//        productSetupPage.selectAddToBasket();
//        page.waitForLoad(driver);
//        basketSummaryPage.getDataPlan();
//
//        basketSummaryPage.getProductdetails();

        driver.get("https://business-bt-com-test-ams.digital-ent-int.bt.com/checkout/");
        homePage.waitAndClickCloseCookies();

        customerDetailsPage.sendDetails();
        page.waitForLoad(driver);
        customerDetailsPage.sendBusinessTypeDetails();
        page.waitForLoad(driver);
        customerDetailsPage.sendCreditCheckInformation();
        page.waitForLoad(driver);
        customerDetailsPage.sendPaymentDetails();




    }


}
