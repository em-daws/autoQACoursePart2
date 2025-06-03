package homework.lection26;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;

public class SelenideGaragePage {
    public SelenideElement addCarBtn = Selenide.$(By.cssSelector(".btn-primary"));
    public SelenideElement addCarModal = Selenide.$(By.cssSelector("modal-content"));

    public SelenideElement menuInstractions = Selenide.$(By.cssSelector("nav.sidebar > a[routerlink=\"instructions\"]"));

    public SelenideElement carCard = Selenide.$(By.cssSelector("car-item"));
    public SelenideElement carLogoImg = Selenide.$(By.cssSelector("img.car-logo_img"));
    public SelenideElement carBrand = Selenide.$(By.cssSelector("p.car_name"));
    public SelenideElement updateDate = Selenide.$(By.cssSelector("p.car_update-mileage"));
    public SelenideElement mileageInputForm = Selenide.$(By.cssSelector("input.update-mileage-form_input"));

    @Step("Click 'Add Car' button")
    public void clickAddCarBtn() {
        addCarBtn.shouldBe(clickable).click();
    }

    //Menu interactions-------------------------------------------------------------------------

    public void clickMenuInstructions() {
        menuInstractions.shouldBe(clickable).click();
    }

    //-------------------------------------------------------------------------------------------

    //Car Card Methods---------------------------------------------------------------------------

    public String getCarBrand() {
        return carBrand.getText();
    }

    public String getUpdateDate() {
        String updateDateParagraph = updateDate.getText();
        return updateDateParagraph.substring(updateDateParagraph.length() - 10);
    }

    public String getMileageInputForm() {
        return mileageInputForm.getText();
    }

    public String getCarLogoImgLink() {
        return carLogoImg.getAttribute("src");
    }

    //-------------------------------------------------------------------------------------------

    public SelenideAddCarModal getAddCarModal() {
        return new SelenideAddCarModal();
    }


    class SelenideAddCarModal {
        public SelenideElement brandDropdownList = Selenide.$(By.id("addCarBrand"));
        public SelenideElement modelDropdownList = Selenide.$(By.id("addCarModel"));
        public SelenideElement mileageField = Selenide.$(By.id("addCarMileage"));
        public SelenideElement addBtn = Selenide.$(By.cssSelector(".modal-footer > button.btn-primary"));

        @Step("Select brand")
        public void selectBrand(String brand) {
            brandDropdownList.shouldBe(clickable).click();
            brandDropdownList.sendKeys(brand);
        }

        @Step("Select model")
        public void selectModel(String model) {
            modelDropdownList.shouldBe(clickable).click();
            modelDropdownList.sendKeys(model);
        }

        @Step("Select mileage")
        public void selectMileage(String mileage) {
            mileageField.shouldBe(clickable).click();
            mileageField.sendKeys(mileage);
        }

        @Step("Click 'Add' button")
        public void clickAddBtn() {
            addBtn.shouldBe(clickable).click();
        }
    }
}