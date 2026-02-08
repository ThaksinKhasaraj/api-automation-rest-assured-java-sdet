package hooks;

import io.cucumber.java.Before;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import util.ConfigLoader;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class Hooks {

    @Before
    public void setup() {
        Map<String, String> api = ConfigLoader.getApi();

        baseURI = api.get("baseUrl");

        requestSpecification = given()
                .header("x-api-key", api.get("apiKey"))
                .contentType("application/json");
        RestAssured.filters(new AllureRestAssured());
    }
}
