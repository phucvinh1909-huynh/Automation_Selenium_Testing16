package tesst;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;
import utils.CsvReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Epic("OrangeHRM web")
@Feature("Authentication")

public class LoginTest extends BaseTest{

    @Story("Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login success with login Username & **** Pass and redirect Dashboard Page")
    @Test(description = "Test Login Suceess")
    public void testLoginSuccess() throws Exception{
        LoginPage loginPage = new LoginPage(getDriver(),getWait());

        String username = ConfigReader.get("admin.username");
        String password = ConfigReader.get("admin.password");

        loginPage.login(username,password);

       String currentUrl = getDriver().getCurrentUrl();

       Assert.assertTrue(currentUrl.contains("dashboard"));
    }
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login fail with...")
    @Test(description = "Test login fail")
    public void testLoginFail() throws Exception{
        LoginPage loginPage = new LoginPage(getDriver(),getWait());
        loginPage.login("Admin","admin1234");
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));
        Assert.assertFalse(currentUrl.contains("dashboard"));
    }

    @DataProvider(name= "Login Data")
    public Object[][] loginDataProvider()throws IOException {
        String filePath = getClass().getClassLoader().getResource("loginData.csv").getPath();

        List<String[]> data = CsvReader.readCsvFile(filePath);

        return CsvReader.toDataProviderArray(data);
    }

    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login with data provider")
    @Test(description = "Test login data provinder", dataProvider = "Login Data")
    public void testLoginData(String username, String password,String expectedResult) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver(),getWait());
        loginPage.login(username,password);

        if(expectedResult.equalsIgnoreCase("success")){
            String currentUrl = getDriver().getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("dashboard"));
        } else{
            Assert.assertTrue(getDriver().getCurrentUrl().contains("login"));
        }

    }
}
