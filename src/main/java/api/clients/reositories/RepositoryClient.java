package api.clients.reositories;

import api.clients.AbstractGithubClient;
import api.recordsresponses.GetUserRepositoryModelResponse;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

public class RepositoryClient  extends AbstractGithubClient {

    private static final String BASE_PATH = "repos";

    @Step("Удаление репозитория с именем: {1}, для владельца с именем, {0}")
    public GetUserRepositoryModelResponse deleteRepository(String ownerName, String repositoryName) {
        return new GetUserRepositoryModelResponse(createRepositorySpec()
                .delete("/%s/%s".formatted(ownerName, repositoryName)));
    }

    protected RequestSpecification createRepositorySpec() {
        return createRequestSpec()
                .basePath(BASE_PATH);
    }
}
