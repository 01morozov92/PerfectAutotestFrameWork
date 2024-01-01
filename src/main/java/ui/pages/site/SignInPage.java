package ui.pages.site;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import ui.BrowserManager;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ui.pages.factory.CustomLocator;
import ui.selectors.CustomSelenideElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInPage {

    @As("Логин")
    @CustomLocator(attributeName = "name", attributeValue = "login")
    private CustomSelenideElement loginField;

    @As("Пароль")
    @CustomLocator(attributeName = "name", attributeValue = "password")
    private CustomSelenideElement passwordField;

    public static SignInPage init() {
        return Selenide.page(SignInPage.class);
    }

    @Step("Переход к странице авторизации")
    public static SignInPage openSignInPage() {
        BrowserManager.openUrl("login");
        return init();
    }

    @Step("Авторизация")
    public DashboardPage signIn(String login, String password) {
        loginField._fillField(login);
        passwordField._fillField(password).submit();
        return DashboardPage.init();
    }
}
