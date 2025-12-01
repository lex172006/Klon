import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class LoginTests {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {

        try {
            // Создаём директорию для скриншотов, если её нет
            Files.createDirectories(Paths.get("c:\\screenshot"));

            // Делаем скриншот
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("c:\\screenshot\\screenshot.png");
            FileUtils.copyFile(sourceFile, destFile);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении скриншота: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
    private By loginLocator = By.cssSelector("div.large-field [id*='signin_login']");
    private By passwordLocator = By.cssSelector("div.large-field [id*='signin_password']");
    private By buttonLocator = By.cssSelector("div.form-actions");

    private By klonLocator = By.cssSelector("div.navbar-item>[data-icon*='user']");
    private By multiAccountLocator = By.cssSelector("a[href*='multiAccount']");
    private By ammoAccountLocator = By.cssSelector("input[value*='AMMO']");
    private By enlilAccountLocator = By.cssSelector("input[value*='ENLIL']");
    private By gROShAccountLocator = By.cssSelector("input[value*='gROSh']");
    private By klonNameLocator = By.cssSelector("span.img-option");

    @Test
    public void testLogin() {

        driver.get("https://worldofclone.com");
        String login = "grosh";
        String password = "89147105223";
        driver.findElement(loginLocator).sendKeys(login);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(buttonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(klonLocator));

        driver.findElement(klonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(multiAccountLocator));
        driver.findElement(multiAccountLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ammoAccountLocator));
        driver.findElement(ammoAccountLocator).click();
        WebElement ammoElement = driver.findElement(klonNameLocator);
        assert ammoElement.isDisplayed() : "Name account option is not displayed";

    }

    @Test
    public void testLogin2() {

        driver.get("https://worldofclone.com");
        String login = "grosh";
        String password = "89147105223";
        driver.findElement(loginLocator).sendKeys(login);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(buttonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(klonLocator));

        driver.findElement(klonLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(multiAccountLocator));
        driver.findElement(multiAccountLocator).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(enlilAccountLocator));
        driver.findElement(enlilAccountLocator).click();
        WebElement enlilElement = driver.findElement(klonNameLocator);
        assert enlilElement.isDisplayed() : "Name account option is not displayed";
    }
}

