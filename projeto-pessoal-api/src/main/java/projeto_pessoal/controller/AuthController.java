package projeto_pessoal.controller;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import projeto_pessoal.dto.security.AccountCredentialsDTO;
import projeto_pessoal.service.security.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody AccountCredentialsDTO credentials) {
        try {
            var token = service.signIn(credentials);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
