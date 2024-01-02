package api.clients;

import io.restassured.http.Header;

public class DefaultRestAssuredClient {

    protected Header makeBearerAuthorizationHeader(String token) {
        return new Header("authorization", "Bearer " + token);
    }
}
