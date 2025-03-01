package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ViewPersonalDetailsPage {

    private WebDriver driver;

    // Locator for the first name field on the Personal Details page
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameField;

    public ViewPersonalDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Wait for the first name field to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
    }

    // Method to get the first name
    public String getFirstName() {
        String firstName = firstNameField.getAttribute("value"); // Use getAttribute to retrieve the value
        System.out.println("Retrieved First Name: " + firstName); // Debug statement
        return firstName;
    }
}