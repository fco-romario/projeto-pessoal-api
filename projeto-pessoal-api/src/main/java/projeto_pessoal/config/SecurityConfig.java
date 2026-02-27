package projeto_pessoal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import projeto_pessoal.security.jwt.JwtTokenFilter;
import projeto_pessoal.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${security.jwt.token.secret-password:defaultPasswordSecret}")
    private String passwordSecret;//todo tentar mudar a secret do password e do token pra ver se todos deslogam e a senha continua ok

    private final JwtTokenProvider _tokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this._tokenProvider = jwtTokenProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){

        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
                passwordSecret, 8, 185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        return passwordEncoder;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(_tokenProvider);
        return http
                .httpBasic(AbstractHttpConfigurer::disable)// 1. Desabilita autenticação básica (HTTP Basic Auth) desativar o formulário de login padrão (pop-up)
                .csrf(AbstractHttpConfigurer::disable) // 2. Desabilita proteção CSRF (Cross-Site Request Forgery)
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class) //Adiciona um filtro customizado ANTES do filtro de autenticação padrão do Spring
                .sessionManagement( // 4. Define a política de sessão como STATELESS (sem sessão no servidor)
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(
                                        "/auth/signin", // Login
                                        "/auth/refresh/**",      // Refresh token
                                        "/auth/createUser",      // Criação de usuário
                                        "/swagger-ui/**",        // Swagger UI
                                        "/v3/api-docs/**"        // Documentação OpenAPI
                                ).permitAll()
                                .requestMatchers("/api/**").authenticated()  // Rotas privadas (exigem autenticação)
                                //.requestMatchers("/users").denyAll() // não entendi o porquê do uso
                )
                .cors(cors -> {}) //A classe WebConfig é quem definira as propriedade de Cors
                .build();
    }
}
