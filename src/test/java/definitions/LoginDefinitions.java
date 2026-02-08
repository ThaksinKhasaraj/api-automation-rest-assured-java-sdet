package definitions;

import client.AuthClient;
import client.UserClient;
import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.LoginRequest;
import model.LoginResponse;
import util.ConfigLoader;

import java.util.Map;

import static org.junit.Assert.assertEquals;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LoginDefinitions {

    private final TestContext context = new TestContext();
    private final AuthClient authClient = new AuthClient();
    private final UserClient userClient = new UserClient();

    @Given("user logs in")
    public void user_logs_in() {
        Map<String, String> login = ConfigLoader.getLogin();

        LoginRequest request = new LoginRequest();
        request.setEmail(login.get("email"));
        request.setPassword(login.get("password"));

        LoginResponse response = authClient.login(request);
        context.setToken(response.getToken());

        // stored response (mock response for assert)
        context.setResponse(
                RestAssured.given().when().get() // dummy response holder
        );
    }

    @When("user gets users")
    public void user_gets_users() {
        Response response = userClient.getUsers(context.getToken());
        context.setResponse(response);
    }


    @Then("status is {int}")
    public void status_is(Integer status) {
        assertEquals(status.intValue(), context.getResponse().statusCode());
    }

    @Then("response matches users schema")
    public void response_matches_users_schema() {
        context.getResponse()
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/users-schema.json"));
    }
}
