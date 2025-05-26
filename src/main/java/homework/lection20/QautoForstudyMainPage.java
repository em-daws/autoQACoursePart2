package homework.lection20;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class QautoForstudyMainPage {

    WebDriver driver;

    @FindBy(css = "button.-guest")
    public WebElement guestLogInBtn;

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
