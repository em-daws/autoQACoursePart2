package homework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.HashMap;

public class BaseTest {
    protected static File DOWNLOADS = new File("C:\\Users\\user\\IdeaProjects\\autoQACoursePart2\\src\\test\\resources\\downloads");

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> prefs = new HashMap<>();
        if(!DOWNLOADS.exists()) {
            DOWNLOADS.mkdir();
        }
        prefs.put("download.default_directory", DOWNLOADS.getAbsolutePath());
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver = null;
        File[] files = DOWNLOADS.listFiles();
        for(File file : files) {
            file.delete();
        }
    }
}
