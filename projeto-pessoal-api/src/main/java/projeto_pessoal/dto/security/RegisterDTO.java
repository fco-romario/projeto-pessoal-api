package projeto_pessoal.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class RegisterDTO {

    @NotBlank
    @Size(min = 4)
    private String username;

    @NotBlank @Size(min = 8)
    private String password;

    @NotBlank
    private String name;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}")
    private String cpf;

    public RegisterDTO() {}

    public RegisterDTO(String username, String password, String name, String email, String cpf) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDTO that = (RegisterDTO) o;
        return Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getCpf(), that.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getCpf());
    }
}
