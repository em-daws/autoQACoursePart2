package homework.lection17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Task17_1 {

    public static void main(String[] args) throws InterruptedException {
        WebDriver browser = new ChromeDriver();

        Runtime.getRuntime().addShutdownHook(new Thread(browser::quit)); //instead of "try" block

        browser.manage().window().maximize();
        browser.get("https://guest:welcome2qauto@qauto.forstudy.space/");

        WebElement guestLogInBtn = browser.findElement(By.cssSelector("div.header_right > button.-guest"));
        guestLogInBtn.click();
        Thread.sleep(5000);

        List<WebElement> sidebarMenuItems = browser.findElements(By.cssSelector(".sidebar > a"));
        for (WebElement sidebarMenuItem : sidebarMenuItems) {
            System.out.println(sidebarMenuItem.getText());
        }

        browser.quit();
    }

    //Напишіть тестовий сценарій на мові програмування Java з використанням Selenium WebDriver, який виконує наступні дії:
    //
    //Відкрийте браузер та перейдіть на сайт https://guest:welcome2qauto@qauto.forstudy.space/
    //Клікніть на кнопку"Guest log in".
    //Виведіть текст кожного елемента із border menu в консоль.
    //Вимоги:
    //
    //Використайте Java як мову програмування.
    //Використайте Selenium WebDriver для автоматизації взаємодії з браузером.
    //Реалізуйте функцію main, яка буде містити весь тестовий сценарій.
    //Додаткові вказівки:
    //
    //Переконайтеся, що ваш тест проходить без помилок і виводить текст елементів в консоль.
    //Використовуйте ідентифікатори або інші зручні локатори для знаходження елементів.
    //Використовуйте блок try на випадок падіння тесту.
}
