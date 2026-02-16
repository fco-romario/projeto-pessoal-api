package projeto_pessoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Por enquanto, retorna um nome fixo.
        // Quando tiver Security, mudaremos a lógica aqui dentro.
        return () -> Optional.of("SYSTEM_ADMIN");
    }
}
