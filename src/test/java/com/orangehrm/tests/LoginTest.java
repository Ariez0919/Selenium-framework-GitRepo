package com.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.AddEmployeePage;
import com.orangehrm.pages.ViewPersonalDetailsPage;
import com.orangehrm.pages.EmployeeListPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddEmployeePage addEmployeePage;
    ViewPersonalDetailsPage viewPersonalDetailsPage;
    //EmployeeListPage employeelistPage;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Initialize the LoginPage
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testAddEmployee() {
        // Perform login
        loginPage.login("Admin", "admin123");

        // Initialize DashboardPage
        dashboardPage = new DashboardPage(driver);

        // Navigate to Add Employee Page
        addEmployeePage = dashboardPage.navigateToAddEmployeePage();

        // Add a new employee
       // ViewPersonalDetailsPage viewPersonalDetailsPage = addEmployeePage.addEmployee("Aries", "Doe", "Smith");
          ViewPersonalDetailsPage viewPersonalDetailsPage = addEmployeePage.addEmployee("Aries", "Doe", "Test");
        
        
        // Verify the employee name on the "View Personal Details" page
        String expectedFirstName = "Aries";
        String actualFirstName = viewPersonalDetailsPage.getFirstName();
        Assert.assertEquals(actualFirstName, expectedFirstName, "First name does not match!");
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}