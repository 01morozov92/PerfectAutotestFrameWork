package ui.commands.element;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import ui.pages.common.Attribute;
import ui.pages.common.CommonPageHelper;
import ui.selectors.CustomSelenideElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.requireNonNull;
import static ui.pages.common.CommonPageHelper.getElementAliasName;

@ParametersAreNonnullByDefault
public class _CheckElementCssValue implements Command<CustomSelenideElement> {

    @Override
    @Nonnull
    public CustomSelenideElement execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
        CommonPageHelper.checkElementCssValue(proxy, getElementAliasName(proxy), (String) requireNonNull(args)[0], (String) requireNonNull(args)[1]);
        return (CustomSelenideElement) proxy;
    }
}
