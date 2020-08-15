package tests;

/*
1. Make sure we can register on the casino
2. Make sure we can log in with an existing user
3. Make sure we can deposit and check so the balance matches.
4. Make sure that we can play a game and the balance updates.
5. Make sure we can buy a lottery ticket and balance is updated.
 */
// in the next releases to be added

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
    String email = "b12t@ft.com";
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
    boolean depositMatches = false;

    @Test(priority = 0) // 1. Make sure we can register on the casino
    public void newUserRegistration() throws InterruptedException {
        // Enter into the system
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        // New user registration
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
        // Get registration confirmation
        String actualConfirmation = driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > div > h3")).getText();
        System.out.println("Registration PASS ->->->-> " + actualConfirmation);

        // Assert confirmation successful
        if (actualConfirmation.equals(expectedConfirmation)) {
            registrationSuccessful = true;
        }
        Assert.assertTrue(registrationSuccessful);
    }

    @Test(priority = 1) // 2. Make sure we can log in with an existing user
    public void loginWithExistingAccount() throws InterruptedException {
        // Enter into the system
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        // Login with existing account
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();

        // Assert login successful
        Thread.sleep(1000);
        String loginConfirmation = driver.findElement(By.cssSelector("#__layout > div > main > div.container.big > div > div > h1")).getText();
        System.out.println("Login PASS ->->->-> " + loginConfirmation);

        // Assert confirmation successful
        if (loginConfirmation.equals(expectedLoginConfirmation)) {
            loginSuccessful = true;
        }
        Assert.assertTrue(loginSuccessful);
    }

    @Test(priority = 2) // 3. Make sure we can deposit and check so the balance matches.
    public void depositAmountWithoutBonus() throws InterruptedException {
        // Enter into the system
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        // Login with existing account
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
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
            depositSuccessful = false;
        }
        Assert.assertTrue(depositSuccessful);
        // Close pop-up
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(4) > div > button")).click();
        // Assert Balance Matches
        String expectedBalanceAfterDeposit = "€100.00";
        String actualDepositBalance = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        if (actualDepositBalance.equals(expectedBalanceAfterDeposit)) {
            depositMatches = true;
            System.out.println("Balance PASS ->->->-> " + actualDepositBalance);
        } else {
            System.out.println("Balance FAIL ->->->-> " + actualDepositBalance);
            depositMatches = false;
        }
        Assert.assertTrue(depositMatches);
        // Close pop-up
        driver.findElement(By.cssSelector("#fasttrack-crm > div:nth-child(1) > div.small-notifications-wrapper > div > div.close-btn")).click();
    }

    @Test(priority = 3) // 3. Make sure we can deposit and check so the balance matches.
    public void depositAmountWithBonus() throws InterruptedException {
        // Enter into the system
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        // Login with existing account
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
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
            depositSuccessful = false;
        }
        Assert.assertTrue(depositSuccessful);
        // Close pop-ups
        driver.findElement(By.cssSelector("#__layout > div > div.modal > div.modal__wrapper > div > div.modal__body > div:nth-child(4) > div > button")).click();
        driver.findElement(By.cssSelector("#fasttrack-crm > div:nth-child(1) > div.small-notifications-wrapper > div > div.close-btn")).click();
        // Assert Balance Matches
        String expectedBalanceAfterDeposit = "€130.00";
        String actualDepositBalance = driver.findElement(By.cssSelector("#__layout > div > main > div.deposit-wrapper > button")).getText();
        if (actualDepositBalance.equals(expectedBalanceAfterDeposit)) {
            depositMatches = true;
            System.out.println("Balance PASS ->->->-> " + actualDepositBalance);
        } else {
            System.out.println("Balance FAIL ->->->-> " + actualDepositBalance + "Expected Balance: " + expectedBalanceAfterDeposit);
            depositMatches = false;
        }
        Assert.assertTrue(depositMatches);
    }

    @Test(priority = 4) // 4. Make sure that we can play a game and the balance updates.
    public void playGame() throws InterruptedException {
        // Enter into the system
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        // Login with existing account
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
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
        driver.findElement(By.cssSelector("#fasttrack-crm > div:nth-child(1) > div.small-notifications-wrapper > div > div.close-btn")).click();

    }
    @Test(priority = 5) // 5. Make sure we can buy a lottery ticket and balance is updated.
    public void buyLotteryTicket() throws InterruptedException {
        // Enter into the system
        driver.findElement(By.cssSelector("#__layout > div > div > form > input[type=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("#__layout > div > div > form > div > button")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        // Login with existing account
        driver.findElement(By.cssSelector("#__layout > div > footer > div > div:nth-child(2) > button")).click();
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div > div > input")).sendKeys(email);
        driver.findElement(By.cssSelector("#__layout > div > div > div.modal__wrapper > div > div.modal__body > form > section > div > div > div.column.column--wrap > button")).click();
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


    @BeforeMethod
    public void setUp() {
        driver = utilities.DriverFactory.open(browserType);
        driver.get("https://demo.ft-crm.com/password");
    }

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }

}
