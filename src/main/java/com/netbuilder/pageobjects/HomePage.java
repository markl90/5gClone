package com.netbuilder.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    @FindBy(id = "cookie-accept")
    private WebElement cookieAcceptButton;

    @FindBy(xpath = "/html/body/header/div[2]/div[2]/div/div[2]/div[2]/a")
    private WebElement phonesLink;

    @FindBy(xpath = "//*[@id=\"productsLink\"]/div")
    private WebElement productsAndServiceNavLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getCookieAcceptButton() {
        return cookieAcceptButton;
    }

    public void waitAndClickCloseCookies(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(cookieAcceptButton));
        cookieAcceptButton.click();
    }

    public void clickPhonesLink(){
        phonesLink.click();
    }

    public void hoverOverProductsAndServicesDropDown(){
        Actions actions = new Actions(driver);
        actions.moveToElement(productsAndServiceNavLink).perform();
    }
}
