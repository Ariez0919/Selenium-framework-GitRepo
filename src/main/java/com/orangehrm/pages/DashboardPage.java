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
}