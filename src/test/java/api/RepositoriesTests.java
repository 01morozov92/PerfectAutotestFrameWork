package api;

import api.clients.reositories.RepositoryClient;
import api.clients.reositories.UserRepositoryClient;
import api.models.repositories.getuserrepository.response.GetUserRepositoryModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import reporting.ReportStep;

import java.util.HashMap;
import java.util.Map;

public class RepositoriesTests {

    private final Map<String, String> repositoriesForDelete = new HashMap<>();
    private final RepositoryClient repositoryClient = new RepositoryClient();

    @Test(description = "У пользователя есть репозиторий")
    public void getRepositoriesTest() {
        var userRepositoryClient = new UserRepositoryClient();
        var userName = "usefulrepository";
        var repositoryName = "new-repo";

        ReportStep.preconditionStep("Создание репозитория", () -> {
            userRepositoryClient.createRepository(repositoryName);
            repositoriesForDelete.put("usefulrepository", "new-repo");
        });

        var listOfRepositoryNames = userRepositoryClient.getRepositories("usefulrepository").getJson()
                .stream()
                .map(GetUserRepositoryModel::getName)
                .toList();

        ReportStep.checkStep("У пользователя с именем: %s, присутствует репозиторий с именем: %s", () -> {
            Assertions.assertThat(listOfRepositoryNames)
                    .as("Не найдено ни одного репозитория с именем: %s, у пользователя с именем: %s".formatted(repositoryName, userName))
                    .contains(repositoryName);
        });
    }

    @AfterClass
    public void deleteTestData() {
        if (!repositoriesForDelete.isEmpty()) {
            repositoriesForDelete.forEach((key, value) ->
                    repositoryClient.deleteRepository(key, value)
                            .checkResponseStatusCode(201,
                                    "Произошла ошибка при удалении репозитория с именем: %s".formatted(key)));
        }
    }
}
