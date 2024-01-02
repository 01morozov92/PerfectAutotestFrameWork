package ui;

import org.testng.annotations.Test;
import ui.pages.site.MainPage;

public class CheckUiTests {

    @Test(description = "Поиск репозитория - имя репозитория")
    public void checkUi() {
        MainPage.openMainPage()
                .goToSignInPage()
                .signIn("usefulrepository", "TrueLVL010492")
                .checkDashboardTitle()
                .openSearch()
                .search("selenide")
                .getResultListBlock()
                .getRepositoryByName("selenide/selenide")
                .goToThatRepository()
                .checkRepositoryName("selenide");
    }
}
