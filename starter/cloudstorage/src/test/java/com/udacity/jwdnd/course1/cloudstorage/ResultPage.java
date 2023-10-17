package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id = "homeLinkSuccess")
    private WebElement homeLinkSuccess;

    private WebDriver driver;

    private JavascriptExecutor js;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void clickHomeLink() {
        js.executeScript("arguments[0].click();", this.homeLinkSuccess);
    }

}
