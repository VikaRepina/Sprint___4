package ru.page.object;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;

import static org.junit.Assert.assertTrue;


public class OrderPage extends BasePage {
    // Поле ввода Имени
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    // Поле ввода Фамилии
    private final By lastnameInput = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Фамилия']");
    // Поле ввода Адреса
    private final By addressInput = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Адрес: куда привезти заказ']");
    // Поле ввода Станции метро
    private final By metroStationInput = By.xpath("//input[@class='select-search__input']");
    // Поле ввода Телефона
    private final By phoneInput = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка согласия на использование куки
    private final By cookieButton = By.xpath("//button[@id='rcc-confirm-button']");
    // Кнопка перехода в следующее окно
    private final By nextButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()= 'Далее']");
    // Поле выбора даты доставки
    private final By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Поле выбора срока аренды
    private final By rentalPeriodOption = By.xpath("//div[@class='Dropdown-placeholder']");
    // Cтрока аренды
    private final By rentalPeriodSelect = By.xpath("//div[@class='Dropdown-option' and text()= 'двое суток']");
    // Кнопка оформления заказа
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Кнопка подтверждения оформления заказа
    private final By orderConfirmationButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()= 'Да']");
    // Сообщение об успешном создании заказа
    private final By successMessage = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and text()= 'Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void fillFirstOrderForm (String name, String lastname, String address, String metroStation, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(lastnameInput).sendKeys(lastname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroStationInput).sendKeys(metroStation, Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(phoneInput).sendKeys(phone);


    }

    public void nextButton (WebDriverWait wait) {
        WebElement cookieButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
        cookieButtonLocator.click();

        WebElement nextButtonLocator = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButtonLocator.click();

    }


    public void fillSecondOrderForm (String deliveryDate) {
        driver.findElement(deliveryDateInput).sendKeys(deliveryDate, Keys.ENTER);
        driver.findElement(rentalPeriodOption).click();
        driver.findElement(rentalPeriodSelect).click();
        driver.findElement(orderButton).click();

    }


    public void orderConfirmationButton () {
        driver.findElement(orderConfirmationButton).click();
    }

    public void successMessage () {
        WebElement successMessageElement = driver.findElement(successMessage);
        assertTrue("Всплывающее окно не отображается!", successMessageElement.isDisplayed());
    }

}
