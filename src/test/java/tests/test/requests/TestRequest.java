package tests.test.requests;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TestRequest {

    @Step("Realizar Test - Disponível na Documentação - GET")
    public Response test(String path) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path);
    }
}