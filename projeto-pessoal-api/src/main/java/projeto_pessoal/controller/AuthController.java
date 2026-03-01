package projeto_pessoal.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import projeto_pessoal.controller.docs.AuthControllerDoc;
import projeto_pessoal.dto.security.AccountCredentialsDTO;
import projeto_pessoal.dto.security.RegisterDTO;
import projeto_pessoal.service.security.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDoc {

    @Autowired
    private AuthService _service;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody AccountCredentialsDTO credentials) {
        try {
            var token = _service.signIn(credentials);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<AccountCredentialsDTO> create(@Valid @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_service.create(registerDTO));
    }

    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(
            @PathVariable("username") String username,
            @RequestHeader("Authorization") String refreshToken) {
        try {
            var token = _service.refreshToken(username, refreshToken);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}


