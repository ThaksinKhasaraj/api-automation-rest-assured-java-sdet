package client;

import model.LoginRequest;
import model.LoginResponse;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public LoginResponse login(LoginRequest request) {
        return given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/login")
                .then()
                .extract()
                .as(LoginResponse.class);
    }
}
