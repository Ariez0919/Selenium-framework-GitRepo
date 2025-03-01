package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BaseClass {
    public static WebDriver driver;

    public static void initialization() {
        // Use WebDriverManager to automatically set up ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Optional: Set Chrome options (useful if browser startup fails)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Initialize the WebDriver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Use modern Selenium 4 timeout settings
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the OrangeHRM login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void tearDown() {
        // Close the browser
       if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
