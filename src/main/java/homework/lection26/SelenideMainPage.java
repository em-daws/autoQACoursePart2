package homework.lection26;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;

public class SelenideMainPage {
    public SelenideElement guestLogInBtn = Selenide.$(By.cssSelector("button.-guest"));

    @Step("Click 'Guest Log In' button")
    public void clickGuestLogInBtn() {
        guestLogInBtn.shouldBe(clickable).click();
    }
}
