package tests.products.requests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import static io.restassured.RestAssured.given;

@Slf4j
@Feature("Products")
@Owner("SICREDI")
public class ProductsRequest {

    @Step("Realizar Busca dos produtos - GET")
    public Response buscaProdutos(String path, String ID){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path +ID);
    }

    @Step("Realizar Busca de produtos autenticado na aplicação - GET")
    public Response buscaProdutosAutenticado(String path, String ID, String token){
        return given()
                .header("Authorization", "Bearer " +token)
                .contentType(ContentType.JSON)
                .when()
                .get(path+ID);
    }

    @Step("Realizar Busca dos produtos cadastrados - Authentication Problem - GET")
    public Response buscaProdutosAutenticadosComfalha(String token, String path){
        return given()
                .contentType("Authorization Bearer "+token)
                .contentType(ContentType.JSON)
                .when()
                .get(path);
    }

    @Step("Realizar cadastro de Produtos  - POST")
    public Response cadastroProduto(String path, String dados){
        return given()
                .body(dados)
                .contentType(ContentType.JSON)
                .when()
                .post(path);
    }

    @Step("Realizar o cadastro de um produto com a aplicação autenticada - POST")
    public Response cadastroProdutoautenticado(String token, String path, String dados){
        return given()
                .header("Authorization", "Bearer " + token)
                .body(dados)
                .contentType(ContentType.JSON)
                .when()
                .post(path);
    }
}
