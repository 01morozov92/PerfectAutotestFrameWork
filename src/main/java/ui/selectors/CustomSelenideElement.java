package ui.selectors;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

public interface CustomSelenideElement extends SelenideElement {
    void clickElement();
    CustomSelenideElement setAlias(String string);
    CustomSelenideElement checkElementAppear();
    CustomSelenideElement fillField(String newValue);
    CustomSelenideElement getElementText();
    CustomSelenideElement clickField();
    CustomSelenideElement clickButton();
    CustomSelenideElement checkElementDisappear();
    CustomSelenideElement checkElementText(String text);
    CustomSelenideElement checkElementMatchText(String regex);
}
