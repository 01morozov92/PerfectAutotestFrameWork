package api;

import api.clients.reositories.RepositoryClient;
import api.clients.reositories.UserRepositoryClient;
import api.models.repositories.getuserrepository.response.GetUserRepositoryModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import reporting.ReportStep;

public class RepositoriesTests {

    @Test(description = "Успешное создание репозитория через ручку /repos")
    public void getRepositoriesTest() {
        var userRepositoryClient = new UserRepositoryClient();
        var userName = "usefulrepository";
        var repositoryName = "new-repo";

        ReportStep.preconditionStep("Создание репозитория", () -> {
            userRepositoryClient.createRepository(repositoryName)
                    .markForDelete();
        });

        var listOfRepositoryNames = userRepositoryClient.getRepositories("usefulrepository").getListJson()
                .stream()
                .map(GetUserRepositoryModel::getName)
                .toList();

        ReportStep.checkStep("У пользователя с именем: %s, присутствует репозиторий с именем: %s", () -> {
            Assertions.assertThat(listOfRepositoryNames)
                    .as("Не найдено ни одного репозитория с именем: %s, у пользователя с именем: %s".formatted(repositoryName, userName))
                    .contains(repositoryName);
        });
    }
}
