package com.example.gematria.selenium.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Sleeper;

import static org.junit.jupiter.api.Assertions.*;

class GematriaFlowSeleniumIT {

    private WebDriver driver;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:5173");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void userCanComputeGematria() {
        driver.get(baseUrl);

        // adapte les sélecteurs à ton UI (ex: data-testid)
        WebElement input = driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/label/input"));
        input.sendKeys("שלום");

        WebElement btn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/form/button"));
        btn.click();

        WebElement result = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/pre"));
        assertTrue(result.getText().length() > 0);
    }
    
    
    @Test
    void isGuematriaAtbash() {
        driver.get(baseUrl);

        System.out.println("ceci est juste un autre test fictif");

    }
}
