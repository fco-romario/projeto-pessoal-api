package projeto_pessoal.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import projeto_pessoal.dto.security.TokenDTO;
import projeto_pessoal.exception.InvalidJwtAuthenticationException;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.issuer:projeto-pessoal-api}")
    private String issuer = "projeto-pessoal-api";

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-lenght:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    Algorithm algorithm = null;

    private final UserDetailsService _userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        _userDetailsService = userDetailsService;
    }

    @PostConstruct//Garante que o objeto está totalmente construído (dependências injetadas) antes de executar lógica;
    protected void init() { // Este método roda DEPOIS da injeção de dependências
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAcessToken(String username, List<String> roles) {
        Date now = new Date();
        Date accessValidity = new Date(now.getTime() + validityInMilliseconds);
        Date refreshValidity = new Date(now.getTime() + (validityInMilliseconds * 3));

        String accessToken = buildToken(username, roles, now, accessValidity);
        String refreshToken = buildToken(username, roles, now, refreshValidity);//todo ver depois se realmente precisa de roles no refreshtoken
        return new TokenDTO(username, true, now, accessValidity, accessToken, refreshToken);
    }

    private String buildToken(
            String username,
            List<String> roles,
            Date issuedAt,
            Date expiresAt
    ) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(username)
                .withClaim("roles", roles)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this._userDetailsService
                .loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(refreshTokenContainsBearer(bearerToken)) return bearerToken.substring("Bearer ".length());
        return null;
    }

    private static boolean refreshTokenContainsBearer(String refreshToken) {
        return StringUtils.isNotBlank(refreshToken) && refreshToken.startsWith("Bearer ");
    }

    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = decodedToken(token);

        try {
            if (decodedJWT.getExpiresAt().before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or Invalid JWT Token!");
        }
    }

    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        return verifier.verify(token);
    }
}