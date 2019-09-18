package com.netbuilder.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContractSelectionPage {

    private WebDriver driver;


    private final String twelveMonthRadioButton = "//*[@id=\"mobileProduct\"]/div/div[3]/div[2]/div[1]/div[2]/div[3]/div[1]/label" ;
    private final String twentyFourMonthRadioButton = twelveMonthRadioButton.replace("/div[1]/label", "/div[2]/label");
    private final String thirtySixMonthRadioButton = twentyFourMonthRadioButton.replace("/div[2]/label", "/div[3]/label");



    public ContractSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public WebElement findAndClickContractRadioSelector(String contractLength){
        contractLength = contractLength.toLowerCase();
        WebElement radioButton = contractLength.contains("12 month") ? driver.findElement(By.xpath(twelveMonthRadioButton)):
                                 contractLength.contains("24 month") ? driver.findElement(By.xpath(twentyFourMonthRadioButton)):
                                                                       driver.findElement(By.xpath(thirtySixMonthRadioButton));
        radioButton.click();
        return radioButton;
    }

    public void findAndSelectDataPlan(String dataPlan){
        WebElement container =  driver.findElement(By.xpath("//*[@id=\"mobileProduct\"]/div/div[3]/div[2]/div[2]/div/div[1]"));
        List<WebElement> plans = container.findElements(By.cssSelector("div.component-product-plan-selector__plan_contents"));
        for(WebElement elem: plans){
            if(elem.findElement(By.cssSelector("div.component-product-plan-selector__plan_data_name.ng-binding")).getText().contains(dataPlan)){
                elem.findElement(By.cssSelector("a.component-product-plan-selector__plan-cta-link.ng-scope")).click();
                break;
            }
        }

    }


}
