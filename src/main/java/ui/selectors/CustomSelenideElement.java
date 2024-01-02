package ui.selectors;

import com.codeborne.selenide.SelenideElement;
import ui.pages.common.Attribute;

import java.io.File;

public interface CustomSelenideElement extends SelenideElement {
    void clickElement();
    CustomSelenideElement setAlias(String string);
    CustomSelenideElement checkElementAppear();
    CustomSelenideElement fillField(String newValue);
    CustomSelenideElement getElementText();
    CustomSelenideElement getElementOwnText();
    CustomSelenideElement getElementValue();
    CustomSelenideElement getElementAttributeValue(Attribute attribute);
    CustomSelenideElement checkElementAttributeValue(Attribute attribute, String attributeValue);
    CustomSelenideElement checkElementCssValue(String cssAttribute, String attributeValue);
    CustomSelenideElement checkElementAttributeMatching(Attribute attribute, String attributeRegex);
    CustomSelenideElement checkElementAttributeNotMatching(Attribute attribute, String attributeRegex);
    CustomSelenideElement checkFieldEnabled();
    CustomSelenideElement checkFieldDisabled();
    CustomSelenideElement checkFieldReadOnly();
    CustomSelenideElement checkFieldAppear();
    CustomSelenideElement clickField();
    CustomSelenideElement clickButton();
    CustomSelenideElement checkButtonEnabled();
    CustomSelenideElement checkButtonDisabled();
    CustomSelenideElement checkButtonAppear();
    CustomSelenideElement checkButtonDisappear();
    CustomSelenideElement checkElementDisappear();
    CustomSelenideElement checkElementText(String text);
    CustomSelenideElement checkElementValue(String value);
    CustomSelenideElement checkElementMatchText(String regex);
    CustomSelenideElement checkElementHref(String href);
    CustomSelenideElement clickElementLink(String href);
    CustomSelenideElement getCheckBoxValue();
    CustomSelenideElement hoverElement();
    CustomSelenideElement uploadFile(File file);
    CustomSelenideElement checkElementSelect(Boolean value);
}
