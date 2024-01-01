package api.clients;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class AbstractGithubClient {

    protected RequestSpecification createRequestSpec() {
        return RestAssured.given()
                .when()
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer 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")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .baseUri("https://api.github.com/");
    }
}
