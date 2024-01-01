package ui.pages.site;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import ui.pages.factory.CustomLocator;
import ui.selectors.CustomSelenideElement;

import static com.codeborne.selenide.Selenide.sleep;

public class SearchResultsPage {

    @CustomLocator(attributeName = "data-testid", attributeValue = "search-sub-header")
    private CustomSelenideElement title;

    @Getter
    @CustomLocator(attributeName = "data-testid", attributeValue = "results-list")
    private ResultsListBlock resultListBlock;

    public static SearchResultsPage init() {
        return Selenide.page(SearchResultsPage.class);
    }
}
