package homework.lection19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Welcome2QautoTest {

    WebDriver driver;
    String baseUrl = "https://guest:welcome2qauto@qauto.forstudy.space/";

    @BeforeMethod
    public void setUp() {
        //Відкрити веб-браузер та перейти за наступним посиланням: https://guest:welcome2qauto@qauto.forstudy.space/
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    //Тест-кейс 1: Перевірка відображення логотипу
    //
    //Перевірити, чи логотип відображається на сторінці.
    //Якщо логотип відображається, вивести у консоль повідомлення:"Logo displayed".
    //Якщо логотип не відображається, вивести у консоль повідомлення: "Logo does not displayed".
    //Виконати тест за допомогою консолі.

    @Test
    public void logoDisplayed() {
        WebElement logo = driver.findElement(By.cssSelector(".header_logo > svg"));
        Assert.assertTrue(logo.isDisplayed(), "Logo does not displayed");
        System.out.println("Logo displayed");
    }


    //Тест-кейс 2: Перевірка кольору фону кнопки "Sign up"
    //
    //Знайти кнопку з надписом "Sign up" та перевірити значення атрибуту background-color.
    //Переконатися, що значення атрибуту background-color рівне #0275d8.
    //Якщо значення атрибуту background-color рівне #0275d8, вивести у консоль повідомлення: "Background color of Sign up button is correct".
    //Якщо значення атрибуту background-color не рівне #0275d8, вивести у консоль повідомлення: "Background color of Sign up button is incorrect".
    //Виконати тест за допомогою консолі.

    @Test
    public void signUpBtnColorTest() {
        WebElement signUpBtn = driver.findElement(By.cssSelector("button.btn-primary"));
        String colorRGBA = signUpBtn.getCssValue("background-color");
        String colorHex = HelperClass.rgbaToHex(colorRGBA);
        Assert.assertEquals(colorHex, "#0275d8", "Background color of Sign up button is incorrect");
        System.out.println("Background color of Sign up button is correct");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
