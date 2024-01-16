package tests.user.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.user.requests.UserRequest;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

@Slf4j
@Feature("User")
@Owner("SICREDI")
public class UserTest {

    UserRequest userRequest = new UserRequest();
    String path= "https://dummyjson.com/user/";
    String ID;

    @Test
    @Tag("regressao")
    @DisplayName("TC05 - Busca de Usuários Cadastrados Todos - Sucesso")
    @Description("Deve realizar a busca dos usuários cadastrados com sucesso.")
    public void deveRealizarAbuscaDosUsuariosCadastradosComSucesso(){
        ID = "";
        userRequest.user(path, ID)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body("users", not(empty()))
                .body("users", hasSize(greaterThan(0)))
                .body("users[0].firstName", not(emptyOrNullString()))
                .body("users[0].email", notNullValue())
                .body("users[0].birthDate", matchesRegex("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC06 - Busca de Usuários Cadastrados por ID com Sucesso")
    @Description("Deve realizar a busca de usuário cadastrado pelo o ID com sucesso.")
    public void deveRealizarAbuscaDeUsuarioCadastradoPeloIdComSucesso(){
        ID = "100";
        userRequest.user(path,ID)
                .then()
                .assertThat()
                .body("users", not(empty()))
                .body("firstName", not(emptyOrNullString()))
                .body("lastName", not(emptyOrNullString()) )
                .body("birthDate", matchesRegex("\\d{4}-\\d{2}-\\d{2}") )
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC07 - Busca de Usuário cadastrado por ID - Não Encontrado")
    @Description("Deve realizar a busca usuario Cadastradp por ID - Não Encontrado")
    public void deveRealizarAbuscaUsuarioCadastradoPorIdNaoEncontrado(){
        ID = "10000";
        userRequest.user(path,ID)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat()
                .body("message", containsString("User with id '"+ID+"' not found") )
                .log().all();
    }
}