package ui.pages.site;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.support.FindBy;
import ui.pages.factory.CustomLocator;
import ui.pages.factory.ElementsContainer;
import ui.selectors.CustomSelenideElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchSuggestionsBlock {

    @As("Строка поиска")
    @FindBy(id = "query-builder-test")
    private CustomSelenideElement searchInput;

    public static SearchSuggestionsBlock init(){
        return Selenide.page(SearchSuggestionsBlock.class);
    }

    @Step("Поиск по запросу: {0}")
    public SearchResultsPage search(String query){
        searchInput.fillField(query).pressEnter();
        return SearchResultsPage.init();
    }
}
