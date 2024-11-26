import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.page.object.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FrequentlyAskedQuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;
    private String questionId;
    private String answerId;
    private String expectedQuestionText;
    private String expectedAnswerText;


    public FrequentlyAskedQuestionsTest(String questionId, String answerId, String expectedQuestionText, String expectedAnswerText) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.expectedQuestionText = expectedQuestionText;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"0", "0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "7", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},

        });
    }

    @Test
    public void QuestionDropdownFunctionalityTest() {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        String actualQuestionText = mainPage.getQuestionText(wait, questionId);
        assertTrue("Текст вопроса не соответствует ожидаемому для вопроса: " + questionId,
                actualQuestionText.equals(expectedQuestionText));

        mainPage.clickQuestion(wait,questionId);

        assertTrue("Ответ не отображается после нажатия на вопрос: " + questionId,
                mainPage.isAnswerVisible(wait, answerId));


        String actualAnswerText = mainPage.getAnswerText(wait, answerId);
        assertTrue("Текст ответа не соответствует ожидаемому для вопроса: " + questionId,
                actualAnswerText.equals(expectedAnswerText));
    }
}
