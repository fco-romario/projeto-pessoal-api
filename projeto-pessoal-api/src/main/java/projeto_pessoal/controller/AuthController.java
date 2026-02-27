package projeto_pessoal.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import projeto_pessoal.dto.security.AccountCredentialsDTO;
import projeto_pessoal.dto.security.RegisterDTO;
import projeto_pessoal.service.security.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

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
    public AccountCredentialsDTO create(@Valid @RequestBody RegisterDTO registerDTO) {
        return _service.create(registerDTO);
    }

    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(
            @PathVariable("username") @NotBlank(message = "Username is required") String username,
            @RequestHeader("Authorization") @NotBlank(message = "Refresh Token is required") String refreshToken) {
        try {
            var token = _service.refreshToken(username, refreshToken);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}


