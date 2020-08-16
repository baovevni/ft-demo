package tests;

/*
1. Make sure we can register on the casino.
2. Make sure we can log in with an existing user.
3. Make sure we can deposit and check so the balance matches.
4. Make sure that we can play a game and the balance updates.
5. Make sure we can buy a lottery ticket and balance is updated.
 */


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DemoTest {
    WebDriver driver;
    String browserType = "chrome";
    String password = "demo123";
    String email = "b33t@ft.com";
    String prefix = "+373";
    String phone = "69629696";
    String name = "Evgheni Barducov";
    String accountPassword = "Ddemo!123";
    String expectedConfirmation = "✨ Your registration is complete! ✨";
    String expectedLoginConfirmation = "FAST TRACK CRM - Built for Casino, Sports and Lottery";
    String expectedAlertText = "Lower your bet to play again.";
    String expectedDepositConfirmation = "✨ Your deposit was successful! ✨";
    boolean registrationSuccessful = false;
    boolean loginSuccessful = false;
    boolean alertSuccessful = false;
    boolean depositSuccessful;
    boolean depositMatches ;

    @Test(priority = 0) // 1. Make sure we can register on the casino
    public void newUserRegistration() throws InterruptedException {
        // New user registration
        registerNewUser();
        // Get registration confirmation
        String actualConfirmation = driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > div > h3")).getText();

        // Assert confirmation successful
        if (actualConfirmation.equals(expectedConfirmation)) {
            System.out.println("Registration PASS ->->->-> " + actualConfirmation);
            registrationSuccessful = true;
        }
        Assert.assertTrue(registrationSuccessful);
    }
    @Test(priority = 1) // 1. Make sure we can register on the casino
    public void newUserRegistrationWithSameEmail() throws InterruptedException {
        // New user registration
        registerNewUser();
        // Get registration confirmation
        String actualConfirmation = driver.switchTo().alert().getText();
        // Assert confirmation successful
        if (actualConfirmation.equals("Email already registered")) {
            System.out.println("Registration with same email is not possible PASS ->->->-> " + actualConfirmation);
            registrationSuccessful = true;
        }
        Assert.assertTrue(registrationSuccessful);
    }
    @Test(priority = 2) // 2. Make sure we can log in with an existing user
    public void loginWithExistingAccount() throws InterruptedException {
        // Login with existing account
        login();
        // Assert login successful
        Thread.sleep(1000);
        String loginConfirmation = driver.findElement(By.cssSelector("#__layout > div > main > div.container.big > div > div > h1")).getText();

        // Assert confirmation successful
        if (loginConfirmation.equals(expectedLoginConfirmation)) {
            System.out.println("Login PASS ->->->-> " + loginConfirmation);
            loginSuccessful = true;
        }else{
            System.out.println("Login FAIL ->->->-> " + loginConfirmation);
            loginSuccessful = false;
        }
        Assert.assertTrue(loginSuccessful);
    }

    @Test(priority = 3) // 4.1. Make sure that we can't play a game with Zero balance.
    public void playGameWithZeroBalance() throws InterruptedException {
        // Login with existing account
        login();
        // Open menu
        driver.findElement(By.cssSelector("#navigation > svg")).click();
        // Select Game
        driver.findElement(By.cssSelector("#__layout > div > header > div.sidebar.sidebar--show > div:nth-child(1) > a")).click();
        // Click on the cat
        driver.findElement(By.cssSelector("#__layout > div > main > div.casino-page > div > div > div.game__buttons > div:nth-child(1) > div:nth-child(1) > img")).click();
        // Get Alert Text
        String actualAlertText = driver.switchTo().alert().getText();
        // Assert alert
        if (actualAlertText.equals(expectedAlertText)) {
            alertSuccessful = true;
        }
        Assert.assertTrue(alertSuccessful);
    }

    @Test(priority = 4) // 3. Make sure we can deposit and check so the balance matches.
    public void depositAmountWithoutBonus() throws InterruptedException{
        // Login with existing account
        login();
        // Click on current amount
        driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).click();
        // Select CARD payment method
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div.step1 > div.buttons > button:nth-child(1)")).click();
        // Select amount Eur 100
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(2) > div > div:nth-child(2) > button:nth-child(1)")).click();
        // Select Deposit approved option
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(3) > div > button:nth-child(1)")).click();
        // Get confirmation
        String actualDepositConfirmation = driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(4) > h3")).getText();
        // Assert deposit confirmation successful
        if (actualDepositConfirmation.equals(expectedDepositConfirmation)) {
            System.out.println("Deposit PASS ->->->-> " + actualDepositConfirmation);
            depositSuccessful = true;
        } else {
            System.out.println("Deposit FAIL ->->->-> " + actualDepositConfirmation);
        }
        Assert.assertTrue(depositSuccessful);
        // Close pop-up
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(4) > div > button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#fasttrack-crm > div:nth-child(1) > div.small-notifications-wrapper > div > div.close-btn")).click();
        // Assert Balance Matches
        String expectedBalanceAfterDeposit = "€100.00";
        String actualDepositBalance = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        if (actualDepositBalance.equals(expectedBalanceAfterDeposit)) {
            depositMatches = true;
            System.out.println("Balance PASS ->->->-> " + actualDepositBalance);
        } else {
            System.out.println("Balance FAIL ->->->-> " + actualDepositBalance + "Expected Balance: " + expectedBalanceAfterDeposit);
        }
        Assert.assertTrue(depositMatches);
        // Close pop-up
    }

    @Test(priority = 5) // 3. Make sure we can deposit and check so the balance matches.
    public void depositAmountWithBonus() throws InterruptedException{
        // Login with existing account
        login();
        // Click on current amount
        driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).click();
        // Select Welcome bonus 200% to be applied
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div.step1 > div.form-row > select > option:nth-child(2)")).click();
        // Select Direct Bank payment method
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div.step1 > div.buttons > button:nth-child(2)")).click();
        // Select amount Eur 10
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(2) > div > div:nth-child(1) > button:nth-child(1)")).click();
        // Select Deposit approved option
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(3) > div > button:nth-child(1)")).click();
        // Get confirmation
        String actualDepositConfirmation = driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(4) > h3")).getText();
        // Assert deposit confirmation successful
        if (actualDepositConfirmation.equals(expectedDepositConfirmation)) {
            System.out.println("Deposit PASS ->->->-> " + actualDepositConfirmation);
            depositSuccessful = true;
        } else {
            System.out.println("Deposit FAIL ->->->-> " + actualDepositConfirmation);
        }
        Assert.assertTrue(depositSuccessful);
        // Close pop-up
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(4) > div > button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#fasttrack-crm > div:nth-child(1) > div.inbox-wrapper > div.inbox > div.close-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#fasttrack-crm > div:nth-child(1) > div.small-notifications-wrapper > div > div.close-btn")).click();
        // Assert Balance Matches
        String expectedBalanceAfterDeposit = "€130.00";
        String actualDepositBalance = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        if (actualDepositBalance.equals(expectedBalanceAfterDeposit)) {
            System.out.println("Balance PASS ->->->-> " + actualDepositBalance);
            depositMatches = true;
        } else {
            System.out.println("Balance FAIL ->->->-> " + actualDepositBalance + "Expected Balance: " + expectedBalanceAfterDeposit);
        }
        Assert.assertTrue(depositMatches);
        // Close pop-up

    }

    @Test(priority = 6) // 4. Make sure that we can play a game and the balance updates.
    public void playGame() throws InterruptedException {
        // Login with existing account
        login();
        // Open menu
        driver.findElement(By.cssSelector("#navigation > svg")).click();
        // Select Game
        driver.findElement(By.cssSelector("#__layout > div > header > div.sidebar.sidebar--show > div:nth-child(1) > a")).click();
        // Click on the cat
        driver.findElement(By.cssSelector("#__layout > div > main > div.casino-page > div > div > div.game__buttons > div:nth-child(1) > div:nth-child(1) > img")).click();
        // Get current debit
        String currentDebit = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        System.out.println("Current Debit is ->->->-> " + currentDebit);
        Thread.sleep(3000);
        // Assert new debit
        currentDebit = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        System.out.println("New debit is ->->->-> " + currentDebit);
        // Close pop-up
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#fasttrack-crm > div:nth-child(1) > div.small-notifications-wrapper > div > div.close-btn")).click();

    }
    @Test(priority = 7) // 5. Make sure we can buy a lottery ticket and balance is updated.
    public void buyLotteryTicket() throws InterruptedException {
        // Login with existing account
        login();
        // Open menu
        driver.findElement(By.cssSelector("#navigation > svg")).click();
        // Select Lottery
        driver.findElement(By.cssSelector("#__layout > div > header > div.sidebar.sidebar--show > div:nth-child(3) > a")).click();
        // Add another ticket
        driver.findElement(By.cssSelector("#__layout > div > main > div.lottery-page > div > div.button-wrapper > button")).click();
        // Select 7 numbers {Random values to be added in the next release}
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/main/div[1]/div/div[1]/div[2]/div/div/div[22]")).click();
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/main/div[1]/div/div[1]/div[2]/div/div/div[48]")).click();
        driver.findElement(By.cssSelector("#__layout > div > main > div.lottery-page > div > div.lottery-tickets > div:nth-child(2) > div > div > div:nth-child(27)")).click();
        driver.findElement(By.cssSelector("#__layout > div > main > div.lottery-page > div > div.lottery-tickets > div:nth-child(2) > div > div > div:nth-child(19)")).click();
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/main/div[1]/div/div[1]/div[2]/div/div/div[35]")).click();
        driver.findElement(By.xpath("//*[@id=\"__layout\"]/div/main/div[1]/div/div[1]/div[2]/div/div/div[39]")).click();
        driver.findElement(By.cssSelector("#__layout > div > main > div.lottery-page > div > div.lottery-tickets > div:nth-child(2) > div > div > div:nth-child(13)")).click();
        // Select 2 Draws
        new Select(driver.findElement(By.cssSelector("#__layout > div > main > div.lottery-page > div > div.betting-module-wrapper > div > div > div:nth-child(1) > select"))).selectByValue("2");
        // Get current debit
        String currentDebit = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        System.out.println("Current Debit is ->->->-> " + currentDebit);
        // Buy tickets
        driver.findElement(By.cssSelector("#__layout > div > main > div.lottery-page > div > div.betting-module-wrapper > div > div > div.column.column--wrap > button")).click();
        // Assert new balance {Balance Calulation to be added in the next release}
        Thread.sleep(3000);
        currentDebit = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        System.out.println("New debit is ->->->-> " + currentDebit);

    }

    // Login method {reused}
    public void login(){
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
    }

    // Register new user method {reused}
    public void registerNewUser() throws InterruptedException{
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
    }

    @BeforeMethod
    public void setUp() {
        // Define the browser, open page
        driver = utilities.DriverFactory.open(browserType);
        driver.get("https://demo.ft-crm.com/password");
        // Enter into the system
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
