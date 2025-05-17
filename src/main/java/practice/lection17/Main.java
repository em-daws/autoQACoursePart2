package practice.lection17;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, World!");
        firstTest();
    }

    public static void firstTest() throws InterruptedException {
        String path = "https://translate.google.com/";

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(path);

        WebElement rejectButton = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/div/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/div/div/button/span[4]"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", rejectButton);

        assert rejectButton.isDisplayed();
        assert rejectButton.isEnabled();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", rejectButton);

        assert driver.getCurrentUrl().equals(path);

        WebElement textField = driver.findElement(By.cssSelector("textarea[aria-label]"));
        textField.sendKeys("test");
        assert textField.getText().equals("test");

        //choosing language
        WebElement arrow = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[1]/c-wiz/div[1]/c-wiz/div[5]/button/div[3]"));
        arrow.click();

        WebElement ukrainian = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[1]/c-wiz/div[2]/c-wiz/div[2]/div/div[3]/div/div[2]/span[233]/div[1]"));
        ukrainian.click();

        Thread.sleep(5000);
        WebElement textFieldTranslated = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[2]/div[2]/c-wiz/div[1]/div[6]/div/div[1]"));
        System.out.println(textFieldTranslated.getText());
//        rejectButton.click();
//        Thread.sleep(500000);

        driver.quit();
    }
}