package projeto_pessoal.security.jwt;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.tokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            var token = tokenProvider.resolveToken((HttpServletRequest) servletRequest);

            if (StringUtils.isNotBlank(token) && tokenProvider.validateToken(token)) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (com.auth0.jwt.exceptions.TokenExpiredException e) {
            // Se o token expirou, retornamos 401 para o Angular saber que deve tentar o Refresh
            handleException(response, "Token expired", e.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
        } catch (Exception e) {
            // Para qualquer outro erro de autenticação (token malformado, etc)
            handleException(response, "Invalid token", e.getMessage(), HttpServletResponse.SC_FORBIDDEN);
        }
    }

    // Método auxiliar para montar o JSON de erro
    private void handleException(HttpServletResponse response, String message, String details, int status) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");

        String json = String.format(
                "{\"timeStamp\": \"%s\", \"message\": \"%s\", \"details\": \"%s\"}",
                java.time.LocalDateTime.now(), message, details
        );

        response.getWriter().write(json);
    }
}
