package com.netbuilder.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductSetupPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"productConfigurationConfigElement\"]/div[5]/div/div/div[2]/div[4]/div/div/button[1]")
    private WebElement addQtyButton;

    @FindBy(xpath = "//*[@id=\"productConfigurationConfigElement\"]/div[5]/div/div/div[2]/div[4]/div/div/button[2]")
    private WebElement lowerQtyButton;

    @FindBy(css = "button.button.start.ng-scope")
    private WebElement startAgainButton;

    @FindBy(css = "div.option.spendCapToggle")
    private WebElement spendingCapCheckbox;

    @FindBy(css = "select.ng-valid")
    private WebElement spendingCapDropDown;

    @FindBy(xpath = "//*[@id=\"productConfigurationConfigElement\"]/div[11]/div[2]/div/a/div")
    private WebElement optionalExtrasTickBox;

    @FindBy(xpath = "//*[@id=\"productConfigurationConfigElement\"]/div[11]/div[3]/div[1]/div/a/div")
    private WebElement extraMinutesTickBox;

    @FindBy(xpath = "//*[@id=\"productConfigurationConfigElement\"]/div[12]/div[9]/div[1]/div/a/div")
    private WebElement extraDataTickBox;

    @FindBy(css = "a.button.basket")
    private WebElement addToBasketButton;



    public ProductSetupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addToProductQuantity(int number){
        for(int i = 0; i<number; i++){
            addQtyButton.click();
        }
    }

    public void lowerProductQuantity(int number){
        for(int i = 0; i<number; i++){
            lowerQtyButton.click();
        }
    }

    public void switchingMobileProviderSelection(String selection){
        List<WebElement> options = driver.findElements(By.cssSelector("div.item.columns.end.ng-scope"));
        for(WebElement elem: options){
            if(elem.findElement(By.cssSelector("div.number-switching.ng-scope")).getText().toLowerCase().contains(selection.toLowerCase())){
                elem.click();
            }
        }
    }

    public void waitForVisibilityOfSpendingToggle(){
        Wait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.option.spendCapToggle"))));
        } catch (Exception e){
            spendingCapCheckbox = driver.findElement(By.xpath("//*[@id=\"productConfigurationConfigElement\"]/div[9]/div[3]/div[6]/div[1]/div[2]"));
        }
    }

    public void monthlySpendingCap(boolean capSelected, String spendingCap){
        if(capSelected){
            if(spendingCapCheckbox.getAttribute("class").equals("option spendCapToggle")){
                spendingCapCheckbox.click();
            }
            spendingCapDropDown.sendKeys(spendingCap);
        }
        if(spendingCapCheckbox.getAttribute("class").equals("option spendCapToggle selected") && !capSelected){
            spendingCapCheckbox.click();
        }
    }

    public void seeOptionalExtras(boolean showExtras){
        WebElement optionalExtrasContainer = driver.findElement(By.cssSelector("div.columns.small-12.add-ons-heading.ng-scope"));
        if(showExtras){
            showExtraMinutes(true);
            showExtraData(true);
        }
        if(showExtras && optionalExtrasContainer.getAttribute("class").equals("columns small-12 add-ons-heading ng-scope notRequired")){
            optionalExtrasTickBox.click();
            showExtraMinutes(true);
            showExtraData(true);
        }
        if(!showExtras && optionalExtrasContainer.getAttribute("class").equals("columns small-12 add-ons-heading ng-scope")){
            optionalExtrasTickBox.click();
        }
    }

    public void showExtraMinutes(boolean selectExtra){
        WebElement extraMinutesContainer = driver.findElement(By.xpath("//*[@id=\"productConfigurationConfigElement\"]/div[12]/div[3]"));
        if(selectExtra && extraMinutesContainer.getAttribute("class").equals("columns small-12 notRequired")){
            extraMinutesTickBox.click();
        }
        else if(!selectExtra && extraMinutesContainer.getAttribute("class").equals("columns small-12")){
            extraMinutesTickBox.click();
        }

    }

    public void selectExtraMinutesOption(String extraMinutesOption){
        showExtraMinutes(true);
        WebElement optionContainer = driver.findElement(By.xpath("//*[@id=\"productConfigurationConfigElement\"]/div[12]/div[3]"));
        List<WebElement> options = optionContainer.findElements(By.cssSelector("div.columns.large-8.small-8"));
        for(WebElement elem: options){
            if(elem.findElement(By.cssSelector("span.ng-scope.ng-binding")).getText().toLowerCase().contains(extraMinutesOption.toLowerCase())){
                elem.click();
                break;
            }
        }
    }

    public void showExtraData(boolean selectExtra){
        WebElement extraDataContainer = driver.findElement(By.xpath("//*[@id=\"productConfigurationConfigElement\"]/div[13]/div[9]"));
        if(selectExtra && extraDataContainer.getAttribute("class").equals("columns small-12 notRequired")){
            extraDataTickBox.click();
        }
        else if(!selectExtra && extraDataContainer.getAttribute("class").equals("columns small-12")){
            extraDataTickBox.click();
        }

    }

    public void selectExtraDataOption(String option){
        WebElement optionContainer = driver.findElement(By.xpath("//*[@id=\"productConfigurationConfigElement\"]/div[13]/div[9]/div[6]"));
        List<WebElement> options = optionContainer.findElements(By.cssSelector("div.item.columns.end.ng-scope"));
        for(WebElement elem: options){
            if(elem.findElement(By.cssSelector("span.ng-scope.ng-binding")).getText().toLowerCase().contains(option.toLowerCase())){
                elem.click();
                break;
            }
        }
    }

    public void selectAddToBasket(){
        addToBasketButton.click();
    }

}
