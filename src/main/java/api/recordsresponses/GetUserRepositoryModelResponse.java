package api.recordsresponses;

import api.ValidatableRecordResponse;
import api.models.repositories.getuserrepository.response.GetUserRepositoryModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

public record GetUserRepositoryModelResponse(Response response) implements ValidatableRecordResponse {

    @Step
    public List<GetUserRepositoryModel> getJson() {
        checkResponseStatusCode200("Ошибка при получении списка репозиториев");
        return response.jsonPath().getList("", GetUserRepositoryModel.class);
    }
}
