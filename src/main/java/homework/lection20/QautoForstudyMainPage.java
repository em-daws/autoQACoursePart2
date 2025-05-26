package homework.lection20;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class QautoForstudyMainPage {

    private final WebDriver driver;

    @FindBy(css = ".header_logo > svg")
    public WebElement logo;

    @FindBy(css = "button.-guest")
    public WebElement guestLogInBtn;

    @FindBy(css = "button.btn-primary")
    public WebElement signUpBtn;

    public QautoForstudyMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1L));
        this.driver = driver;
    }

    public void clickGuestLogInBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", guestLogInBtn);
    }
}
