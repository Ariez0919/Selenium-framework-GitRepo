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
import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.RolesPage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddEmployeePage addEmployeePage;
    ViewPersonalDetailsPage viewPersonalDetailsPage;
    AdminPage adminPage;
    RolesPage rolesPage;
    

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        // Initialize the LoginPage
        loginPage = new LoginPage(driver);
       // dashboardPage = new DashboardPage(driver);
     //   adminPage = new AdminPage(driver);
      //  rolesPage = new RolesPage(driver);
        
       
    }

    @Test
    public void testAddEmployee() {
        // Perform login
        loginPage.login("Admin", "admin123");
        

        // Initialize DashboardPage
         dashboardPage = new DashboardPage(driver);

         System.out.println("Dashboard page loaded successfully.");

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
    
    @Test(priority = 2)
    public void testSearchAdminRole() {
        // Login
        loginPage.login("Admin", "admin123");
        
        waitForDashboardPage();
        
        // Initialize DashboardPage
         dashboardPage = new DashboardPage(driver);

         System.out.println("Dashboard page loaded successfully.");

        // Navigate to Add Employee Page
         
        adminPage = dashboardPage.navigateToAdminMenu();
      //  adminPage.navigateToAdminPage();

        // Wait for Admin page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Admin']")));
        System.out.println("Admin page loaded successfully.");
        
        adminPage = new AdminPage(driver);  // ✅ Initialize before use
        rolesPage = new RolesPage(driver);
        
        rolesPage.searchRole("Admin");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     // Verify Role is Displayed
        String roleText = rolesPage.getRoleText(); // Get the text content of the role
        Assert.assertEquals(roleText, "Admin", "The role text is not equal to 'Admin'.");
        
        System.out.println(roleText);
        
       
    }


    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
    
    private void waitForDashboardPage() {
        try {
            WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
            System.out.println("Dashboard page loaded successfully.");
        } catch (Exception e) {
            System.out.println("Dashboard page did not load within the expected time.");
            throw e;
        }
    }
}