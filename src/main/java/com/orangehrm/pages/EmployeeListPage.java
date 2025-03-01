package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EmployeeListPage {

    private WebDriver driver;

    // Locators for Employee List page
    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement employeeInformationHeader;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeNameSearchField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement employeeNameInList;

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Wait for the Employee Information header to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(employeeInformationHeader));
    }

    // Method to search for an employee by name
    public void searchEmployeeByName(String employeeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(employeeNameSearchField));

        // Enter the employee name in the search field
        employeeNameSearchField.sendKeys(employeeName);

        // Click the Search button
        searchButton.click();
    }

    // Method to verify if the employee is in the list
    public boolean isEmployeeInList(String employeeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(employeeNameInList));
        
        String actualName = employeeNameInList.getText();
        System.out.println("Retrieved Employee Name:" + actualName);

        // Check if the employee name is displayed in the list
       // return employeeNameInList.getText().contains(employeeName);
        
        return actualName.contains(employeeName);
    }
}