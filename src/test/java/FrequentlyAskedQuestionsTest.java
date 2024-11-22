import PageObject.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

public class FrequentlyAskedQuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;

    private String questionId;
    private String answerId;

    public FrequentlyAskedQuestionsTest(String questionId, String answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
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
                {"accordion__heading-0", "accordion__panel-0"},
                {"accordion__heading-1", "accordion__panel-1"},
                {"accordion__heading-2", "accordion__panel-2"},

        });
    }

    @Test
    public void testQuestionDropdownFunctionality() {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(mainPage.getQuestionLocator(questionId)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);

        question.click();

        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getAnswerLocator(answerId)));

        assertTrue("Ответ не отображается после нажатия на вопрос: " + questionId, answer.isDisplayed());
    }
}
