package ui.pages.common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.StringHelper;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.Keys;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.Callable;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.sleep;

public abstract class CommonPageHelper {

    @Step("Получить текст внутри элемента {elementName}")
    public static String getElementText(SelenideElement element, String elementName) {
        return StringHelper.normalizeText(checkElementAppear(element, elementName).getText());
    }

    @Step("Заполнить поле {fieldName}")
    public static void fillField(SelenideElement field, String fieldName, String newValue) {
        checkFieldEnabled(field, fieldName);
        clearValueWithBackspace(field);
        field.setValue(newValue);
        field.pressTab();
    }

    @Step("Проверка доступности поля {fieldName}")
    public static SelenideElement checkFieldEnabled(SelenideElement field, String fieldName) {
        checkFieldAppear(field, fieldName);
        return field.should(enabled.because("Поле %s должно быть доступно для редактирования".formatted(fieldName)));
    }

    @Step("Проверка отображения поля {fieldName}")
    public static SelenideElement checkFieldAppear(SelenideElement field, String fieldName) {
        return checkAppear(field, "Поле '%s' должно отображаться".formatted(fieldName));
    }

    @Step("Клик по полю {fieldName}}")
    public static void clickField(SelenideElement field, String fieldName) {
        checkFieldAppear(field, fieldName).click();
    }

    @Step("Клик по кнопке {buttonName}")
    public static void clickButton(SelenideElement button, String buttonName) {
        checkButtonEnabled(button, buttonName)
                .click();
    }

    @Step("Клик по элементу {elementName}")
    public static void clickElement(SelenideElement button, String elementName) {
        checkElementAppear(button, elementName).click();
    }
    @Step("Проверить недоступность кнопки {buttonName}")
    public static SelenideElement checkButtonDisabled(SelenideElement button, String buttonName) {
        checkButtonAppear(button, buttonName);
        return button.should(disabled.because("Кнопка %s не должна быть активной".formatted(buttonName)));
    }

    @Step("Проверить доступность кнопки {buttonName}")
    public static SelenideElement checkButtonEnabled(SelenideElement button, String buttonName) {
        checkButtonAppear(button, buttonName);
        return button.should(enabled.because("Кнопка '%s' должна быть активной".formatted(buttonName)));
    }

    @Step("Проверить отображение кнопки {buttonName}")
    public static SelenideElement checkButtonAppear(SelenideElement button, String buttonName) {
        return checkAppear(button, "Кнопка '%s' должна отображаться".formatted(buttonName));
    }

    @Step("Проверить отображение элемента {elementName}")
    public static SelenideElement checkElementAppear(SelenideElement element, String elementName) {
        return checkAppear(element, "Элемент '%s' должен отображаться".formatted(elementName));
    }

    @Step("Проверить отсутствие элемента {elementName}")
    public static SelenideElement checkElementDisappear(SelenideElement element, String elementName) {
        return element.shouldBe(disappear.because("Элемент '%s' не должен отображаться".formatted(elementName)));
    }

    @Step("Проверить текста элемента {elementName}")
    public static SelenideElement checkElementText(SelenideElement element, String elementName, String text) {
        return checkElementAppear(element, elementName).shouldHave(Condition.text(text)
                .because("Элемент '%s' должен иметь текст '%s'".formatted(elementName, text)));
    }

    @Step("Проверить текста элемента {elementName} по регулярному выражению")
    public static SelenideElement checkElementMatchText(SelenideElement element, String elementName, String regex) {
        return checkElementAppear(element, elementName).should(Condition.matchText(regex)
                .because("Элемент '%s' должен соответствовать регулярному выражению '%s'".formatted(elementName, regex)));
    }

    public static String getElementAliasName(SelenideElement element) {
        return Optional.ofNullable(element.getAlias())
                .orElseThrow(() -> new RuntimeException("""
                        Нельзя использовать метод без указания имени элемента
                        Варианты решения:
                        a) Проставить аннотацию @As("Имя элемента") у SelenideElement
                        б) Добавить alias для элемента -> element.as("Имя элемента")
                        в) Использовать метод в который передаётся elementName для SelenideElement
                        """));
    }

    @Step("Проверка отображения элемента")
    private static SelenideElement checkAppear(SelenideElement element, String because) {
        return element.shouldBe(appear.because(because));
    }

    @Step("Проверка отсутствия элемента")
    private static SelenideElement checkDisappear(SelenideElement element, String because) {
        return element.shouldBe(disappear.because(because));
    }

    @SneakyThrows
    @Step
    private static void clearFieldWithBackspace(SelenideElement field, Callable<String> callable) {
        if (callable.call() == null || callable.call().isEmpty()) return;
        int charsCount = callable.call().length();
        while (callable.call() != null && charsCount > 0) {
            field.sendKeys(Keys.SHIFT, Keys.DOWN);
            field.sendKeys(Keys.BACK_SPACE);

            field.sendKeys(Keys.SHIFT, Keys.UP);
            field.sendKeys(Keys.BACK_SPACE);
            if (callable.call() == null || charsCount <= callable.call().length()) break;
            sleep(50);
            charsCount = callable.call().length();
        }
        if (callable.call() != null && !callable.call().isEmpty()) {
            field.sendKeys(Keys.END);
            for (int i = 0; i < charsCount; i++)
                field.sendKeys(Keys.BACK_SPACE);
        }
    }

    @Step
    public static void clearValueWithBackspace(SelenideElement field) {
        clearFieldWithBackspace(field, field::getValue);
    }
}
