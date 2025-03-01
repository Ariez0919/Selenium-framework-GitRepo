package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddEmployeePage {

    private WebDriver driver;

    // Locators for Add Employee page
    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "middleName")
    private WebElement middleNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to add a new employee
    public ViewPersonalDetailsPage addEmployee(String firstName, String middleName, String lastName) {
        // Wait for the fields to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstNameField));

        // Enter employee details
        firstNameField.sendKeys(firstName);
        middleNameField.sendKeys(middleName);
        lastNameField.sendKeys(lastName);
        
        System.out.println("Entered First Name: " + firstName);
        System.out.println("Entered Middle Name: " + middleName);
        System.out.println("Entered Last Name: " + lastName);

        // Click the Save button
        saveButton.click();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Return the next page object (ViewPersonalDetailsPage)
        return new ViewPersonalDetailsPage(driver);
    }
}