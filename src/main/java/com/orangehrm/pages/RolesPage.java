package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RolesPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators
    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//div[contains(text(), 'Admin')]")
    WebElement roleName;
    
    @FindBy(xpath = "//div[contains(@class, 'oxd-table-cell oxd-padding-cell')][3]")
    WebElement roleCell;

    // Constructor
    public RolesPage(WebDriver driver) {
    	this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    // Methods
    public void enterRoleName(String role) {
        searchField.sendKeys(role);
    }

    public void clickSearchButton() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
        searchButton.click();
    }

    
    public void searchRole(String role) {
    	WebElement dropdownTrigger = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='oxd-select-text-input']")));
        dropdownTrigger.click(); // Expand the dropdown

        // Wait for the dropdown option to be visible
        WebElement roleOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']/span[text()='" + role + "']")));
        roleOption.click(); // Select the option

     // Click the Search button
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
    }
    
    public String getRoleText() {
        // Wait for the role cell to be visible
        wait.until(ExpectedConditions.visibilityOf(roleCell));
        return roleCell.getText(); // Get the text content
    }

	
    

}