package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.testng.annotations.Test;
import org.testng.Assert;

public class TestNG {

    @Test(priority = 1)
    public void loginWithValidCredentials() throws InterruptedException {
        // Set the system property for Chrome driver with its path
        WebDriverManager.chromedriver().setup();
        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        // Navigate to the login page
        driver.get("https://rahulnxttrendz.ccbp.tech/login");
        //to maximize the window
        driver.manage().window().maximize();

        // Locate the username input field element by its id
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys("rahul");

        // Locate the password input field element by its id
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("rahul@2021");

        // Find the login button element by its class name
        WebElement loginButtonEl = driver.findElement(By.className("login-button"));

        // Submit the login form
        loginButtonEl.submit();

        // Declare expected URL
        String expectedUrl = "https://rahulnxttrendz.ccbp.tech/";

        // Introduce a 2-second delay
        Thread.sleep(2000);

        // Wait for the page to be redirected to the expected URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        // Get the current URL
        String currentUrl = driver.getCurrentUrl();

        // Verify if the expected heading matches the actual heading
        Assert.assertEquals(expectedUrl, currentUrl, "URLs do not match");

        // Close the driver instance
        driver.quit();
    }

    @Test(priority = 2)
    public void loginWithInvalidCredentials() throws InterruptedException {
        // Set the system property for Chrome driver with its path
        WebDriverManager.chromedriver().setup();

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        //to maximize the window
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("https://rahulnxttrendz.ccbp.tech/login");
        //to maximize the window
        driver.manage().window().maximize();

        // Locate and enter valid username input field element by its id
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys("rahul");

        // Locate and enter invalid password input field element by its id
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("rahul2021");

        // Find the login button element by its class name
        WebElement loginButtonEl = driver.findElement(By.className("login-button"));

        // Submit the login form
        loginButtonEl.submit();

        // Introduce a 2-second delay
        Thread.sleep(2000);

        // Wait for the Error message to be loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));

        // Verify the error message
        WebElement errorMessageEl = driver.findElement(By.className("error-message"));
        String errorMessage = errorMessageEl.getText();
        Assert.assertEquals(errorMessage, "*username and password didn't match");

        // Close the browser
        driver.close();
    }
}
