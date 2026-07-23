package test;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;

@Epic("OrangeHRM web")
@Feature("Admin Module")
public class AdminTest extends BaseTest {
    private AdminPage adminPage;

    public void AdminPage(WebDriver driver, WebDriverWait wait) {

    }

    @BeforeMethod
    public void loginAndOpenAdminPage() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver(),getWait());
        loginPage.login("Admin", "admin123");

        adminPage = new AdminPage(getDriver(), getWait());
        adminPage.open();
    }

    @Story("Manage System User")
    @Severity(SeverityLevel.NORMAL)
    @Description("Filter by admin user")
    @Test(description = "Test filter by admin user")
    public void testFilterByAdminUser() throws InterruptedException {
        adminPage.filterByUser("Admin","Admin");
        Assert.assertTrue(adminPage.checkNumberOfRecords());
    }
}
