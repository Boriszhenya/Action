package Action;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        // Получаем последнюю версию драйвера браузера Chrome
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        Map<String, Object> profile = new HashMap<String, Object>();
        Map<String, Object> contentSettings = new HashMap<String, Object>();

        //profile.put("managed_default_content_settings",contentSettings);
        prefs.put("profile",profile);
        options.setExperimentalOption("prefs",prefs);
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        // Создаём новый объект класса ChromeDriver
        driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
    }
}
