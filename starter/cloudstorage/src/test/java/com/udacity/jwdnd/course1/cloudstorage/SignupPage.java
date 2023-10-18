package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "success-msg")
    private WebElement successMsg;
    @FindBy(id = "errorMsg")
    private WebElement errorMsg;
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;
    @FindBy(id = "inputLastName")
    private WebElement inputLastName;
    @FindBy(id = "inputUsername")
    private WebElement inputUsername;
    @FindBy(id = "inputPassword")
    private WebElement inputPassword;
    @FindBy(id = "buttonSignUp")
    private WebElement buttonSignUp;


    private WebDriver driver;

    private JavascriptExecutor js;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void signUpUser(String firstName, String lastName, String username, String password) {
        js.executeScript("arguments[0].value='" + firstName + "';", this.inputFirstName);
        js.executeScript("arguments[0].value='" + lastName + "';", this.inputLastName);
        js.executeScript("arguments[0].value='" + username + "';", this.inputUsername);
        js.executeScript("arguments[0].value='" + password + "';", this.inputPassword);
        js.executeScript("arguments[0].click();", this.buttonSignUp);
    }
}
