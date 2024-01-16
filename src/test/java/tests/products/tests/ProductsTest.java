package tests.products.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.products.requests.ProductsRequest;
import static data.Data.*;
import static org.hamcrest.Matchers.*;
import static utils.Util.getToken;

@Slf4j
@Feature("Products")
@Owner("SICREDI")
public class ProductsTest {
    String ID;
    String token;
    String path = "https://dummyjson.com/products/";
    String pathAutenticado = "https://dummyjson.com/auth/products/";
    String pathCadatro = "https://dummyjson.com/products/add";
    ProductsRequest productsRequest = new ProductsRequest();


    @Test
    @Tag("regressao")
    @DisplayName("TC08 - Busca de Todos os produtos cadastrados com sucesso")
    @Description("Deve realizar a busca dos produtos cadastrados com sucesso.")
    public void deveRealizarAbuscaDosProdutosCadastradosComSucesso() {
        String ID = "";
        productsRequest.buscaProdutos(path, ID)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body("products", not(empty()))
                .body("products[1]", hasKey("title"))
                .body("products[1]", hasKey("price"))
                .body("products[1]", hasKey("rating"))
                .body("products[1]", hasKey("stock"))
                .body("products[1]", hasKey("brand"))
                .body("products[1]", hasKey("category"));
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC09 - Busca do produto pelo - ID - com Sucesso")
    @Description("Deve realizar a busca do produto casadatrado pelo ID com sucesso.")
    public void deveRealizarAbuscaDoProdutoCadastradoPeloIdComSucesso(){
        ID = "50";
        productsRequest.buscaProdutos(path, ID)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body("title", containsString("Women Shoes") )
                .body("price", equalTo(36) )
                .body("rating", equalTo(4.33f) )
                .body("stock", equalTo(46) )
                .body("brand", containsString("Arrivals Genuine") )
                .body("category", containsString("womens-shoes") );
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC10 - Busca do produto pelo ID - Não Encontrado")
    @Description("Deve realizar a busca do produto pelo ID - Não Encontrado")
    public void deveRealizarAbuscaProdutoPeloIdNaoEncontrado(){
        ID = "10000";
        productsRequest.buscaProdutos(path, ID)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat()
                .body("message", containsString("Product with id '"+ID+"' not found") )
                .log().all();
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC11 - Busca dos Produtos cadastrados autenticado na aplicação com sucesso")
    @Description("Deve realizar a busca dos produtos cadastrados autenticado na aplicação com sucesso.")
    public void deveRealizarAbuscaDosProdutosCadastradosAutenticadoNaAplicacaoComSucesso(){
        token = getToken("kminchelle", "0lelplR");
        ID = "";
        productsRequest.buscaProdutosAutenticado(pathAutenticado,ID,token)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body("products[1].'title'", containsString("iPhone X") )
                .body("products[1].'price'", equalTo(899) )
                .body("products[1].'rating'", equalTo(4.44f) )
                .body("products[1].'stock'", equalTo(34) )
                .body("products[1].'brand'", containsString("Apple") )
                .body("products[1].'category'", containsString("smartphones") );
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC12 - Busca de peodutos cadastrados autenticado na aplicação - Token Expirado")
    @Description("Deve realizar a busca dos produtos cadastratos autenticado  na aplicação - Token Expirado.")
    public void deveRealizarAbuscaDosProdutosCadastradoAutenticadoNaAplicacaoTokenExpirado(){
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTUsInVzZXJuYW1lIjoia21pbmNoZWxsZSIsImVtYWlsIjoia21pbmNoZWxsZUBxcS5jb20iLCJmaXJzdE5hbWUiOiJKZWFubmUiLCJsYXN0TmFtZSI6IkhhbHZvcnNvbiIsImdlbmRlciI6ImZlbWFsZSIsImltYWdlIjoiaHR0cHM6Ly9yb2JvaGFzaC5vcmcvYXV0cXVpYXV0LnBuZyIsImlhdCI6MTY5ODEyOTQ3MiwiZXhwIjoxNjk4MTMzMDcyfQ.XF_5gqRdpIs9zHA5m2OndIuhmaXNCo5gJ3ckLgn6UfI";
        ID = "";
        productsRequest.buscaProdutosAutenticado(pathAutenticado,ID,token)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .log().all()
                .body("name", containsString("TokenExpiredError") )
                .body("message", containsString("Token Expired!") );
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC13 - Busca de peodutos cadastrados autenticado na aplicação - token Inválido")
    @Description("Deve realizar a busca dos produtos cadastrados autenticado na aplicação - token Inválido")
    public void deveRealizarAbuscaDosProdutosCadastradosAutenticadoNaAplicacaoTokenInvalido(){
        token = "FHJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTUsInVzZXJuYW1lIjoia21pbmNoZWxsZSIsImVtYWlsIjoia21pbmNoZWxsZUBxcS5jb20iLCJmaXJzdE5hbWUiOiJKZWFubmUiLCJsYXN0TmFtZSI6IkhhbHZvcnNvbiIsImdlbmRlciI6ImZlbWFsZSIsImltYWdlIjoiaHR0cHM6Ly9yb2JvaGFzaC5vcmcvYXV0cXVpYXV0LnBuZyIsImlhdCI6MTY5ODEyOTQ3MiwiZXhwIjoxNjk4MTMzMDcyfQ.XF_5gqRdpIs9zHA5m2OndIuhmaXNCo5gJ3ckLgn6UfI";
        ID = "";
        productsRequest.buscaProdutosAutenticado(pathAutenticado,ID,token)
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .log().all()
                .body("message", containsString("invalid token") );
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC14 - Busca de Produtos cadastrados autenticado na aplicação  - Problema de Autenticação")
    @Description("Deve realizar a busca de produtos cadastrados  autencticado na aplicação-  Problema de Autenticação")
    public void deveRealizarAbuscaDeProdutosCadastradosAutenticadoNaApliccaoProblemaDeAutenticcao(){
        token = getToken("kminchelle", "0lelplR");
        productsRequest.buscaProdutosAutenticadosComfalha(token, pathAutenticado)
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .log().all()
                .body("message", containsString("Authentication Problem") );
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC15 - Busca de produto cadastrado autenticado na aplicação pelo ID com sucesso")
    @Description("Deve realizar a busca de produto cadastrado autenticado na aplicação pelo ID com sucesso")

    public void deveRealizarAbuscaDeProdutoCadastradoAutenticadoNaAplicacaoPeloIdComSucesso(){
        token = getToken("kminchelle", "0lelplR");
        ID = "50";
        productsRequest.buscaProdutosAutenticado(pathAutenticado,ID, token)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body("title", containsString("Women Shoes") )
                .body("price", equalTo(36) )
                .body("rating", equalTo(4.33f) )
                .body("stock", equalTo(46) )
                .body("brand", containsString("Arrivals Genuine") )
                .body("category", containsString("womens-shoes") );
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC16 - Busca de produto cadastrado autenticado na aplicação pelo ID - Não Encontrado")
    @Description("Deve realizar a busca de produto cadastrado autenticado na aplicação pelo - Não Encontrado.")
    public void deveRealizarAbuscaDeProdutoCadastradoAutenticadoNaAplicacaoPeloIdNaoEncontrado(){
        ID = "10000";
        token = getToken("kminchelle", "0lelplR");
        productsRequest.buscaProdutosAutenticado(pathAutenticado,ID, token)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .assertThat()
                .body("message", containsString("Product with id '"+ID+"' not found") )
                .log().all();
    }

    @Test
    @Tag("regressao")
    @Tag("BUG")
    @DisplayName("TC17 - Cadastro de um Produto com Sucesso.")
    @Description("Deve realizar o cadastro de um Produto com Sucesso.")
    @Flaky
    @Issue("BUG-01")
    @TmsLink("Descrição: Ao cadastrar o produto o mesmo teria que retornar status code 201 e está retornando status code 200")
    @Severity(SeverityLevel.CRITICAL)
    public void deveRealizarOCadastroDeUmProdutoComSucesso() {
        System.out.println(getSucessProduto());
        productsRequest.cadastroProduto(pathCadatro,getSucessProduto())
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @Tag("regressao")
    @Tag("BUG")
    @DisplayName("TC18 - Cadastro de um Produto com Sucesso - Verificando se o mesmo está refletindo na aplicação")
    @Flaky
    @Issue("BUG-02")
    @TmsLink("Descrição: Os produtos cadastrado não está pesistindo na aplicação")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deve realizar o cadastro de um Produto com Sucesso - Verificando se o mesmo está refletindo na aplicação")
    public void deveRealizarOCadastroDeUmProdutoComSucessoVerificandoSeOmesmoEstaRefletindoNaAplicacao() {
       Response res = productsRequest.cadastroProduto(pathCadatro,getSucessProduto());
            res
                .then()
                .log().all();

       ID = res.jsonPath().getString("id");
        productsRequest.buscaProdutos(path,ID)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Tag("regressao")
    @Tag("BUG")
    @DisplayName("TC19 - cadastro de um produto com as informações divergente da Documentação")
    @Flaky
    @Issue("BUG-03")
    @TmsLink("DESCRIÇÃO: Ao mudar o tipo de Dados númerico ao inserir um tipo booleando => Aplicação está retonando 200.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deve realizar o cadastro de um produto com as informações divergente da Documentação.")
    public void deveRealizarOvadastroDeUmProdutoComAsInformacoesDivergenteDaDocumentacao() {
        Response res= productsRequest.cadastroProduto(pathCadatro, getInSucessProduto());
        res
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag("regressao")
    @Tag("BUG")
    @DisplayName("TC20 - cadastro de um produto com os valores das keys divergente da Dacumentação")
    @Flaky
    @Issue("BUG-04")
    @TmsLink("Descrição: Ao mudar todas as keys (chaves) renomeando e mudando o tipo de dados totalmente divergente da Documentação  => Aplicação está retonando 200.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deve realizar o cadastro de um produto com os valores das keys (chaves) divergente da Dacumentação.")
    public void deveRealizarOcadastroDeUmProdutoComOsValoresDasKeysChavesDivergenteDaDocumentacao() {
        Response res= productsRequest.cadastroProduto(pathCadatro, getFormatoErradoProduto());
        res
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Tag("regressao")
    @Tag("BUG")
    @DisplayName("TC21 - cadastro de um produto autenticado na aplicação com Sucesso - Verificando se o mesmo está refletindo na aplicação")
    @Flaky
    @Issue("BUG-05")
    @TmsLink("Descrição: Os produtos cadastrado não está refletindo, mesmo, realizando o cadastro autenticado na aplicação")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deve realizar o cadastro de um produto autenticado na aplicacao com Sucesso - Verificando se o mesmo está refletindo na aplicação")
    public void deveRealizarOcadastroDeUmProdutoAutenticadoNaAplicacaoComSucessoVerificandoSeOMesmoEstaRefletindoNaAplicacao(){
        token = getToken("kminchelle", "0lelplR");
        Response res=productsRequest.cadastroProdutoautenticado(token,pathCadatro,getSucessProduto());
        res
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);


        ID = res.jsonPath().getString("id");
        productsRequest.buscaProdutosAutenticado(path,ID,token)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("message", containsString("User with id '"+ID+"Unexpected string in JSON at position 106") );
    }

    @Test
    @Tag("regressao")
    @DisplayName("TC22 - cadastro de um produto autenticado na aplicação com Sucesso - Formato do Documento Inválido")
    @Description("Deve realizar o cadastro de um produto autenticado na aplicacao com Sucesso - Formato do Documento Inválido")
    public void deveRealizarOcadastroDeUmProdutoAutenticadoNaAplicacaoComSucessoFormatoDoDocumentoInvalido(){
        token = getToken("kminchelle", "0lelplR");
        Response res=productsRequest.cadastroProdutoautenticado(token,pathCadatro,getFormatoErradoDocumento());
        res
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .assertThat()
                .body("message", containsString("Unexpected string in JSON at position") );
    }
}
