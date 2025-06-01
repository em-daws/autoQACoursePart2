package homework.lection27;

import homework.lection20.QautoForstudyMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task27_1 {
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
        driver.manage().deleteAllCookies();
        driver.quit();
        page = null;
    }

    //Тест-кейс: Перевірка відображення повідомлення про помилку при введенні незв'язаних даних аутентифікаціі.
    //
    //Натиснути на кнопку Sign In.
    //Ввести дані до інпутів Email та password :
    //email: test@hillel.ua, password: 1111
    //email: test@hillel.ua, password: 1234
    //Натиснути на кнопку Login.
    //Перевіряти відображення повідомлення про помилку з текстом “Wrong email or password”.

    //Очікуваний результат:
    //Два тест-кейса з різними даними виконуються успішно.

    @Test(dataProvider = "wrongCredentials")
    public void authWithWrongCredentialsTest(String email, String password) {
        QautoForstudyMainPage.SignInModalWindow signInModalWindow = page.clickSignInBtn();
        signInModalWindow.login(email, password);
        Assert.assertTrue(signInModalWindow.isAlertDisplayed());
        Assert.assertEquals(signInModalWindow.getAlertText(), "Wrong email or password");
    }

    @DataProvider (name = "wrongCredentials")
    public Object[][] getWrongCreds() {
        return new Object [][] {
                {"test@hillel.ua", "1111"},
                {"test@hillel.ua", "1234"}
        };
    }
}