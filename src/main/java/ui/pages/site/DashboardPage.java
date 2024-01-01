package ui.pages.site;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ui.pages.factory.CustomLocator;
import ui.selectors.CustomSelenideElement;

public class DashboardPage {

    @As("Тайтл Dashboard")
    @CustomLocator(classStartsWith = "AppHeader-context-item-label")
    private CustomSelenideElement title;

    @As("Поиск")
    @CustomLocator(attributeName = "data-target", attributeValue = "qbsearch-input.inputButton")
    private CustomSelenideElement searchInput;

    public static DashboardPage init() {
        return Selenide.page(DashboardPage.class);
    }

    @Step("[Проверка] Тайтл на странице Dashboard содержит слово Dashboard")
    public DashboardPage checkDashboardTitle(){
        title._checkElementMatchText("^.*Dashboard.*$");
        return this;
    }

    @Step("[Проверка] Тайтл на странице Dashboard содержит слово Dashboard")
    public SearchSuggestionsBlock openSearch(){
        searchInput._clickField();
        return SearchSuggestionsBlock.init();
    }
}
