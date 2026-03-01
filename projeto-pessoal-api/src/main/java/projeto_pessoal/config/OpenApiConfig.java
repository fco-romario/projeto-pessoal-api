package projeto_pessoal.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Pessoal API")
                        .version("v1")
                        .description("API RESTful para gerenciamento de usuários e pessoas, " +
                                "implementando autenticação JWT e auditoria JPA.")
                        .termsOfService("https://meusite.com/termos")
                        .license(new License()
                                .name("Uso Privado / Propriedade de Romário Alves")
                                .url("https://projeto-pessoal.com/politica-privacidade")))
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth")) // <--- 1. "Ei Swagger, use a configuração chamada 'bearerAuth'"
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", // <--- 2. "Aqui está a configuração chamada 'bearerAuth'"
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    //http://localhost:8080/swagger-ui/index.html
}
