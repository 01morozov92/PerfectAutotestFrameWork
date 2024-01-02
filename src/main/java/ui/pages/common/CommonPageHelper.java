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

    public static String getElementText(SelenideElement element) {
        return getElementText(element, getElementAliasName(element));
    }

    @Step("Получить текст элемента {elementName}")
    public static String getElementOwnText(SelenideElement element, String elementName) {
        return StringHelper.normalizeText(checkExist(element, elementName).getOwnText());
    }

    public static String getElementOwnText(SelenideElement element) {
        return getElementOwnText(element, getElementAliasName(element));
    }

    @Step("Получить значение внутри элемента {elementName}")
    public static String getElementValue(SelenideElement element, String elementName) {
        return StringHelper.normalizeText(checkElementAppear(element, elementName).getValue());
    }

    public static String getElementValue(SelenideElement element) {
        return getElementValue(element, getElementAliasName(element));
    }

    @Step("Получить значение атрибута")
    public static String getElementAttributeValue(SelenideElement element, String elementName, Attribute elementAttribute) {
        return element.shouldHave(Condition.attribute(elementAttribute.getValue())
                        .because("У элемента %s отсутствует атрибут %s".formatted(elementName, elementAttribute)))
                .getAttribute(elementAttribute.getValue());
    }

    public static String getElementAttributeValue(SelenideElement element, Attribute elementAttribute) {
        return getElementAttributeValue(element, getElementAliasName(element), elementAttribute);
    }

    @Step("Проверить значение атрибута")
    public static void checkElementAttributeValue(SelenideElement element, String elementName, Attribute elementAttribute, String attributeValue) {
        element.shouldHave(Condition.attribute(elementAttribute.getValue(), attributeValue)
                .because("У элемента %s отсутствует атрибут %s=%s".formatted(elementName, elementAttribute, attributeValue)));
    }

    public static void checkElementAttributeValue(SelenideElement element, Attribute cssAttribute, String attributeValue) {
        checkElementAttributeValue(element, getElementAliasName(element), cssAttribute, attributeValue);
    }

    @Step("Проверить значение css атрибута")
    public static void checkElementCssValue(SelenideElement element, String elementName, String cssAttribute, String attributeValue) {
        element.shouldHave(Condition.cssValue(cssAttribute, attributeValue)
                .because("У элемента %s отсутствует css атрибут %s=%s".formatted(elementName, cssAttribute, attributeValue)));
    }

    public static void checkElementCssValue(SelenideElement element, String elementAttribute, String attributeValue) {
        checkElementCssValue(element, getElementAliasName(element), elementAttribute, attributeValue);
    }

    @Step("Проверить содержимое атрибута")
    public static void checkElementAttributeMatching(SelenideElement element, String elementName, Attribute elementAttribute, String attributeRegex) {
        element.shouldHave(Condition.attributeMatching(elementAttribute.getValue(), attributeRegex)
                .because("У элемента %s отсутствует атрибут %s отвечающий регулярному выражению %s".formatted(elementName, elementAttribute, attributeRegex)));
    }

    public static void checkElementAttributeMatching(SelenideElement element, Attribute elementAttribute, String attributeRegex) {
        checkElementAttributeMatching(element, getElementAliasName(element), elementAttribute, attributeRegex);
    }

    @Step("Проверить содержимое атрибута")
    public static void checkElementAttributeNotMatching(SelenideElement element, String elementName, Attribute elementAttribute, String attributeRegex) {
        element.shouldNotHave(Condition.attributeMatching(elementAttribute.getValue(), attributeRegex)
                .because("У элемента %s отсутствует атрибут %s отвечающий регулярному выражению %s".formatted(elementName, elementAttribute, attributeRegex)));
    }

    public static void checkElementAttributeNotMatching(SelenideElement element, Attribute elementAttribute, String attributeRegex) {
        checkElementAttributeNotMatching(element, getElementAliasName(element), elementAttribute, attributeRegex);
    }

    @Step("Заполнить поле {fieldName}")
    public static void fillField(SelenideElement field, String fieldName, String newValue) {
        checkFieldEnabled(field, fieldName);
        clearValueWithBackspace(field);
        field.setValue(newValue);
        field.pressTab();
    }

    public static void fillField(SelenideElement field, String newValue) {
        fillField(field, getElementAliasName(field), newValue);
    }

    @Step("Заполнить поле {fieldName}")
    public static void fillTextField(SelenideElement field, String fieldName, String newValue) {
        checkFieldEnabled(field, fieldName);
        clearTextWithBackspace(field);
        setValueFromKeyboardWithPause(field, newValue, 0);
        field.pressTab();
    }

    public static void fillTextField(SelenideElement field, String newValue) {
        fillTextField(field, getElementAliasName(field), newValue);
    }

    @Step("Заполнить поле {fieldName}")
    public static void fillTextFieldWithoutPressTab(SelenideElement field, String fieldName, String newValue) {
        checkFieldEnabled(field, fieldName);
        clearTextWithBackspace(field);
        setValueFromKeyboardWithPause(field, newValue, 0);
    }

    public static void fillTextFieldWithoutPressTab(SelenideElement field, String newValue) {
        fillTextFieldWithoutPressTab(field, getElementAliasName(field), newValue);
    }

    @Step("Заполнить поле '{fieldName}'")
    public static void fillFieldFromKeyboard(SelenideElement field, String fieldName, String value) {
        checkFieldEnabled(field, fieldName);
        clearValueWithBackspace(field);
        setValueFromKeyboardWithPause(field, value, 0);
        field.pressTab();
    }

    public static void fillFieldFromKeyboard(SelenideElement field, String value) {
        fillFieldFromKeyboard(field, getElementAliasName(field), value);
    }

    @Step("Проверка доступности поля {fieldName}")
    public static SelenideElement checkFieldEnabled(SelenideElement field, String fieldName) {
        checkFieldAppear(field, fieldName);
        return field.should(enabled.because("Поле %s должно быть доступно для редактирования".formatted(fieldName)));
    }

    public static SelenideElement checkFieldEnabled(SelenideElement field) {
        return checkFieldEnabled(field, getElementAliasName(field));
    }

    @Step("Проверка недоступности поля {fieldName}")
    public static SelenideElement checkFieldDisabled(SelenideElement field, String fieldName) {
        checkFieldAppear(field, fieldName);
        return field.should(disabled.because("Поле '%s' должно быть не доступно для редактирования".formatted(fieldName)));
    }

    public static SelenideElement checkFieldDisabled(SelenideElement field) {
        return checkFieldDisabled(field, getElementAliasName(field));
    }

    @Step("Проверка недоступности поля для редактирования {fieldName}")
    public static SelenideElement checkFieldReadOnly(SelenideElement field, String fieldName) {
        checkFieldAppear(field, fieldName);
        return field.shouldBe(readonly.because("Поле '%s' должно быть не доступно для редактирования".formatted(fieldName)));
    }

    public static SelenideElement checkFieldReadOnly(SelenideElement field) {
        return checkFieldReadOnly(field, getElementAliasName(field));
    }

    @Step("Проверка отображения поля {fieldName}")
    public static SelenideElement checkFieldAppear(SelenideElement field, String fieldName) {
        return checkAppear(field, "Поле '%s' должно отображаться".formatted(fieldName));
    }

    public static SelenideElement checkFieldAppear(SelenideElement field) {
        return checkFieldAppear(field, getElementAliasName(field));
    }

    @Step("Клик по полю {fieldName}}")
    public static void clickField(SelenideElement field, String fieldName) {
        checkFieldAppear(field, fieldName).click();
    }

    public static void clickField(SelenideElement field) {
        clickField(field, getElementAliasName(field));
    }

    @Step("Клик по кнопке {buttonName}")
    public static void clickButton(SelenideElement button, String buttonName) {
        checkButtonEnabled(button, buttonName)
                .click();
    }

    public static void clickButton(SelenideElement button) {
        clickButton(button, getElementAliasName(button));
    }

    @Step("Клик по элементу {elementName}")
    public static void clickElement(SelenideElement button, String elementName) {
        checkElementAppear(button, elementName).click();
    }

    public static void clickElement(SelenideElement button) {
        clickElement(button, getElementAliasName(button));
    }

    @Step("Проверить недоступность кнопки {buttonName}")
    public static SelenideElement checkButtonDisabled(SelenideElement button, String buttonName) {
        checkButtonAppear(button, buttonName);
        return button.should(disabled.because("Кнопка %s не должна быть активной".formatted(buttonName)));
    }

    public static SelenideElement checkButtonDisabled(SelenideElement button) {
        return checkButtonDisabled(button, getElementAliasName(button));
    }

    @Step("Проверить доступность кнопки {buttonName}")
    public static SelenideElement checkButtonEnabled(SelenideElement button, String buttonName) {
        checkButtonAppear(button, buttonName);
        return button.should(enabled.because("Кнопка '%s' должна быть активной".formatted(buttonName)));
    }

    public static SelenideElement checkButtonEnabled(SelenideElement button) {
        return checkButtonEnabled(button, getElementAliasName(button));
    }

    @Step("Проверить отображение кнопки {buttonName}")
    public static SelenideElement checkButtonAppear(SelenideElement button, String buttonName) {
        return checkAppear(button, "Кнопка '%s' должна отображаться".formatted(buttonName));
    }

    public static SelenideElement checkButtonAppear(SelenideElement button) {
        return checkButtonAppear(button, getElementAliasName(button));
    }

    @Step("Проверить отсутствие кнопки {buttonName}")
    public static SelenideElement checkButtonDisappear(SelenideElement button, String buttonName) {
        return checkDisappear(button, "Кнопка '%s' не должна отображаться".formatted(buttonName));
    }

    public static SelenideElement checkButtonDisappear(SelenideElement button) {
        return checkButtonDisappear(button, getElementAliasName(button));
    }

    @Step("Проверить существование элемента {elementName}")
    public static SelenideElement checkElementExist(SelenideElement element, String elementName) {
        return checkExist(element, "Элемент '%s' должен существовать".formatted(elementName));
    }

    public static SelenideElement checkElementExist(SelenideElement element) {
        return checkElementExist(element, getElementAliasName(element));
    }

    @Step("Проверить отсутствие элемента {elementName}")
    public static SelenideElement checkElementNotExist(SelenideElement element, String elementName) {
        return checkNotExist(element, "Элемент '%s' не должен существовать".formatted(elementName));
    }

    public static SelenideElement checkElementNotExist(SelenideElement element) {
        return checkElementNotExist(element, getElementAliasName(element));
    }

    @Step("Проверить отображение элемента {elementName}")
    public static SelenideElement checkElementAppear(SelenideElement element, String elementName) {
        return checkAppear(element, "Элемент '%s' должен отображаться".formatted(elementName));
    }

    public static SelenideElement checkElementAppear(SelenideElement element) {
        return checkElementAppear(element, getElementAliasName(element));
    }

    @Step("Проверить отсутствие элемента {elementName}")
    public static SelenideElement checkElementDisappear(SelenideElement element, String elementName) {
        return element.shouldBe(disappear.because("Элемент '%s' не должен отображаться".formatted(elementName)));
    }

    public static SelenideElement checkElementDisappear(SelenideElement element) {
        return checkElementDisappear(element, getElementAliasName(element));
    }

    @Step("Проверить текста элемента {elementName}")
    public static SelenideElement checkElementText(SelenideElement element, String elementName, String text) {
        return checkElementAppear(element, elementName).shouldHave(Condition.text(text)
                .because("Элемент '%s' должен иметь текст '%s'".formatted(elementName, text)));
    }

    public static SelenideElement checkElementText(SelenideElement element, String text) {
        return checkElementText(element, getElementAliasName(element), text);
    }

    @Step("Проверить значение элемента {elementName}")
    public static SelenideElement checkElementValue(SelenideElement element, String elementName, String value) {
        return checkElementAppear(element, elementName).should(Condition.value(value)
                .because("Элемент '%s' должен иметь значение '%s'".formatted(elementName, value)));
    }

    public static SelenideElement checkElementValue(SelenideElement element, String value) {
        return checkElementValue(element, getElementAliasName(element), value);
    }

    @Step("Проверить текста элемента {elementName} по регулярному выражению")
    public static SelenideElement checkElementMatchText(SelenideElement element, String elementName, String regex) {
        return checkElementAppear(element, elementName).should(Condition.matchText(regex)
                .because("Элемент '%s' должен соответствовать регулярному выражению '%s'".formatted(elementName, regex)));
    }

    public static SelenideElement checkElementMatchText(SelenideElement element, String regex) {
        return checkElementMatchText(element, getElementAliasName(element), regex);
    }

    @Step("Проверить ссылку элемента {elementName}")
    public static SelenideElement checkElementHref(SelenideElement element, String elementName, String href) {
        return checkElementAppear(element, elementName).should(Condition.href(href)
                .because("Элемент '%s' должен иметь в себе ссылку '%s'".formatted(elementName, href)));
    }

    public static SelenideElement checkElementHref(SelenideElement element, String regex) {
        return checkElementHref(element, getElementAliasName(element), regex);
    }

    @Step("Клик по ссылке элемента {elementName}")
    public static void clickElementLink(SelenideElement element, String elementName) {
        checkAppear(checkElementAppear(element, elementName).$(byTagName("a")),
                "Ссылка элемента '%s' должна отображаться".formatted(elementName)).click();
    }

    public static void clickElementLink(SelenideElement element) {
        clickElementLink(element, getElementAliasName(element));
    }

    @Step("Клик по ссылке {linkName}")
    public static void clickLink(SelenideElement link, String linkName) {
        checkElementAppear(link)
                .shouldHave(attribute("href")
                        .because("У ссылки \"%s\" отсутствует атрибут href".formatted(linkName)))
                .click();
    }

    @Step("Получить значение чекбокса/свитчера")
    public static boolean getCheckBoxValue(SelenideElement checkBox, String elementName) {
        checkElementExist(checkBox, elementName);
        return checkBox.isSelected();
    }

    @Step("Получить значение чекбокса/свитчера")
    public static boolean getCheckBoxValue(SelenideElement checkBox) {
        return getCheckBoxValue(checkBox, getElementAliasName(checkBox));
    }

    @Step("Навести курсор на элемент")
    public static void hoverElement(SelenideElement element, String elementName) {
        checkElementAppear(element, elementName).hover();
    }

    @Step("Навести курсор на элемент")
    public static void hoverElement(SelenideElement element) {
        hoverElement(element, getElementAliasName(element));
    }

    @Step("Загрузить файл")
    public static void uploadFile(File file, SelenideElement input, String elementName) {
        checkElementExist(input, elementName);
        input.uploadFile(file);
    }

    @Step("Загрузить файл")
    public static void uploadFile(File file, SelenideElement input) {
        uploadFile(file, input, getElementAliasName(input));
    }

    @Step("Проверить статуса тогла")
    public static SelenideElement checkElementSelect(SelenideElement element, boolean value) {
        return checkElementSelect(element, getElementAliasName(element), value);
    }

    @Step("Проверить статуса тогла")
    public static SelenideElement checkElementSelect(SelenideElement element, String elementName, boolean value) {
        if (value)
            return checkElementSelected(element, "Элемент %s должен быть выделенным".formatted(elementName));
        else
            return checkElementNotSelected(element, "Элемент %s не должен быть выделенным".formatted(elementName));
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

    @Step("Проверка наличия элемента")
    private static SelenideElement checkExist(SelenideElement element, String because) {
        return element.shouldBe(exist.because(because));
    }

    @Step("Проверка отсутствия элемента")
    private static SelenideElement checkNotExist(SelenideElement element, String because) {
        return element.shouldNotBe(exist.because(because));
    }

    @Step("Проверка отображения элемента")
    private static SelenideElement checkAppear(SelenideElement element, String because) {
        return element.shouldBe(appear.because(because));
    }

    @Step("Проверка отсутствия элемента")
    private static SelenideElement checkDisappear(SelenideElement element, String because) {
        return element.shouldBe(disappear.because(because));
    }

    @Step("Проверить что тогл выбран")
    private static SelenideElement checkElementSelected(SelenideElement element, String because) {
        return element.should(selected.because(because));
    }

    @Step("Проверить что тогл не выделен")
    private static SelenideElement checkElementNotSelected(SelenideElement element, String because) {
        return element.shouldNot(selected.because(because));
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

    @Step
    public static void clearTextWithBackspace(SelenideElement field) {
        clearFieldWithBackspace(field, field::getText);
    }

    @Step
    public static void setValueFromKeyboardWithPause(SelenideElement field, String value, int pauseTime) {
        value.chars().forEach(c -> {
            sleep(pauseTime);
            field.sendKeys(String.valueOf((char) c));
        });
    }
}
