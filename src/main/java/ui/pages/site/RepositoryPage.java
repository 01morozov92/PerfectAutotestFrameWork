package ui.pages.site;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ui.pages.factory.CustomLocator;
import ui.selectors.CustomSelenideElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryPage {

    @As("Имя репозитория")
    @CustomLocator(attributeName = "itemprop", attributeValue = "name")
    CustomSelenideElement repositoryName;

    public static RepositoryPage init() {
        return Selenide.page(RepositoryPage.class);
    }

    @Step("[Проверка] Имя репозитория - {0}")
    public RepositoryPage checkRepositoryName(String name){
        
        repositoryName._checkElementText(name);
        return this;
    }
}
