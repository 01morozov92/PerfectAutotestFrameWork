package ui.commands.element;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import ui.pages.common.CommonPageHelper;
import ui.selectors.CustomSelenideElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.requireNonNull;
import static ui.pages.common.CommonPageHelper.getElementAliasName;

@ParametersAreNonnullByDefault
public class _CheckElementHref implements Command<CustomSelenideElement> {

    @Override
    @Nonnull
    public CustomSelenideElement execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
        CommonPageHelper.checkElementHref(proxy, getElementAliasName(proxy), (String) requireNonNull(args)[0]);
        return (CustomSelenideElement) proxy;
    }
}
