package com.netbuilder.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

    private WebDriver driver;

    @FindBy(xpath= "/html/body/div[4]/div/div[4]/div[12]/div/div[2]/div[3]")
    private WebElement headerTitle;

    @FindBy(css= "/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[5]/div[1]/span[1]")
    private WebElement headerMessage;

//    @FindBy(css= "span.component-checkoutv2-completion-title")
//    private WebElement headerMessage;

    @FindBy(css = "h3.component-checkoutv2-completion-basket-item-showcase-details-heading")
    private WebElement productDetails;


    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductDetails(){
        return productDetails.getText();
    }

    public String getHeaderMessage(){
        return headerMessage.getText();
    }

    public String getHeaderTitle(){
        return headerTitle.getText();
    }
}
