package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='PIM']/parent::a")
    private WebElement pimMenu;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeLink;
    
 // Admin Menu
    @FindBy(xpath = "//span[text()='Admin']/parent::a")
    private WebElement adminMenu;

    // Roles Link (under Admin menu)
    @FindBy(xpath = "//a[text()='User Roles']")
    private WebElement rolesLink;
    
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Wait for the dashboard header to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dashboardHeader));
    }

    // Method to navigate to the PIM menu
    public void navigateToPIMMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu));
        pimMenu.click();
    }

    // Method to navigate to the Add Employee page
    public AddEmployeePage navigateToAddEmployeePage() {
        // Navigate to the PIM menu first
        navigateToPIMMenu();

        // Wait for the Add Employee link to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeLink));
        addEmployeeLink.click();

        // Return the AddEmployeePage object
        return new AddEmployeePage(driver);
    }
    
    // Method to navigate to the Admin menu
    public AdminPage navigateToAdminMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu));
        adminMenu.click();
		return null;
    }
    
    public RolesPage navigateToRolesPage() {
        // Navigate to the Admin menu first
        navigateToAdminMenu();

        // Wait for the Roles link to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(rolesLink));
        rolesLink.click();

        // Return the RolesPage object
        return new RolesPage(driver);
    }
}