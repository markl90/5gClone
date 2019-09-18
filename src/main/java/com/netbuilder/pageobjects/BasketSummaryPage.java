package com.netbuilder.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasketSummaryPage {

    private WebDriver driver;

    private WebElement productDetails;

    @FindBy(xpath = "/html/body/div[4]/div/div[4]/div/div[2]/div/div[7]/div/div[2]/div/div/div/div[3]/div[2]/ul/li")
    private WebElement dataPlan;

    @FindBy(xpath = "/html/body/div[4]/div/div[4]/div/div[2]/div/div[7]/div/div[2]/div/div/div/div[4]/div[5]/div/div/div/ul/li")
    private WebElement extraData;

    @FindBy(xpath = "/html/body/div[4]/div/div[4]/div/div[2]/div/div[7]/div/div[2]/div/div/div/div[4]/div[7]/div/div/ul/li/span")
    private WebElement changeNumberOption;

    @FindBy(css = "a.component-basketv2-primary-button")
    private WebElement goToCheckoutButton;

    @FindBy(css = "a.component-basketv2-secondary-button")
    private WebElement continueShoppingButton;



    public BasketSummaryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getProductdetails(){
        List<WebElement> detailsContainer = driver.findElements(By.cssSelector("div.component-basketv2-item-section"));
        productDetails = detailsContainer.get(0).findElement(By.cssSelector("div.component-basketv2-item-section-prices.ng-scope"));
        return productDetails.getText();
    }

    public String getDataPlan(){
      dataPlan = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div/div[2]/div/div[7]/div/div[2]/div/div/div/div[3]/div[2]/ul/li"));
      return dataPlan.getText();
    }

    public String getExtraData(){
        return extraData.getText();
    }

    public String getChangeNumberOption(){
       return changeNumberOption.getText();
    }

    public void selectContinueShopping(){
        continueShoppingButton.click();
    }

    public void selectGoToCheckout(){
        goToCheckoutButton.click();
    }




}
