package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    // note tab
    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;
    @FindBy(id = "addNewNoteButton")
    private WebElement addNewNoteButton;
    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;
    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;
    @FindBy(id = "note-title")
    private WebElement noteTitleInput;
    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;
    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;
    @FindBy(id = "noteSaveChange")
    private WebElement noteSaveChange;
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    //credential tab
    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;
    @FindBy(id = "addNewCredentialButton")
    private WebElement addNewCredentialButton;
    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;
    @FindBy(id = "deleteCredentialButton")
    private WebElement deleteCredentialButton;
    @FindBy(id = "credential-url")
    private WebElement credentialUrlInput;
    @FindBy(id = "credential-username")
    private WebElement credentialUsernameInput;
    @FindBy(id = "credential-password")
    private WebElement credentialPasswordInput;
    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmit;
    @FindBy(id = "credentialSaveChangeButton")
    private WebElement credentialSaveChangeButton;
    @FindBy(id = "noteTitleData")
    private WebElement noteTitleData;
    @FindBy(id = "noteDescriptionData")
    private WebElement noteDescriptionData;
    @FindBy(id = "credentialURLData")
    private WebElement credentialURLData;
    @FindBy(id = "credentialUsernameData")
    private WebElement credentialUsernameData;
    @FindBy(id = "credentialPasswordData")
    private WebElement credentialPasswordData;

    private WebDriver driver;
    private JavascriptExecutor js;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public boolean checkElementExist(String id, WebDriver driver) {
        try {
            driver.findElement(By.id(id));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    public void selectNoteTab() {
        js.executeScript("arguments[0].click();", this.noteTab);
    }

    public void selectCredentialTab() {
        js.executeScript("arguments[0].click();", this.credentialsTab);
    }

    public void logout() {
        js.executeScript("arguments[0].click();", this.logoutButton);
    }

    public String getNoteTitleValue() {
        return noteTitleData.getAttribute("innerHTML");
    }

    public boolean checkNote() {
        return checkElementExist("noteTitleData", driver) && checkElementExist("noteDescriptionData", driver);
    }

    public String getNoteDescriptionValue() {
        return noteDescriptionData.getAttribute("innerHTML");
    }

    public String getCredentialUrlValue() {
        return credentialURLData.getAttribute("innerHTML");
    }

    public String getCredentialUsernameValue() {
        return credentialUsernameData.getAttribute("innerHTML");
    }

    public boolean checkCredential() {
        return checkElementExist("credentialURLData", driver) && checkElementExist("credentialUsernameData", driver)
                && checkElementExist("credentialPasswordData", driver) ;
    }

    public String getCredentialPasswordValue() {
        return credentialPasswordData.getAttribute("innerHTML");
    }

    public void addNewNote(String title, String description) {
        selectNoteTab();
        js.executeScript("arguments[0].click();", this.addNewNoteButton);
        js.executeScript("arguments[0].value='" + title + "';", this.noteTitleInput);
        js.executeScript("arguments[0].value='" + description + "';", this.noteDescriptionInput);
        js.executeScript("arguments[0].click();", this.noteSubmit);
    }

    public void editNote(String noteTitle, String noteDescription) {
        selectNoteTab();
        js.executeScript("arguments[0].click();", this.editNoteButton);
        js.executeScript("arguments[0].value='" + noteTitle + "';", this.noteTitleInput);
        js.executeScript("arguments[0].value='" + noteDescription + "';", this.noteDescriptionInput);
        js.executeScript("arguments[0].click();", this.noteSubmit);
    }

    public void deleteNote() {
        selectNoteTab();
        js.executeScript("arguments[0].click();", this.deleteNoteButton);
    }

    public void addNewCredential(String url, String username, String password) {
        selectCredentialTab();
        js.executeScript("arguments[0].click();", this.addNewCredentialButton);
        js.executeScript("arguments[0].value='" + url + "';", this.credentialUrlInput);
        js.executeScript("arguments[0].value='" + username + "';", this.credentialUsernameInput);
        js.executeScript("arguments[0].value='" + password + "';", this.credentialPasswordInput);
        js.executeScript("arguments[0].click();", this.credentialSubmit);
    }

    public void editCredential(String url, String username, String password) {
        selectCredentialTab();
        js.executeScript("arguments[0].click();", this.editCredentialButton);
        js.executeScript("arguments[0].value='" + url + "';", this.credentialUrlInput);
        js.executeScript("arguments[0].value='" + username + "';", this.credentialUsernameInput);
        js.executeScript("arguments[0].value='" + password + "';", this.credentialPasswordInput);
        js.executeScript("arguments[0].click();", this.credentialSubmit);
    }

    public void deleteCredential() {
        selectCredentialTab();
        js.executeScript("arguments[0].click();", this.deleteCredentialButton);
    }
}
