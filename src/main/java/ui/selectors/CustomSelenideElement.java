package ui.selectors;

import com.codeborne.selenide.SelenideElement;
import ui.pages.common.Attribute;

import java.io.File;

public interface CustomSelenideElement extends SelenideElement {
    void _click();
    CustomSelenideElement _setAlias(String string);
    CustomSelenideElement _checkElementAppear();
    CustomSelenideElement _fillField(String newValue);
    CustomSelenideElement _getElementText();
    CustomSelenideElement _getElementOwnText();
    CustomSelenideElement _getElementValue();
    CustomSelenideElement _getElementAttributeValue(Attribute attribute);
    CustomSelenideElement _checkElementAttributeValue(Attribute attribute, String attributeValue);
    CustomSelenideElement _checkElementCssValue(String cssAttribute, String attributeValue);
    CustomSelenideElement _checkElementAttributeMatching(Attribute attribute, String attributeRegex);
    CustomSelenideElement _checkElementAttributeNotMatching(Attribute attribute, String attributeRegex);
    CustomSelenideElement _checkFieldEnabled();
    CustomSelenideElement _checkFieldDisabled();
    CustomSelenideElement _checkFieldReadOnly();
    CustomSelenideElement _checkFieldAppear();
    CustomSelenideElement _clickField();
    CustomSelenideElement _clickButton();
    CustomSelenideElement _checkButtonEnabled();
    CustomSelenideElement _checkButtonDisabled();
    CustomSelenideElement _checkButtonAppear();
    CustomSelenideElement _checkButtonDisappear();
    CustomSelenideElement _checkElementDisappear();
    CustomSelenideElement _checkElementText(String text);
    CustomSelenideElement _checkElementValue(String value);
    CustomSelenideElement _checkElementMatchText(String regex);
    CustomSelenideElement _checkElementHref(String href);
    CustomSelenideElement _clickElementLink(String href);
    CustomSelenideElement _getCheckBoxValue();
    CustomSelenideElement _hoverElement();
    CustomSelenideElement _uploadFile(File file);
    CustomSelenideElement _checkElementSelect(Boolean value);
}
