package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private LoginPage loginPage;

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
    }

    @Test
    public void openLoginPageWithUnauthorized() {
        driver.get(url + "/login");
        assertEquals(Constant.LOGIN, driver.getTitle());
    }
}
