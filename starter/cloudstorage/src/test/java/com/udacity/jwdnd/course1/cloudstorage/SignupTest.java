package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupTest {
    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private SignupPage signupPage;

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
        url = "http://localhost:" + port;
        signupPage = new SignupPage(driver);
    }

    @Test
    public void openSignupPage() {
        driver.get(url + "/signup");
        assertEquals(Constant.SIGNUP, driver.getTitle());
    }

    @Test
    public void signupSuccess() {
        driver.get(url + "/signup");
        signupPage.signUpUser(Constant.MOCK_FIRST_NAME, Constant.MOCK_LAST_NAME, Constant.MOCK_USERNAME, Constant.MOCK_PASSWORD);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Constant.MOCK_USERNAME, Constant.MOCK_PASSWORD);
        assertEquals(Constant.HOME, driver.getTitle());
    }
}
