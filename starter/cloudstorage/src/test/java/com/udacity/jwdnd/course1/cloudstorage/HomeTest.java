package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeTest {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;

    private HomePage homePage;

    private SignupPage signupPage;

    private ResultPage resultPage;

    private String url;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        url =  "http://localhost:" + port;
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        resultPage = new ResultPage(driver);
        signupPage = new SignupPage(driver);
    }

    public void signupAndLoginSuccess(String username) {
        driver.get(url + "/signup");
        signupPage.signUpUser(Constant.MOCK_FIRST_NAME, Constant.MOCK_LAST_NAME,username, Constant.MOCK_PASSWORD);
        loginPage.login(username, Constant.MOCK_PASSWORD);
    }

    public void createNote(String noteTitle, String noteDescription) {
        homePage.addNewNote(noteTitle, noteDescription);
        resultPage.clickHomeLink();
        homePage.selectNoteTab();
    }

    public void editNote(String noteTitle, String noteDescription) {
        homePage.editNote(noteTitle, noteDescription);
        resultPage.clickHomeLink();
        homePage.selectNoteTab();
    }


    public void createCredential() {
        homePage.addNewCredential(Constant.MOCK_URL, Constant.MOCK_USERNAME, Constant.MOCK_PASSWORD);
        resultPage.clickHomeLink();
        homePage.selectCredentialTab();
    }

    public void editCredential() {
        homePage.editCredential("editUrl", "editUsername", "editPassword");
        resultPage.clickHomeLink();
        homePage.selectCredentialTab();
    }

    @Test
    public void redirectLoginWithUnauthorized() {
        driver.get(url + "/home");
        assertEquals(Constant.LOGIN, driver.getTitle());
    }

    @Test
    @Order(1)
    public void createNoteSuccess() {
        signupAndLoginSuccess("user1");
        createNote(Constant.MOCK_TITLE, Constant.MOCK_DESCRIPTION);
        assertEquals(Constant.MOCK_TITLE, homePage.getNoteTitleValue());
        assertEquals(Constant.MOCK_DESCRIPTION, homePage.getNoteDescriptionValue());
    }

    @Test
    @Order(2)
    public void editNoteSuccess() {
        signupAndLoginSuccess("user2");
        createNote(Constant.MOCK_TITLE, Constant.MOCK_DESCRIPTION);
        editNote("editTile", "editDescription");
        assertEquals("editTile", homePage.getNoteTitleValue());
        assertEquals("editDescription", homePage.getNoteDescriptionValue());
    }

    @Test
    public void deleteNoteSuccess() {
        signupAndLoginSuccess("user3");
        createNote(Constant.MOCK_TITLE, Constant.MOCK_DESCRIPTION);
        homePage.deleteNote();
        resultPage.clickHomeLink();
        homePage.selectNoteTab();
        assertEquals(false, homePage.checkNote());
    }

    @Test
    public void createCredentialSuccess() {
        signupAndLoginSuccess("user4");
        createCredential();
        assertEquals(Constant.MOCK_URL, homePage.getCredentialUrlValue());
        assertEquals(Constant.MOCK_USERNAME, homePage.getCredentialUsernameValue());
    }

    @Test
    public void editCredentialSuccess() {
        signupAndLoginSuccess("user5");
        createCredential();
        editCredential();
        assertEquals("editUrl", homePage.getCredentialUrlValue());
        assertEquals("editUsername", homePage.getCredentialUsernameValue());
    }

    @Test
    public void deleteCredentialSuccess() {
        signupAndLoginSuccess("user6");
        createCredential();
        homePage.deleteCredential();
        resultPage.clickHomeLink();
        homePage.selectCredentialTab();
        assertEquals(false, homePage.checkCredential());
    }

    @Test
    public void logoutSuccess() {
        signupAndLoginSuccess("user7");
        homePage.logout();
        assertEquals(Constant.LOGIN, driver.getTitle());
    }

}
