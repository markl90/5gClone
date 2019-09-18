package com.netbuilder.utilities;

import com.netbuilder.test.common.utilities.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    private WebDriver driver;
    private WebDriverWait wait;
    private PageHelper page;

    public Waits(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);
        page = new PageHelper();
    }

    public void waitForProductsToLoad(){
       wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.la-ball-pulse"))));
       //confirm with javaScript executor page load is complete
       page.waitForLoad(driver);
    }
}
