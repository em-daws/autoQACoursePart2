package homework.lection23;

import homework.lection19.HelperClass;
import homework.lection20.QautoForstudyMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task23_1 {
    WebDriver driver;
    QautoForstudyMainPage page;

    @BeforeMethod
    public void setUp(){
        //Тест-кейс 2: Перевірка коректного додавання авто до гаража з профілем гість у браузері FireFox.
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
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

    //Тест-кейс 1: Перевірка коректного додавання авто до гаража з профілем гість у браузері Chrome.
    //Клікнути на кнопку "Guest log in".
    //Перевірити, що перехід на сторінку https://qauto.forstudy.space/panel/garage виконаний успішно.
    //На сторінці Garage клікнути на кнопку"Add Car".
    //У випливаючому вікні вибрати наступні дані:
    //Brand: Audi
    //Model: TT
    //Значення в інпуті "millage": 20
    //Натиснути кнопку "Add" для додавання автомобіля.

    @Test
    public void guestUserIsAbleToAddAutoToGarage() throws InterruptedException {
        String carBrand = "Audi";
        String carModel = "TT";
        String mileage = "20";
        String pngName = "audi.png";
        SoftAssert softAssert = new SoftAssert();
        String expectedDate = HelperClass.getCurrentDateAsAStringInSpecificFormat("dd.MM.yyyy");

        page.clickGuestLogInBtn();
        GaragePage garagePage = new GaragePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");

        garagePage.clickAddCarBtn();

        GaragePage.AddCarModal addCarModal = garagePage.getAddCarModal(driver);
        addCarModal.selectBrand(carBrand);
        addCarModal.selectModel(carModel);
        addCarModal.selectMileage(mileage);
        addCarModal.clickAddBtn();

        ////Перевірки з використанням soft assert.
        softAssert.assertEquals(garagePage.getCarBrand(), carBrand + " " + carModel);    //на сторінці Garage відображається доданий автомобіль "Audi TT"
        softAssert.assertEquals(garagePage.getUpdateDate(), expectedDate);                        //в абзаці "car_update-mileage" відображається поточна дата
//        softAssert.assertEquals(garagePage.getMileageInputForm(), mileage);                     //в інпуті "miles" значення дорівнює 20
        softAssert.assertTrue(garagePage.carLogoImg.isDisplayed());                               //відображення зображення (img) в блоку "car_logo car-logo"
        softAssert.assertTrue(garagePage.getCarLogoImgLink().endsWith(pngName));                  //посилання на зображення (img) закінчується на "audi.png"

        ////Всі кроки тесту виконуються успішно, і перевірки з використанням soft assert не виявляють помилок.
        softAssert.assertAll();
    }
}
