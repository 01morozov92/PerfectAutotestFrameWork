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
public class MainPage {

    @As("Войти")
    @CustomLocator(classStartsWith = "HeaderMenu-link HeaderMenu-link--sign-in")
    private CustomSelenideElement signInElement;

    public static MainPage init() {
        return Selenide.page(MainPage.class);
    }

    @Step("Переход на главную страницу")
    public static MainPage openMainPage(){
        BrowserManager.openUrl("");
        return init();
    }

    @Step("Переход к странице авторизации")
    public SignInPage goToSignInPage() {
        signInElement._click();
        return SignInPage.init();
    }
}
