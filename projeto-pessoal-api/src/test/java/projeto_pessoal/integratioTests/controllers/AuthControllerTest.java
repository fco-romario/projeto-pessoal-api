package projeto_pessoal.integratioTests.controllers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import projeto_pessoal.config.TestConfigs;
import projeto_pessoal.dto.PersonDTO;
import projeto_pessoal.dto.security.AccountCredentialsDTO;
import projeto_pessoal.dto.security.RegisterDTO;
import projeto_pessoal.dto.security.TokenDTO;
import projeto_pessoal.integratioTests.containers.AbstractIntegrationTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//Indica que os testes não serão executados em ordem aleatória (padrão do JUnit), mas seguirão uma ordem específica.
public class AuthControllerTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;
    private static PersonDTO person;
    private static TokenDTO tokenDTO;

    @BeforeAll
    static void setUp() {
        person = new PersonDTO();
        tokenDTO = new TokenDTO();

        // Configuramos o RestAssured para apontar para a porta dinâmica do Spring
        specification = new RequestSpecBuilder()
                .setBasePath("/auth") // Ajuste conforme seu @RequestMapping
                .setPort(8888) // Porta definida no seu application-test.yml
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    void signin() {
        AccountCredentialsDTO credentials = new AccountCredentialsDTO("rom", "admin123");

        tokenDTO = given()
                .basePath("/auth/signin")
                .port(TestConfigs.SERVER_PORT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)//verificar se nesse caso é realmente necessário
                .body(credentials)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenDTO.class);

        assertNotNull(tokenDTO.getAccessToken());
        assertNotNull(tokenDTO.getRefreshToken());

    }

    @Test
    @DisplayName("Deve criar um usuário com sucesso quando os dados forem válidos")
    @Order(2)
    void create() {
        RegisterDTO registerDTO = new RegisterDTO(
                "novousuario",
                "senha123456",
                "João Silva",
                "joao@email.com",
                "12345678901" // CPF com 11 dígitos
        );

        var response = given()
                .spec(specification)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(registerDTO)
                .when()
                .post("/createUser")
                .then()
                .statusCode(201) // HttpStatus.CREATED
                .extract()
                .as(AccountCredentialsDTO.class);

        assertNotNull(response);
        assertEquals("novousuario", response.getUsername());
    }

    @Test
    @Order(3)
    void refreshToken() {
        tokenDTO = given()
                .basePath("/auth/refresh")
                .port(TestConfigs.SERVER_PORT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("username", tokenDTO.getUsername())
                .header(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + tokenDTO.getRefreshToken())
                .when()
                .put("{username}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(TokenDTO.class);

        assertNotNull(tokenDTO.getAccessToken());
        assertNotNull(tokenDTO.getRefreshToken());
    }
}
