package utils;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static data.Data.getSucessUser;

public class Util {

    static public String getToken(String user, String password){
        String token;
        String PATH_LOGIN = "https://dummyjson.com/auth/login";
        return token = given()
                .body(getSucessUser(user, password))
                .contentType(ContentType.JSON)
                .when()
                .post(PATH_LOGIN)
                .then()
                .extract()
                .path("token");
    }
}