package homework.lection20;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task20_1_WaitAndJSExecutor {

    //Задача: Освоєння практики роботи з явними очікуваннями та використання JavascriptExecutor
    // під час написання автоматизованих тестів для веб-додатків.
    //
    //Кроки виконання:
    //
    //Відкриття сторінки:
    //Відкрити браузер і перейти за адресою https://guest:welcome2qauto@qauto.forstudy.space/.
    //Очікуваний результат: Сторінка успішно завантажена.

    WebDriver driver;
    String baseUrl = "https://guest:welcome2qauto@qauto.forstudy.space/";
    QautoForstudyMainPage page;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        page = new QautoForstudyMainPage(driver);
    }

    //Явне очікування для тайтла:
    //Дочекатися тайтла сторінки "Hillel Qauto", використовуючи явне очікування.
    //Очікуваний результат: Тайтл сторінки стає рівним "Hillel Qauto".

    @Test
    public void addingCarBtnTest() {
        String expectedTitle = "Hillel Qauto";
        String expectedURL = "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1L));
        wait.until(driver ->
                ExpectedConditions.titleIs(expectedTitle)
        );

        //JavascriptExecutor для кліку:
        //За допомогою JavascriptExecutor клікнути на кнопку "Guest log in".
        //Очікуваний результат: З'явлення форми входу для гостя.

        page.clickGuestLogInBtn();
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        //Явне очікування для кнопки "Add car":
        //Дочекатися, поки кнопка "Add car" стане клікабельною, використовуючи явне очікування.
        //Очікуваний результат: Кнопка "Add car" стає активною і доступною для кліка.

        FluentWait<WebDriver> fWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(1L))
                .pollingEvery(Duration.ofSeconds(1L));

        fWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-primary")));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
