package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    // Загаловок страницы
    private final By title = By.xpath("//div[@class='Home_Header__iJKdX']");
    // Кнопка "Заказать" вверху страницы
    private final By orderButtonTop = By.xpath("//button[@class='Button_Button__ra12g']");
    // Кнопка "Заказать" внизу страницы
    private final By orderButtonBottom = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Выпадающий список в разделе «Вопросы о важном»
    private final By dropdownFrequentlyAskedQuestions = By.xpath("//div[@class='accordion']");
    // Вопрос в выпадающий список в разделе «Вопросы о важном»
    private final By dropdownQuestions = By.xpath("//div[@id='accordion__heading-0']");
    // Ответ в выпадающий список в разделе «Вопросы о важном»
    private final By dropdownAnswer = By.xpath("//div[@id='accordion__panel-0']");



    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public By getQuestionLocator(String questionId) {
        return By.xpath("//div[@id='" + questionId + "']");
    }

    public By getAnswerLocator(String answerId) {
        return By.xpath("//div[@id='" + answerId + "']");
    }

}
