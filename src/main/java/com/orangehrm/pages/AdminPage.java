package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class AdminPage {
	WebDriver driver;
	WebDriverWait wait;

    // Locators
    @FindBy(xpath = "//span[text()='Admin']")
    WebElement adminMenu;

    @FindBy(xpath = "//span[contains(text(),'User Management')]")
    WebElement userManagementMenu;

    @FindBy(xpath = "//a[contains(text(),'Users')]")
    WebElement rolesMenu;

    // Constructor
    public AdminPage(WebDriver driver) {
        this.driver = driver;
        
        System.out.println("AdminPage constructor called.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("WebDriverWait initialized: " + wait);
        PageFactory.initElements(driver, this);
    }
    
    // Methods
    public void clickAdminMenu() {
        adminMenu.click();
    }

    public void clickUserManagementMenu() {
        userManagementMenu.click();
    }

    public void clickRolesMenu() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        rolesMenu.click();
    }
    
    public void navigateToAdminPage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement adminMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Admin']")));
        adminMenu.click();
    }

    public RolesPage navigateToRolesPage() {
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	userManagementMenu.click();
      //  rolesMenu.click();
    //	WebElement rolesMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Users')]")));
      //  rolesMenu.click();
		return null;
    }
	
}
