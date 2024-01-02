package ui.pages.factory;

import com.codeborne.selenide.selector.ByAttribute;
import com.codeborne.selenide.selector.ByText;
import com.codeborne.selenide.selector.WithText;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class CustomLocatorBuilder {

    /**
     * Метод вызывается в CustomPageFactory для присвоения полю By локатора
     * Если нужно написать новый кастомный локатор, то его необходимо поддержать здесь
     */
    public static By getCustomLocator(CustomLocator customLocator) {
        validateLocator(customLocator);

        if (isNotBlank(customLocator.withText()) && isNotBlank(customLocator.tag())) {
            return buildWithTextAndTag(customLocator.withText(), customLocator.tag());
        }

        if (isNotBlank(customLocator.classStartsWith()) && isNotBlank(customLocator.tag())) {
            return buildClassStartsWithAndTag(customLocator.classStartsWith(), customLocator.tag());
        }

        if (isNotBlank(customLocator.classStartsWith()) && isNotBlank(customLocator.text()) && isNotBlank(customLocator.tag())) {
            return buildClassStartsWithAndTextAndTag(customLocator.classStartsWith(), customLocator.text(), customLocator.tag());
        }

        if (isNotBlank(customLocator.classStartsWith()) && isNotBlank(customLocator.text())) {
            return buildClassStartsWithAndText(customLocator.classStartsWith(), customLocator.text());
        }

        if (isNotBlank(customLocator.classStartsWith()) && isNotBlank(customLocator.withText())) {
            return buildClassStartsWithAndWithText(customLocator.classStartsWith(), customLocator.withText());
        }

        if (isNotBlank(customLocator.classStartsWith())) {
            return buildClassStartsWith(customLocator.classStartsWith());
        }

        if (isNotBlank(customLocator.dataTarget())){
            return buildDataTarget(customLocator.dataTarget());
        }
        if (isNotBlank(customLocator.classStartsWith()) && isNotBlank(customLocator.notContainClass())) {
            return buildClassStartsWithAndNotContainOtherClass(customLocator.classStartsWith(),
                    customLocator.notContainClass());
        }
        if (isNotBlank(customLocator.text())) {
            return new ByText(customLocator.text());
        }

        if (isNotBlank(customLocator.withText())) {
            return new WithText(customLocator.withText());
        }

        if (isNotBlank(customLocator.attributeName()) && isNotBlank(customLocator.attributeValue())) {
            return new ByAttribute(customLocator.attributeName(), customLocator.attributeValue());
        } else if (isNotBlank(customLocator.attributeName())) {
            return buildAttributeName(customLocator.attributeName());
        }

        throw new NotImplementedException("Не можем обработать локатор");
    }

    private static By buildClassStartsWith(String className) {
        return By.xpath(String.format(".//*[starts-with(@class,'%s') or contains(@class,' %s') or contains(@class,'styled__%s')]", className, className, className));
    }

    public static By buildClassStartsWithAndNotContainOtherClass(String containClassName, String notContainClass) {
        return By.xpath(".//*[(starts-with(@class,'%s') or contains(@class,' %s')) and ".formatted(containClassName, containClassName) +
                "not(contains(@class,'%s'))]".formatted(notContainClass));
    }

    private static By buildClassStartsWithAndTextAndTag(String className, String text, String tag) {
        return By.xpath(String.format(".//%s[(starts-with(@class,'%s') or contains(@class,'%s')) and text()='%s']", tag, className, className, text));
    }

    private static By buildClassStartsWithAndTag(String className, String tag) {
        return By.xpath(String.format(".//%s[(starts-with(@class,'%s') or contains(@class,'%s'))]", tag, className, className));
    }

    private static By buildWithTextAndTag(String text, String tag) {
        var containsTextXpathExpression = "contains(normalize-space(translate(text(), '\t\n\r ', '    ')),'%s')".formatted(text);
        return By.xpath(".//%s[%s]".formatted(tag, containsTextXpathExpression));
    }

    private static By buildClassStartsWithAndText(String className, String text) {
        return By.xpath(String.format(".//*[(starts-with(@class,'%s') or contains(@class,'%s')) and text()='%s']", className, className, text));
    }

    private static By buildDataTarget(String dataTarget) {
        return By.xpath(".//*[@data-target='%s']".formatted(dataTarget));
    }

    private static By buildClassStartsWithAndWithText(String className, String text) {
        //Selenide realization withText()
        var containsTextXpathExpression = "contains(normalize-space(translate(text(), '\t\n\r ', '    ')),'%s')".formatted(text);
        return By.xpath(String.format(".//*[(starts-with(@class,'%s') or contains(@class,'%s')) and %s]", className, className, containsTextXpathExpression));
    }

    private static By buildAttributeName(String attributeName) {
        return By.xpath(String.format(".//*[@%s]", attributeName));
    }

    private static void validateLocator(CustomLocator customLocator) {
        var locators = new ArrayList<>();
        int expectedLocatorsSize = 1;

        if (isNotBlank(customLocator.classStartsWith()) && isNotBlank(customLocator.text())) {
            locators.addAll(List.of(customLocator.classStartsWith(), customLocator.text()));
            expectedLocatorsSize = 2;
        } else if (isNotBlank(customLocator.classStartsWith()) && isNotBlank(customLocator.withText())) {
            locators.addAll(List.of(customLocator.classStartsWith(), customLocator.withText()));
            expectedLocatorsSize = 2;
        } else if (isNotBlank(customLocator.classStartsWith())) {
            locators.add(customLocator.classStartsWith());
        }else if (isNotBlank(customLocator.dataTarget())){
            locators.add(customLocator.dataTarget());
        } else if (isNotBlank(customLocator.text())) {
            locators.add(customLocator.text());
        } else if (isNotBlank(customLocator.withText())) {
            locators.add(customLocator.withText());
        } else if (isAttributes(customLocator)) {
            locators.addAll(List.of(customLocator.attributeName(), customLocator.attributeValue()));
            expectedLocatorsSize = 2;
        } else if (isNotBlank(customLocator.attributeName())) {
            locators.add(customLocator.attributeName());
        }
        if (locators.size() != expectedLocatorsSize) {
            throw new InvalidSelectorException("CustomLocator должен содержать %d локатор/а/ов"
                    .formatted(expectedLocatorsSize));
        }
    }


    private static boolean isAttributes(CustomLocator customLocator) {
        var attrName = customLocator.attributeName();
        var attrValue = customLocator.attributeValue();

        if (isNotBlank(attrValue)) {

            if (isBlank(attrName)) {
                throw new InvalidSelectorException("""
                        CustomLocator для атрибутов должен содержать и attributeName, и attributeValue
                        Добавьте attributeName""");
            }
            return true;
        }
        return false;
    }
}
