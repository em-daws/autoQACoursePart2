package homework.lection26;

import com.codeborne.selenide.*;
import homework.lection19.HelperClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task26_1 {
    SelenideMainPage mainPage;

    @BeforeMethod
    public void setUp(){
        Configuration.browser = "firefox";
        Configuration.pageLoadStrategy = "eager";
        Selenide.open("https://guest:welcome2qauto@qauto.forstudy.space/");
        mainPage = new SelenideMainPage();
    }

    @AfterMethod
    public void tearDown(){
        Selenide.clearBrowserCookies();
        Selenide.closeWebDriver();
        mainPage = null;
    }

    @Test
    public void guestUserIsAbleToAddAutoToGarage(){
        String carBrand = "Audi";
        String carModel = "TT";
        String mileage = "20";
        String pngName = "audi.png";
        SoftAssert softAssert = new SoftAssert();
        String expectedDate = HelperClass.getCurrentDateAsAStringInSpecificFormat("dd.MM.yyyy");

        mainPage.clickGuestLogInBtn();
        SelenideGaragePage garagePage = new SelenideGaragePage();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");

        garagePage.clickAddCarBtn();

        SelenideGaragePage.SelenideAddCarModal addCarModal = garagePage.getAddCarModal();
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
