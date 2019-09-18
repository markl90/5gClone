package com.netbuilder.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PhoneSelectionPage {

    private WebDriver driver;

    @FindBy(className = "facet-keyword")
    private WebElement brandSelectionPane;

    @FindBy(className = "capacity-options")
    private WebElement capacitySelectionPane;

    @FindBy(className = "colour-options")
    private WebElement colourSelectionPane;

    private List<WebElement> products;



    public PhoneSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void findBrandAndSelect(String text){
        WebElement brandSelector = brandSelectionPane.findElement(By.id("brand-"+text));
        brandSelector.click();
    }

    public void findCapacityAndSelect(String capacity){
        WebElement capacitySelector = capacitySelectionPane.findElement(By.id("capacity-"+capacity));
        capacitySelector.click();
    }

    public void findColourAndSelect(String colour){
        List<WebElement> colourSelectors = colourSelectionPane.findElements(By.cssSelector("div.checkbox.colour-checkbox.ng-scope"));
        for(WebElement elem: colourSelectors){
            if(elem.findElement(By.cssSelector("label.colour-button.ng-binding")).getText().toLowerCase().equals(colour.toLowerCase())){
                new Actions(driver).moveToElement(elem).click().perform();
            }
        }
    }


    public void findAndSelectProduct(String product){
        List<WebElement> products = driver.findElements(By.cssSelector("div.product.ng-scope"));
        for(WebElement elem: products){
            if(elem.findElement(By.cssSelector("h3.product-title.ng-binding")).getText().toLowerCase().equals(product.toLowerCase())){
                    elem.findElement(By.cssSelector("button.button.purple")).click();
                    break;
            }
        }
    }
}
