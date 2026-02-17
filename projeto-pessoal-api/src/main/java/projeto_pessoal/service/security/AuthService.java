package projeto_pessoal.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projeto_pessoal.dto.security.AccountCredentialsDTO;
import projeto_pessoal.dto.security.TokenDTO;
import projeto_pessoal.repository.security.UserRepository;
import projeto_pessoal.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    public TokenDTO signIn(AccountCredentialsDTO credentials) {
        // 1. O "Segurança" checa a senha. Se estiver errada, para aqui.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );

        // 2. Busca os dados para o Token (Roles, etc)
        var user = repository.findByUsername(credentials.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        // 3. Gera o token e retorna o objeto puro
        return tokenProvider.createAcessToken(
                credentials.getUsername(),
                user.getRoles()
        );
    }



}
