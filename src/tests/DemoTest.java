package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DemoTest {
    WebDriver driver;
    String browserType = "chrome";
    String password = "demo123";
    String email = "b4f@ft.com";
    String prefix = "+373";
    String phone = "69629696";
    String name = "Evgheni Barducov";
    String accountPassword = "Ddemo!123";
    String expectedConfirmation = "✨ Your registration is complete! ✨";
    boolean registrationSuccessful = false;
    String expectedLoginConfirmation = "FAST TRACK CRM - Built for Casino, Sports and Lottery";
    boolean loginSuccessful = false;
    String expectedDepositConfirmation = "✨ Your deposit was successful! ✨";
    boolean depositSuccessful = false;
    String expectedBalanceAfterDeposit = "€300.00";
    boolean depositMatches = false;
    String winText = "\uD83D\uDE80 Congratulations! \uD83D\uDE80";

    @Test(priority = 0)
    public void newUserRegistration() throws InterruptedException {
        // 1. login to the page
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        // 2. New user registration
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(1) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > div > footer > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div.column.column--wrap > input")).sendKeys(prefix);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div:nth-child(2) > div > input")).sendKeys(phone);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div:nth-child(1) > div > input")).sendKeys(name);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(accountPassword);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
        Thread.sleep(1000);
        String actualConfirmation = driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > div > h3")).getText();
        System.out.println("Registration PASS ->->->-> " + actualConfirmation);

        // 3. Assert confirmation successful
        if (actualConfirmation.equals(expectedConfirmation)) {
            registrationSuccessful = true;
        }
        Assert.assertTrue(registrationSuccessful);
    }

        // 4. Login with existing account
    @Test(priority = 1)
    public void loginWithExistingAccount() throws InterruptedException {
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();

        // 5. Assert login successful
        Thread.sleep(1000);
        String loginConfirmation = driver.findElement(By.cssSelector("#__layout > div > main > div.container.big > div > div > h1")).getText();
        System.out.println("Login PASS ->->->-> " + loginConfirmation);

        // 3. Assert confirmation successful
        if (loginConfirmation.equals(expectedLoginConfirmation)) {
            loginSuccessful = true;
        }
        Assert.assertTrue(loginSuccessful);
    }

    @Test(priority = 2)
    public void depositAmount() throws InterruptedException {
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div.step1 > div.form-row > select > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div.step1 > div.buttons > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(2) > div > div:nth-child(2) > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(3) > div > button:nth-child(1)")).click();
        String actualDepositConfirmation = driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(4) > h3")).getText();
        System.out.println("Deposit PASS ->->->-> " + actualDepositConfirmation);

        // ?. Assert deposit confirmation successful
        if (actualDepositConfirmation.equals(expectedDepositConfirmation)) {
            depositSuccessful = true;
        }
        Assert.assertTrue(depositSuccessful);
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(4) > div > button")).click();
        // Assert Balance Matches
        String actualDepositBalance = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();

        if (actualDepositBalance.equals(expectedBalanceAfterDeposit)) {
            depositMatches = true;
            System.out.println("Balance PASS ->->->-> " + actualDepositBalance);
        } else {
            System.out.println("Balance FAIL ->->->-> " + actualDepositBalance);
        }
        Assert.assertTrue(depositMatches);
    }
    @Test(priority = 3)
    public void playGame() throws InterruptedException {
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
        String currentDebit = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        // Open menu
        driver.findElement(By.cssSelector("#navigation > svg")).click();
        // Select Game
        driver.findElement(By.cssSelector("#__layout > div > header > div.sidebar.sidebar--show > div:nth-child(1) > a")).click();
        // Click on the cat
        driver.findElement(By.cssSelector("#__layout > div > main > div.casino-page > div > div > div.game__buttons > div:nth-child(1) > div:nth-child(1) > img")).click();
        Thread.sleep(3000);
        currentDebit = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        System.out.println("New debit is ->->->-> " + currentDebit);

    }


    @BeforeMethod
    public void setUp() {
        driver = utilities.DriverFactory.open(browserType);
        driver.get("https://demo.ft-crm.com/password");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
