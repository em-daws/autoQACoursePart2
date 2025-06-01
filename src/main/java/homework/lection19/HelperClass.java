package homework.lection19;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HelperClass {

    private static final Logger log = LoggerFactory.getLogger(HelperClass.class);

    public static String rgbaToHex(String rgba) {
        rgba = rgba.replace("rgba(", "").replace(")", "");
        String[] parts = rgba.split(",");
        int r = Integer.parseInt(parts[0].trim());
        int g = Integer.parseInt(parts[1].trim());
        int b = Integer.parseInt(parts[2].trim());

        return String.format("#%02x%02x%02x", r, g, b);
    }

    public static boolean navigateToTab(WebDriver driver, int number) {
        boolean navigatedSuccessfully = false;

        Object[] windowHandles = driver.getWindowHandles().toArray();
        if (windowHandles.length > 1) {
            try {
                driver.switchTo().window((String) windowHandles[number]);
                driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1L));
                log.info("Navigated to " + driver.getCurrentUrl());
                navigatedSuccessfully = true;
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return navigatedSuccessfully;
    }
}
