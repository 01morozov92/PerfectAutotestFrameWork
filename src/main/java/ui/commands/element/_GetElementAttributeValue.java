package ui.commands.element;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import ui.pages.common.Attribute;
import ui.pages.common.CommonPageHelper;
import ui.selectors.CustomSelenideElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.util.Objects.requireNonNull;
import static ui.pages.common.CommonPageHelper.getElementAliasName;

public class _GetElementAttributeValue implements Command<CustomSelenideElement> {

    @Override
    @Nonnull
    public CustomSelenideElement execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
        CommonPageHelper.getElementAttributeValue(proxy, getElementAliasName(proxy), (Attribute) requireNonNull(args)[0]);
        return (CustomSelenideElement) proxy;
    }
}
