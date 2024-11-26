import ru.page.object.MainPage;
import ru.page.object.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collection;

import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class OrderPlacementButtonBottomTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    private String name;
    private String lastname;
    private String address;
    private String metroStation;
    private String phone;
    private String deliveryDate;
    private WebDriverWait wait;
    private String browser;

    public OrderPlacementButtonBottomTest(String browser, String name, String lastname, String address, String metroStation, String phone, String deliveryDate) {
        this.browser = browser;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
    }

    @Before
    public void setup() {

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        wait = new WebDriverWait(driver, 10);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @After
    public void tearDownTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"firefox", "Ольга", "Иванова", "Пушкина 2", "Сокольников", "89797234557", "28.11.2024"},
                {"firefox", "Наталья", "Смирнова", "Московская 4", "Лубянка", "89796486947", "29.11.2024"},
                {"chrome", "Ирина", "Воробьева", "Московская 5", "Лубянка", "89796486947", "29.11.2024"},
        });
    }

    @Test
    public void orderFlowWithFirstSetOfDataTest() {

        WebElement orderButtonBottom = driver.findElement(By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderButtonBottom);
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));

        mainPage.clickOrderButtonBottom();
        orderPage.fillFirstOrderForm(name, lastname, address, metroStation, phone);
        orderPage.nextButton(wait);
        orderPage.fillSecondOrderForm(deliveryDate);
        orderPage.orderConfirmationButton();

        orderPage.successMessage();

    }
}
