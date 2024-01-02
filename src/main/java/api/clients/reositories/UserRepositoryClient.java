package api.clients.reositories;

import api.clients.AbstractGithubClient;
import api.models.repositories.createuserrepository.CreateRepositoryRequestModel;
import api.models.repositories.getuserrepository.response.GetUserRepositoryModel;
import api.recordsresponses.GetUserRepositoryModelResponse;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

public class UserRepositoryClient extends AbstractGithubClient {

    private static final String BASE_PATH_USERS = "users";
    private static final String BASE_PATH_USER = "user";

    @Step("Получение всех репозиториев пользователя с именем: {0}")
    public GetUserRepositoryModelResponse getRepositories(String userName) {
        return new GetUserRepositoryModelResponse(createUsersRepositorySpec()
                .get("/%s/repos".formatted(userName)));
    }

    @Step("Создание репозитория с именем: {0}")
    public GetUserRepositoryModel createRepository(String repositoryName) {
        var response = new GetUserRepositoryModelResponse(createUserRepositorySpec()
                .body(CreateRepositoryRequestModel.builder()
                        .name(repositoryName)
                        .description("This is autotest repository")
                        .homepage("https://github.com/")
                        .isPrivate(false)
                        .isTemplate(true)
                        .build())
                .post("/repos"));

        response.checkResponseStatusCode(201, "Произошла ошибка при создании репозитория");
        return response.getJson();
    }

    protected RequestSpecification createUsersRepositorySpec() {
        return createRequestSpec()
                .basePath(BASE_PATH_USERS);
    }

    protected RequestSpecification createUserRepositorySpec() {
        return createRequestSpec()
                .basePath(BASE_PATH_USER);
    }
}
