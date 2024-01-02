package api.clients;

import config.Configuration;
import config.MainConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class AbstractGithubClient extends DefaultRestAssuredClient{

    public static final MainConfig MAIN_CONFIG = Configuration.getMainConfig();

    protected RequestSpecification createRequestSpec() {
        return RestAssured.given()
                .when()
                .header("Accept", "application/vnd.github+json")
                .header(makeBearerAuthorizationHeader(MAIN_CONFIG.getUserToken()))
                .header("X-GitHub-Api-Version", "2022-11-28")
                .baseUri(MAIN_CONFIG.getBaseApiUri());
    }
}
