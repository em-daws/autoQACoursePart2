package homework.lection22;

import homework.lection20.QautoForstudyMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Task22_1 {
    WebDriver driver;
    QautoForstudyMainPage page;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        page = new QautoForstudyMainPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
        page = null;
    }

    //Тест-кейс 1: Перевірка тайтлу у фреймі.
    //Перейти до фрейму, в якому знаходиться відео.
    //Перевірити, що тайтл цього фрейму дорівнює “Hillel IT School | Учись ради мечты! - YouTube” з використанням TestNG Assert.

    //Очікуваний результат:
    //Успішно пройдений тест, якщо тайтл відповідає очікуваному результату.
    //Повідомлення"Title doesn’t equals to the expected result", якщо тайтл не відповідає очікуваному результату чи не відображається.

    @Test
    public void frameTitleTest(){
        String expectedTitle = "Як потрапити у майбутнє? Трансформація навчання.";
        String actualTitle = page.switchToVideoIFrame().getTitle();

        Assert.assertEquals(actualTitle, expectedTitle,
                "Title doesn’t equals to the expected result");
    }

    //Тест-кейс 2: Перевірка блока соціальних мереж в футері сайту.
    //Перевірити, що блок у футері містить 5 іконок соціальних мереж.
    //По черзі клікнути на іконки соціальних мереж.
    //Після кожного кліку перевірити, що відкривається нова вкладка з коректною офіційною сторінкою соціальної мережі та повернутися до вкладки с головною сторінкою.

    //Очікуваний результат:
    //Успішно пройдений тест, якщо відображається 5 іконок соціальних мереж.
    //Успішно пройдений тест, якщо всі вкладки після кліку на іконку відкрилися з коректним сторінкою.
    //Повідомлення"Social network block doesn’t contain 5 items ", якщо блок не містить 5 іконок соціальних мереж.
    //Повідомлення "New tab did not open after clicking ", якщо після кліку не відкрилась нова вкладка.
    //Повідомлення "Incorrect url of Social network", якщо після кліку відкрилася вкладка з некоректним адресом.

    @Test
    public void socialMediaLinksTest(){
        List<WebElement> socialMediaLinks = page.getSocialMediaLinks();
        String windowHandle = driver.getWindowHandle();
        SoftAssert softAssert = new SoftAssert();

        Assert.assertEquals(socialMediaLinks.size(), 5, "Social network block doesn’t contain 5 items");

        for (WebElement socialMediaLink : socialMediaLinks) {
            String expectedLink = socialMediaLink.getDomAttribute("href");

            softAssert.assertTrue(page.navigateToSocialMedia(socialMediaLink), "New tab did not open after clicking");
            softAssert.assertTrue(page.checkCorrectPageIsOpened(expectedLink),
                    "Incorrect url of Social network\n"
                            + "Expected: " + expectedLink
                            + "\nActual: " + driver.getCurrentUrl());
            driver.close();
            driver.switchTo().window(windowHandle);
        }
        softAssert.assertAll();
    }
}
