package projeto_pessoal.dto;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import projeto_pessoal.enums.TipoGender;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "O nome da mãe é obrigatório")
    private String mathersName;

    @NotNull(message = "O gênero deve ser informado")
    private TipoGender gender;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}",
            message = "O CPF deve conter exatamente 11 números, sem pontos ou hifens")
    private String cpf;

    @NotBlank(message = "O RG é obrigatório")
    private String rg;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotEmpty(message = "Pelo menos um número de telefone deve ser informado")
    private Set<String> phonesNumber = new HashSet<>();

    @Valid // Valida cada endereço dentro da lista
    private List<AddressDTO> addresses = new ArrayList<>();

    @Valid // Valida cada curso dentro da lista
    private List<CourseDTO> courses = new ArrayList<>();

//    @NotEmpty(message = "É necessário um suário")
//    private User user;
//    private LocalDateTime createdAt;
//    private String createdBy;
//    private LocalDateTime updatedAt;

    public PersonDTO() {}

    public PersonDTO(Integer id, String name, String mathersName, TipoGender gender, String cpf, String rg, String email) {
        this.id = id;
        this.name = name;
        this.mathersName = mathersName;
        this.gender = gender;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        //todo adicionar user
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMathersName() {
        return mathersName;
    }

    public void setMathersName(String mathersName) {
        this.mathersName = mathersName;
    }

//    public TipoGender getGender() {
//        return TipoGender.toEnum(gender);
//    }
//
//    public void setGender(TipoGender gender) {
//        this.gender = gender.getCode();
//    }

    public TipoGender getGender() {
        return gender;
    }

    public void setGender(TipoGender gender) {
        this.gender = gender;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getPhonesNumber() {
        return phonesNumber;
    }

    public void setPhonesNumber(Set<String> phonesNumber) {
        this.phonesNumber = phonesNumber;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
        if(addresses != null) {
            addresses.forEach(address -> address.setPerson(this));
        }
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
        if (courses != null) {
            courses.forEach(course -> course.setStudent(this));
        }
    }

//    public void setGender(Integer gender) {
//        this.gender = gender;
//    }

//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        PersonDTO personDTO = (PersonDTO) o;
//        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getName(), personDTO.getName()) && Objects.equals(getMathersName(), personDTO.getMathersName()) && getGender() == personDTO.getGender() && Objects.equals(getCpf(), personDTO.getCpf()) && Objects.equals(getRg(), personDTO.getRg()) && Objects.equals(getEmail(), personDTO.getEmail());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName(), getMathersName(), getGender(), getCpf(), getRg(), getEmail());
//    }
}
