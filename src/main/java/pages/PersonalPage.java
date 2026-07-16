package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class PersonalPage extends BasePage{
    private static final By AVATAR_IMG = By.xpath("//div[@class='orangehrm-edit-employee-image']//img[@class='employee-image']");
    private static final By UPLOAD_BTN = By.xpath("//button[contains(@class,'employee-image-action')]");
    private static final By FILE_INPUT = By.xpath("//input[@type='file']");
    private static final By SAVE_BTN = By.xpath("//button[@type='submit']");
    private static final By SUCCESS_TOAST = By.xpath("//div[contains(@class,'oxd-toast')]");

    public PersonalPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open(String empNumber) {
        Allure.step("Open personal detail page", () -> {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPhotograph/empNumber/" + empNumber);

        wait.until(ExpectedConditions.visibilityOfElementLocated(AVATAR_IMG));
        });
    }

    public void uploadAvatar(String fileName) throws InterruptedException {
        Allure.step("Upload avatar file: " + fileName, () -> {
            WebElement avatarImg = wait.until(ExpectedConditions.elementToBeClickable(AVATAR_IMG));
            highlight(avatarImg);
            avatarImg.click();
            unhighlight(avatarImg);

            WebElement uploadBtn = wait.until(ExpectedConditions.elementToBeClickable(UPLOAD_BTN));
            highlight(uploadBtn);
            uploadBtn.click();
            unhighlight(uploadBtn);

            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(FILE_INPUT));

            String filePath = new File("src/test/resources/images/" + fileName).getAbsolutePath();
            fileInput.sendKeys(filePath);
            Thread.sleep(2000);

            WebElement saveBTN = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BTN));
            highlight(saveBTN);
            saveBTN.click();
            unhighlight(saveBTN);

            Thread.sleep(2000);
        });
    }

    public  boolean isAvatarUploadSuccessfully() throws InterruptedException {
        return Allure.step("Check Avatar Success" , () -> {
            WebElement successTeast = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_TOAST));
            return successTeast.isDisplayed();
        });
    }
}


