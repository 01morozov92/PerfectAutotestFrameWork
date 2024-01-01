package ui;

import api.clients.reositories.RepositoryClient;
import api.clients.reositories.UserRepositoryClient;
import api.models.repositories.getuserrepository.response.GetUserRepositoryModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import reporting.ReportStep;
import ui.pages.site.MainPage;

import java.util.HashMap;
import java.util.Map;

import static api.clients.AbstractGithubClient.MAIN_CONFIG;

public class CheckUiTests {

    @Test(description = "Поиск репозитория - имя репозитория")
    public void checkUi() {
        MainPage.openMainPage()
                .goToSignInPage()
                .signIn(MAIN_CONFIG.getUserLogin(), MAIN_CONFIG.getUserPassword())
                .checkDashboardTitle()
                .openSearch()
                .search("selenide")
                .getResultListBlock()
                .getRepositoryByName("selenide/selenide")
                .goToThatRepository()
                .checkRepositoryName("selenide");
    }
}
