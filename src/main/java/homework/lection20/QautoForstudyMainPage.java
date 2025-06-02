package homework.lection20;

import homework.lection19.HelperClass;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class QautoForstudyMainPage {

    private static final Logger log = LoggerFactory.getLogger(QautoForstudyMainPage.class);
    private final WebDriver driver;

    @FindBy(css = ".header_logo > svg")
    public WebElement logo;
    @FindBy(css = "button.-guest")
    public WebElement guestLogInBtn;
    @FindBy(css = "button.header_signin")
    public WebElement signInBtn;
    @FindBy(css = "button.btn-primary")
    public WebElement signUpBtn;
    @FindBy(css = "div.hero-video > iframe")
    public WebElement videoIFrame;

    @FindBy(css = "#contactsSection")
    public WebElement contactsSection;
    @FindBy(css = "div.contacts_socials")
    public WebElement socialMediaLinks;

    @FindBy(css = "ngb-modal-window")
    public WebElement signInModalWindow;

    public QautoForstudyMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1L));
        this.driver = driver;
    }

    @Step("Click 'Guest Log In' button")
    public void clickGuestLogInBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", guestLogInBtn);
    }

    public SignInModalWindow clickSignInBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", signInBtn);
        return new SignInModalWindow(driver);
    }

    public VideoIFrame switchToVideoIFrame() {
        driver.switchTo().frame(videoIFrame);
        return new VideoIFrame(driver);
    }

    public List<WebElement> getSocialMediaLinks() {
        return socialMediaLinks.findElements(
                By.cssSelector("a")
        );
    }

    public boolean navigateToSocialMedia(WebElement socialMediaLink) {
        socialMediaLink.click();
        return HelperClass.navigateToTab(driver, 1);
    }

    public boolean checkCorrectPageIsOpened(String expectedURL) {
        return Objects.equals(driver.getCurrentUrl(), expectedURL);
    }

    public class VideoIFrame {
        @FindBy(css = "a.ytp-title-link")
        private WebElement title;

        public VideoIFrame(WebDriver driver) {
             PageFactory.initElements(driver, this);
        }

        public String getTitle() {
            return title.getText();
        }
    }

    public class SignInModalWindow {
        @FindBy(id = "signinEmail")
        private WebElement emailField;
        @FindBy(id = "signinPassword")
        private WebElement passwordField;
        @FindBy(css = "div.modal-footer > button.btn-primary")
        private WebElement loginBtn;

        @FindBy(css = "p.alert-danger")
        private WebElement alertDanger;

        public SignInModalWindow(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

        public void login(String email, String password) {
            emailField.sendKeys(email);
            passwordField.sendKeys(password);
            loginBtn.click();
        }

        public boolean isAlertDisplayed() {
            return alertDanger.isDisplayed();
        }

        public String getAlertText() {
            return alertDanger.getText();
        }
    }
}
