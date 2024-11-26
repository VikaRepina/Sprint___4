package ru.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private final By dropdownQuestions = By.xpath("//div[@id='accordion__heading-%s']");
    // Ответ в выпадающий список в разделе «Вопросы о важном»
    private final By dropdownAnswer = By.xpath("//div[@id='accordion__panel-%s']");



    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickQuestion(WebDriverWait wait, String questionId) {
        By questionLocator = By.xpath(String.format("//div[@id='accordion__heading-%s']", questionId));
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        question.click();
    }

    public boolean isAnswerVisible(WebDriverWait wait, String answerId) {
        By answerLocator = By.xpath(String.format("//div[@id='accordion__panel-%s']", answerId));
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.isDisplayed();
    }

    public String getQuestionText(WebDriverWait wait, String QuestionId) {
        By answerLocator = By.xpath(String.format("//div[@id='accordion__heading-%s']", QuestionId));
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText();
    }

    public String getAnswerText(WebDriverWait wait, String answerId) {
        By answerLocator = By.xpath(String.format("//div[@id='accordion__panel-%s']", answerId));
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText();
    }

}

