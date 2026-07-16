package tesst;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PersonalPage;
import utils.ConfigReader;

@Epic("OrangeHRM web")
@Feature("PIM = Employee photo")
public class AvatarUploadTest extends BaseTest{
    private static final String EMP_NUMBER = "7";
    private static final String AVATAR_TEST = "avatar_test.png";
    private PersonalPage personalPage;

    @BeforeMethod
        public void loginAndOpenPersonalPage() throws InterruptedException {
            LoginPage loginPage = new LoginPage(getDriver(), getWait());
            String username = ConfigReader.get("admin.username");
            String password = ConfigReader.get("admin.password");
            loginPage.login(username, password);

            personalPage = new PersonalPage(getDriver(), getWait());
            personalPage.open(EMP_NUMBER);
        }

    @Story("Upload Avatar")
    @Severity(SeverityLevel.NORMAL)
    @Description("Upload avatar success")
    @Test(description = "Test upload avatar success")
    public void testUploadAvatarSuccess() throws InterruptedException {
        personalPage.uploadAvatar(AVATAR_TEST);

        boolean isUploaded = personalPage.isAvatarUploadSuccessfully();
        Assert.assertTrue(isUploaded,"Avatar Upload fail");
    }
}
