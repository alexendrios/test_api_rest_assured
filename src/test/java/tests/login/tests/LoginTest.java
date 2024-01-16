package tests.login.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.login.requests.LoginRequest;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
@Feature("Login")
@Owner("SICREDI")
public class LoginTest {
    LoginRequest loginRequest = new LoginRequest();
    String pathLogin = "https://dummyjson.com/auth/login";

    @Test
    @Tag("regressao")
    @DisplayName("TC01 - Login com Sucesso")
    @Description("Deve realizar o login com sucesso.")
    public void deveRealizarOLoginComSucesso() throws Exception {
        loginRequest.logar("kminchelle", "0lelplR",pathLogin)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body("username", equalTo("kminchelle"));
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC02 - Login com o Usuário Inválido")
    @Description("Deve realizar o login com Usuário Inválido.")
    public void deveRealizarOLoginComUsuarioInvalido() throws Exception {
        loginRequest.logar("jalapao", "0lelplR",pathLogin)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Invalid credentials"));
    }
}