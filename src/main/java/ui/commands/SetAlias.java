package ui.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import ui.selectors.CustomSelenideElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.requireNonNull;

@ParametersAreNonnullByDefault
public class SetAlias implements Command<CustomSelenideElement> {

    @Override
    @CheckReturnValue
    @Nonnull
    public CustomSelenideElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) {
        locator.setAlias((String) requireNonNull(args)[0]);
        return (CustomSelenideElement) proxy;
    }
}
