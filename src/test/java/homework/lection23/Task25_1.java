package homework.lection23;

import homework.lection19.HelperClass;
import homework.lection20.QautoForstudyMainPage;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task25_1 {
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

    @Description("Guest user is able to add auto to garage")
    @Owner("Tetiana")
    @Link(name = "requirements", url = "https://www.somerequire.com", type = "Trello")
    @Severity(SeverityLevel.NORMAL)
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
        softAssert.assertEquals(garagePage.getCarBrand(), carBrand + " " + carModel);
        softAssert.assertEquals(garagePage.getUpdateDate(), expectedDate);
//        softAssert.assertEquals(garagePage.getMileageInputForm(), mileage);
        softAssert.assertTrue(garagePage.carLogoImg.isDisplayed());
        softAssert.assertTrue(garagePage.getCarLogoImgLink().endsWith(pngName));

        softAssert.assertAll();
    }
}
