package ui.pages.site;

import com.codeborne.selenide.As;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ui.pages.common.CommonCollectionsHelper;
import ui.pages.common.CommonPageHelper;
import ui.pages.factory.CustomLocator;
import ui.pages.factory.ElementsContainer;

import java.util.Objects;

public class ResultsListBlock extends ElementsContainer {

    @As("Результаты")
    @CustomLocator(tag = "h3", classStartsWith = "Box")
    private ElementsCollection results;

    public static ResultsListBlock init() {
        return Selenide.page(ResultsListBlock.class);
    }

    @Step("Получение репозитория из списка с именем: {0}")
    public LastRepository getRepositoryByName(String repositoryName) {
        return new LastRepository(CommonCollectionsHelper.checkCollectionIsNotEmpty(results)
                .asDynamicIterable()
                .stream()
                .filter(result -> Objects.equals(result.$x(".//a").getAttribute("href"),
                        "https://github.com/%s".formatted(repositoryName)))
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError("Не найден ни один репозиторий с именем %s".formatted(repositoryName))), repositoryName);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LastRepository {

        private SelenideElement last;
        private String repositoryName;

        public LastRepository(SelenideElement last, String repositoryName) {
            this.repositoryName = repositoryName;
            this.last = last;
        }

        @Step("Переход к этому репозиторию")
        public RepositoryPage goToThatRepository() {
            CommonPageHelper.clickElement(last.$x(".//a"), "Репозиторий '%s'".formatted(repositoryName));
            return RepositoryPage.init();
        }
    }
}
