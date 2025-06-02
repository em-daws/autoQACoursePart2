package homework.lection23;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class GaragePage {
    @FindBy(css = ".btn-primary")
    public WebElement addCarBtn;
    @FindBy(css = "modal-content")
    public WebElement addCarModal;

    @FindBy(css = "nav.sidebar > a[routerlink=\"instructions\"]")
    public WebElement menuInstractions;

    @FindBy(className = "car-item")
    public WebElement carCard;
    @FindBy(css = "img.car-logo_img")
    public WebElement carLogoImg;
    @FindBy(css = "p.car_name")
    public WebElement carBrand;
    @FindBy(className = "car_update-mileage")
    public WebElement updateDate;
    @FindBy(css = "input.update-mileage-form_input")
    public WebElement mileageInputForm;

    public GaragePage(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @Step("Click 'Add Car' button")
    public void clickAddCarBtn() {
        addCarBtn.click();
    }

    //Menu interactions-------------------------------------------------------------------------

    public void clickMenuInstructions() {
        menuInstractions.click();
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

    public AddCarModal getAddCarModal(WebDriver driver) {
        return new AddCarModal(driver);
    }


    class AddCarModal {
        @FindBy(id = "addCarBrand")
        private WebElement brandDropdownList;
        @FindBy(id = "addCarModel")
        private WebElement modelDropdownList;
        @FindBy(id = "addCarMileage")
        private WebElement mileageField;
        @FindBy(css = ".modal-footer > button.btn-primary")
        private WebElement addBtn;

        public AddCarModal(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

        @Step("Select brand")
        public void selectBrand(String brand) {
            brandDropdownList.click();
            brandDropdownList.sendKeys(brand);
        }

        @Step("Select model")
        public void selectModel(String model) {
            modelDropdownList.click();
            modelDropdownList.sendKeys(model);
        }

        @Step("Select mileage")
        public void selectMileage(String mileage) {
            mileageField.click();
            mileageField.sendKeys(mileage);
        }

        @Step("Click 'Add' button")
        public void clickAddBtn() {
            addBtn.click();
        }
    }
}