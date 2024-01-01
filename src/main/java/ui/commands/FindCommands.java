package ui.commands;

import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;
import ui.selectors.CustomSelenideElement;

import static com.codeborne.selenide.WebDriverRunner.driver;

public class FindCommands {

    public static CustomSelenideElement _$(String selector) {
        return ElementFinder.wrap(driver(), CustomSelenideElement.class, null, By.cssSelector(selector), 0);
    }

    public static CustomSelenideElement _$x(String xpath) {
        return ElementFinder.wrap(driver(), CustomSelenideElement.class, null, By.xpath(xpath), 0);
    }
}
