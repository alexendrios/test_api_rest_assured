package tests.test.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.test.requests.TestRequest;
import static org.hamcrest.Matchers.containsString;

@Slf4j
@Feature("Test")
@Owner("SICREDI")
public class TestTest {

    TestRequest testRequest = new TestRequest();
    String pathValido = "https://dummyjson.com/test";
    String pathInvalido = "https://dummyjson.com/tes";

    @Test
    @Tag("regressao")
    @DisplayName("TC03 - Teste Documentação Com Sucesso")
    @Description("Deve realizar o Teste com sucesso.")
    public void deveRealizarTesteComSucesso(){
        testRequest.test(pathValido)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body("status", containsString("ok"));
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC04 - Teste Documentação com a uri inválida")
    @Description("Deve realizar o Teste com a uri inválida.")
    public void deveRealizarTesteComAUriInvalida(){
        testRequest.test(pathInvalido)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log().all()
                .body( containsString("not found!"));
    }
}