package ui.commands.field;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import ui.pages.common.CommonPageHelper;
import ui.selectors.CustomSelenideElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static ui.pages.common.CommonPageHelper.getElementAliasName;

public class _CheckFieldDisabled implements Command<CustomSelenideElement> {

    @Override
    @Nonnull
    public CustomSelenideElement execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
        CommonPageHelper.checkFieldDisabled(proxy, getElementAliasName(proxy));
        return (CustomSelenideElement) proxy;
    }
}
