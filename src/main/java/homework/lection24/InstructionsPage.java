package homework.lection24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionsPage {
    @FindBy(css = "div.brand-select-dropdown")
    private WebElement carBrandsDropdown;

    @FindBy(className = "instruction-link")
    public List<WebElement> downloadItems;

    public InstructionsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickDownloadLinkByName(String name) {
        for (WebElement downloadItem : downloadItems) {
            System.out.println(downloadItem.getText());
            if (downloadItem.findElement(By.cssSelector("p")).getText().equals(name)) {
                downloadItem.findElement(By.cssSelector("a.instruction-link_download")).click();
                break;
            }
        }
    }

    public List<String> getAllCarBrands() {
        carBrandsDropdown.click();
        List<WebElement> carBrandListItems = carBrandsDropdown.findElements(By.cssSelector("ul > li"));
        return carBrandListItems
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
