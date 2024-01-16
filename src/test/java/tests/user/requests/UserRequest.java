package tests.user.requests;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserRequest {
    @Step("Realizar a busca dos usuários Cadastrados - GET")
    public Response user(String path, String ID){
             return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path + ID);
    }
}