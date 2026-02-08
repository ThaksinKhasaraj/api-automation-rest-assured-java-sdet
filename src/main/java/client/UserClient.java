package client;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {

    public Response getUsers(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users");
    }
}
