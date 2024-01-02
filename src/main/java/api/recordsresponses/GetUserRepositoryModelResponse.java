package api.recordsresponses;

import api.ValidatableRecordResponse;
import api.models.repositories.getuserrepository.response.GetUserRepositoryModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

public record GetUserRepositoryModelResponse(Response response) implements ValidatableRecordResponse {

    @Step
    public GetUserRepositoryModel getJson() {
        return response.as(GetUserRepositoryModel.class);
    }

    @Step
    public List<GetUserRepositoryModel> getListJson() {
        return response.jsonPath().getList("", GetUserRepositoryModel.class);
    }
}
