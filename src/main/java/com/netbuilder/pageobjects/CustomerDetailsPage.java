package com.netbuilder.pageobjects;

import com.netbuilder.utilities.Waits;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class CustomerDetailsPage {

    private WebDriver driver;
    private Actions act;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"title\"]")
    private WebElement titleDropDown;

    @FindBy(css = "input#firstname")
    private WebElement firstNameInput;

    @FindBy(css = "input#lastname")
    private WebElement lastNameInput;

    @FindBy(css = "input#email")
    private WebElement emailInput;

    @FindBy(css = "input.component-checkoutv2-step-editor-form-checkboxlabel")
    private WebElement emailLinkCheckBox;

    @FindBy(css = "input#phone")
    private WebElement mainContactNumberInput;

    @FindBy(css = "input#secondaryphone")
    private WebElement secondaryContactNumber;

    @FindBy(xpath = "/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[1]/div/div[2]/div/div[2]/div[13]/div[1]/p[2]/label/span")
    private WebElement marketingEmailCheckbox;

    @FindBy(css = "button.component-checkoutv2-step-next-button")
    private WebElement nextButton;

    public CustomerDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.act = new Actions(driver);
        this.wait = new WebDriverWait(driver, 60);
        PageFactory.initElements(driver, this);
    }

    public String newString(int length){
        String generatedString = RandomStringUtils.random(length, true, false);
        return generatedString;
    }

    public String newNumber(int length){
        String generatedString = "0";
        generatedString += RandomStringUtils.random(length-1, false, true);
        return generatedString;
    }

    public String newEmail(){
        String name = RandomStringUtils.random(5, true, true);
        String domain = RandomStringUtils.random(5, true, false);
        String email = String.format("%s@%s.com", name, domain);
        return email;
    }

    public String newDateOfBirth(){
        Random rand = new Random();
        int day = rand.nextInt(30);
        int month = rand.nextInt(12);
        int year = rand.nextInt(99);
        String dob = String.format("%s/%s/19%s", day, month, year);
        return dob;
    }

    public void selectTitle(String title){
        titleDropDown.click();
        titleDropDown.sendKeys(title);
    }

    public void enterFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email){
        emailInput.sendKeys(email);
    }

    public void enterMainContactNumber(String mainContactNumber){
        mainContactNumberInput.sendKeys(mainContactNumber);
    }

    public void selectMarketingEmailCheckbox(){
        marketingEmailCheckbox.click();
    }

    public void selectNextSection(){
        nextButton.click();
    }

    public void sendDetails(){
        driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[1]/div/div[2]/div/div[2]/div[1]/label[1]/span")).click();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys("Mr").perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(newString(5)).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(newString(7)).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(newEmail()).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(newNumber(11)).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(newNumber(11)).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.ENTER).perform();
    }

    public void sendBusinessTypeDetails(){
        WebElement businessAddressSpan = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[2]/div/div[2]/div/div[2]/div[8]/div[1]/label"));
        wait.until(ExpectedConditions.visibilityOf(businessAddressSpan));
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys("Sole Trader").perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys("netbuilder").perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys("bn3 6bb").perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.ENTER).perform();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[2]/div/div[2]/div/div[2]/div[7]/div[1]/div[2]"))));
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.DOWN).perform();
        act.sendKeys(Keys.ENTER).perform();
        act.sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.ENTER).perform();
    }

    public void sendDirectoryListingsInformation(){
        WebElement directoryListingsHeader = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[3]/div/div[2]/div/div[2]/div[2]/h2"));
        WebElement formContainer = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[3]/div/div[1]"));
        wait.until(ExpectedConditions.visibilityOf(directoryListingsHeader));
        wait.until(ExpectedConditions.attributeToBe(formContainer, "style", "opacity: 0; display: none;"));
        act.moveToElement(directoryListingsHeader).click().perform();
        act.sendKeys(Keys.TAB).perform();
//        act.sendKeys(Keys.TAB).perform();
        act.sendKeys("not").perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.ENTER).perform();
        WebElement deliveryAddressRadioButton = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[4]/div/div[2]/div/div[2]/label[1]"));
        WebElement deliveryContainer = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[4]"));
        wait.until(ExpectedConditions.visibilityOf(deliveryAddressRadioButton));
        wait.until(ExpectedConditions.attributeToBe(deliveryContainer, "style", "height: 347px;"));
        act.moveToElement(deliveryAddressRadioButton).click().perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.ENTER).perform();
    }


    public void sendCreditCheckInformation(){
        WebElement creditCheckContainer = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[5]"));
        WebElement nextButton = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[3]/div/div[2]/div/div[2]/button"));
        wait.until(ExpectedConditions.attributeToBe(creditCheckContainer, "style", "height: 490px;"));
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(newDateOfBirth()).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.ENTER).perform();
    }

    public void sendPaymentDetails(){
        WebElement placeOrderButton = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[6]/div/div[2]/div/div[2]/button[1]"));
        WebElement paymentDetailsContainer = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[6]"));
        wait.until(ExpectedConditions.attributeToBe(paymentDetailsContainer, "style", "height: 1161px;"));
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys("marj spark").perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(newNumber(8)).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(newNumber(2)).perform();
        act.sendKeys(newNumber(2)).perform();
        act.sendKeys(newNumber(2)).perform();
        WebElement confirmationTickBox = driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/div[12]/div/div[3]/div[1]/div[6]/div/div[2]/div/div[2]/div[4]/div/label"));
        act.moveToElement(confirmationTickBox).click().perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.TAB).perform();
        act.sendKeys(Keys.ENTER).perform();
    }




}
