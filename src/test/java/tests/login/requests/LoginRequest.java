package tests.login.requests;


import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import data.Data;

public class LoginRequest {

    @Step("Realizar login - POST")
    public Response logar(String user, String password, String path) {
        return RestAssured.given()
                .body(Data.getSucessUser(user,password))
                .contentType(ContentType.JSON)
                .when()
                .post(path);
    }
}
