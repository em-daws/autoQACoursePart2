package homework.lection21;

import homework.lection19.HelperClass;
import homework.lection20.QautoForstudyMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task21_1 {
    WebDriver driver;
    String baseUrl = "https://guest:welcome2qauto@qauto.forstudy.space/";
    QautoForstudyMainPage page;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        page = new QautoForstudyMainPage(driver);
    }

    //Тест-кейс 1: Перевірка відображення логотипу
    //
    //Перевірити, чи логотип відображається на сторінці з використанням TestNG Assert.
    //Якщо логотип не відображається, вивести помилку у консоль за допомогою TestNG :"Logo does not displayed".
    //Виконати тест за допомогою консолі.
    //
    // Очікуваний результат:
    //Успішно пройдений тест, якщо логотип відображається.
    //Повідомлення "Logo does not displayed", якщо логотип не відображається.

    //Тест-кейс 2: Перевірка кольору фону кнопки "Sign up"
    //
    //Знайти кнопку з надписом "Sign up" та перевірити значення атрибуту background-color.
    //Переконатися, що значення атрибуту background-color рівне #0275d8 з використанням TestNG Assert.
    //Якщо значення атрибуту background-color не рівне #0275d8, вивести помилку у консоль за допомогою TestNG: "Background color of Sign up button is incorrect".
    //Виконати тест за допомогою консолі.
    //
    // Очікуваний результат:
    //Успішно пройдений тест, якщо колір фону кнопки "Sign up" рівний #0275d8.
    //Повідомлення "Background color of Sign up button is incorrect", якщо колір фону кнопки "Sign up" не рівний #0275d8.

    @Test
    public void logoDisplayed() {
        Assert.assertTrue(page.logo.isDisplayed(), "Logo does not displayed");
    }

    @Test
    public void signUpBtnColorTest() {
        String colorRGBA = page.signUpBtn.getCssValue("background-color");
        String colorHex = HelperClass.rgbaToHex(colorRGBA);
        Assert.assertEquals(colorHex, "#0275d8", "Background color of Sign up button is incorrect");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
