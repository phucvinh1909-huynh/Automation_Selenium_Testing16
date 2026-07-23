package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest{

    @Test(description = ("Test Logout"))
    public void testLogout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver(),getWait());
        loginPage.login("Admin","admin123");

        DashboardPage dashboardPage = new DashboardPage(getDriver(),getWait());
        dashboardPage.Logout();

        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("auth/login"));
    }
}
