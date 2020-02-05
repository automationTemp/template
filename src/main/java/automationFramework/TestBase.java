package automationFramework;

import config.Config;
import config.ConfigReader;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utility.Listener;

import java.io.IOException;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

@Listeners(Listener.class)
public class TestBase {
    protected static WebDriver driver;
    protected static Wait<WebDriver> wait;
    protected static final ApplicationManager app = new ApplicationManager();

    protected static JavascriptExecutor jsExecutor;

    private static int delay = 40;//
    private static int sleepTime = 250;//

    public final static Logger logger = Logger.getLogger(TestBase.class);

    public static SoftAssert softAssert;

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    protected static void setUpChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver_old.exe"); //локальное расположение веб-драйвера Chrome
        ChromeOptions option = new ChromeOptions();

        driver = new ChromeDriver(option);
        generalDriverSetting();
        logger.info("Хром драйвер настроен");
    }

    protected void setUpIeDriver() {
        System.setProperty("webdriver.ie.driver", "C://webdrivers/IEDriverServer.exe"); //локальное расположение веб-драйвера IE
        InternetExplorerOptions options = new InternetExplorerOptions();

        driver = new InternetExplorerDriver(options);
        generalDriverSetting();
        logger.info("IE драйвер настроен");
    }

    private static void generalDriverSetting() {
        jsExecutor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, delay, sleepTime);
        driver.manage().deleteAllCookies();
        disableAlerts();
        driver.manage().window().maximize();
    }

    protected static void disableAlerts() {
        jsExecutor.executeScript("alert = function(){};");
    }

    public void acceptAlert() {
        wait.until(alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void shutUp(int numberOfSeconds) {
        try {
            Thread.sleep(numberOfSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    protected void baseTestBeforeSuite() throws IOException {
        logger.debug("Начало тестирования");
        ConfigReader.configOrganizer(new String[]{""});//задаем конфиги
    }

    @BeforeMethod
    protected void baseTestBeforeMethod() {
        logger.debug("Инициализация базового класса");
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void setUp() {
        String browser = Config.getProperty("browser.name");
        switch (browser) {
            case "CHROME":
                setUpChromeDriver();
                break;
            case "IE":
                setUpIeDriver();
                break;
        }

        app.init();
    }

    @AfterMethod
    public void shutdown() {
        if (driver != null)
            driver.quit();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void reloadPage() {
        driver.navigate().refresh();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
