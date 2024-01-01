package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.Locale;
import java.util.Optional;

public interface ValidatableRecordResponse {
    Response response();

    default Response checkResponseStatusCode200(String errorMessage) {
        return checkResponseStatusCode(200, errorMessage);
    }

    default Response checkResponseStatusCode(int statusCode) {
        return checkResponseStatusCode(statusCode, "Код ответа не соответствует ожидаемому");
    }

    @Step("Проверка статус-кода ответа API")
    default Response checkResponseStatusCode(int statusCode, String errorMessage) {
        var response = response();
        var requestIdHeader = "Request-id";
        var requestId = response.header(requestIdHeader.toLowerCase(Locale.ROOT));
        var message = """
                %s
                %s: %s
                Status code: %s
                Body:
                %s
                """.formatted(
                errorMessage,
                requestIdHeader, Optional.ofNullable(requestId).orElse("Отсутствует"),
                response.statusCode(),
                response.getBody().asPrettyString()
        );

        Assertions.assertThat(response.statusCode())
                .as(message)
                .isEqualTo(statusCode);

        return response;
    }
}
