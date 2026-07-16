package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class LoginPage extends BasePage {
    //   <input data-v-1f99f73c=
    //        "" class="oxd-input oxd-input--active"
    //        name="username" placeholder="Username" autofocus="">
    private static final By USERNAME_INPUT = By.xpath("//input[@name='username']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        Allure.step("Open login page",() -> {
            String url = ConfigReader.get("login.base.url");
            driver.get(url);
            wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
            ScreenshotUtil.takeScreenshot(driver, "login_page");
        });
    }

    public void userNameEnter(String username) throws InterruptedException {
        Allure.step("Enter username: " + username, () -> {
            WebElement usernameInput = driver.findElement(USERNAME_INPUT);
            highlight(usernameInput);
            usernameInput.sendKeys(username);
            ScreenshotUtil.takeScreenshot(driver, "enter_username");
            Thread.sleep(2000);
            unhighlight(usernameInput);
        });
    }

    public void userPasswordEnter(String password) throws InterruptedException {
        Allure.step("Enter password: ", () -> {
            WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
            highlight(passwordInput);
            passwordInput.sendKeys(password);
            ScreenshotUtil.takeScreenshot(driver, "enter_password");
            Thread.sleep(2000);
            unhighlight(passwordInput);
        });
    }

    public void loginButtonClick() throws InterruptedException {
        Allure.step("Click login button", () -> {
            WebElement loginButton = driver.findElement(LOGIN_BUTTON);
            highlight(loginButton);

            ScreenshotUtil.takeScreenshot(driver, "click_login_button");
            Thread.sleep(1000);
            loginButton.click();

            Thread.sleep(1000);
            unhighlight(loginButton);
        });
    }

    // gom 3 step lai
    public void login(String username, String password) throws InterruptedException {
        open();
        userNameEnter(username);
        userPasswordEnter(password);
        loginButtonClick();
    }
}
