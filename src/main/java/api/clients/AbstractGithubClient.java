package api.clients;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractGithubClient extends DefaultRestAssuredClient{


    protected RequestSpecification createRequestSpec() {
        return RestAssured.given()
                .when()
                .header("Accept", "application/vnd.github+json")
                .header(makeBearerAuthorizationHeader("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"))
                .header("X-GitHub-Api-Version", "2022-11-28")
                .baseUri("https://api.github.com/");
    }
}
