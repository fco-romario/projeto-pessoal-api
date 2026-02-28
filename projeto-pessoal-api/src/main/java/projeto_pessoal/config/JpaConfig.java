package projeto_pessoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import projeto_pessoal.domain.security.User;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {

    //adiciona id v1
    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()
                    || authentication.getPrincipal().equals("anonymousUser")) {
                return Optional.empty();
            }

            // O Spring Security guarda o UserDetails no Principal.
            // Como seu User implementa UserDetails, fazemos o cast:
            if (authentication.getPrincipal() instanceof User user) {
                return Optional.ofNullable(user.getId()); // Use ofNullable por segurança
            }

            return Optional.empty();
        };
    }

    //adiciona id v2
//    @Bean
//    public AuditorAware<Long> auditorProvider() {
//        return () -> {
//            Authentication authentication =
//                    SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication == null || !authentication.isAuthenticated()) {
//                return Optional.empty();
//            }
//
//            User user = (User) authentication.getPrincipal();
//            return Optional.of(user.getId());
//        };
//    }

    //adiciona o username
//    @Bean
//    public AuditorAware<String> auditorProvider() {
//        return () -> {
//            // 1. Pega a autenticação atual do contexto do Spring Security
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            // 2. Verifica se existe alguém logado e se não é um acesso anônimo
//            if (authentication == null || !authentication.isAuthenticated()
//                    || authentication.getPrincipal().equals("anonymousUser")) {
//                return Optional.of("SYSTEM"); // Caso seja uma tarefa interna ou criação de usuário (sem log)
//            }
//
//            // 3. Retorna o nome do usuário que está no Token JWT
//            return Optional.of(authentication.get);
//        };
//    }

//    @Bean
//    public AuditorAware<String> auditorProvider() {
//        // Por enquanto, retorna um nome fixo.
//        // Quando tiver Security, mudaremos a lógica aqui dentro.
//        return () -> Optional.of("SYSTEM_ADMIN");
//    }
}
