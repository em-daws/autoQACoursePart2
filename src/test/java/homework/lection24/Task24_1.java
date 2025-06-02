package homework.lection24;

import homework.BaseTest;
import homework.lection19.HelperClass;
import homework.lection20.QautoForstudyMainPage;
import homework.lection23.GaragePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.List;

public class Task24_1 extends BaseTest {
    QautoForstudyMainPage mainPage;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        mainPage = new QautoForstudyMainPage(driver);
    }

    @Override
    @AfterMethod
    public void tearDown() {
        super.tearDown();
        mainPage = null;
    }

    //Тест-кейс 1: Завантаження файлу зі сторінку з інструкціями.
    //
    //Клікнути на кнопку"Guest log in".
    //Перевірити, що перехід на сторінку https://qauto.forstudy.space/panel/garage виконаний успішно.
    //На сторінці Garage клікнути у лівому меню на Instructions.
    //Завантажити Front windshield wipers on Audi TT.pdf у корінь свого проекту.
    //Очікуваний результат:
    //
    //У корені проекту з'явився файл під назвою Front windshield wipers on Audi TT.pdf.

    @Test
    public void fileTest() {
        mainPage.clickGuestLogInBtn();
        GaragePage garagePage = new GaragePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");

        garagePage.clickMenuInstructions();

        InstructionsPage instructionsPage = new InstructionsPage(driver);
        instructionsPage.clickDownloadLinkByName("Front windshield wipers on Audi TT");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> DOWNLOADS.listFiles().length > 0);

        File downloadedFile = new File(DOWNLOADS.getAbsolutePath() + "\\Front windshield wipers on Audi TT.pdf");
        Assert.assertTrue(downloadedFile.exists());
    }

    //Тест-кейс 2: Запис списку доступних автомобілів у txt файл.
    //Клікнути на кнопку"Guest log in".
    //Перевірити, що перехід на сторінку https://qauto.forstudy.space/panel/garage виконаний успішно.
    //На сторінці Garage клікнути у лівому меню на Instructions.
    //Клікнути на кнопку вибору автомобіля.
    //Зберегти в List всі доступні автомобілі з вікна, що відкрилося.
    //Записати всі дані з List до нового txt файлу та зберегти його в корені проекту

    //Очікуваний результат:
    //У корені проекту з'явився txt файл з п'ятьма автомобілями(Audi, Bmw, Ford, Porsche, Fiat).

    @Test
    public void writingToFileTest() {
        mainPage.clickGuestLogInBtn();
        GaragePage garagePage = new GaragePage(driver);
        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");

        garagePage.clickMenuInstructions();
        InstructionsPage instructionsPage = new InstructionsPage(driver);
        List<String> allCarBrandsList = instructionsPage.getAllCarBrands();

        Path filePath = Paths.get(DOWNLOADS + "\\carBrands.txt");
        try {
            Files.write(
                    filePath,
                    allCarBrandsList,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
            System.out.println("File is created and filled");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(new File(DOWNLOADS + "\\carBrands.txt").exists());

        String expectedCarBrandList = HelperClass.expectedFormat(allCarBrandsList);
        try {
            String content = Files.readString(filePath);
            Assert.assertEquals(content, expectedCarBrandList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
