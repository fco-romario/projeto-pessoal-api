package projeto_pessoal.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import projeto_pessoal.domain.security.User;
import projeto_pessoal.enums.TipoGender;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "pessoas")
public class Person extends Audit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Column(length = 150, nullable = false)
    private String name;

    //@NotBlank(message = "O nome da mãe é obrigatório")
    @Column(name = "mathers_name", length = 150, nullable = true)
    private String mathersName;

    //@NotNull(message = "O gênero deve ser informado")
    @Column(nullable = true)
    private Integer gender;

    //@NotBlank(message = "O CPF é obrigatório")
    //@Pattern(regexp = "\\d{11}",
    //        message = "O CPF deve conter exatamente 11 números, sem pontos ou hifens")
    @Column(length = 11, nullable = true)
    private String cpf;

    //@NotBlank(message = "O RG é obrigatório")
    @Column(length = 11, nullable = true)
    private String rg;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Column(length = 150, nullable = false, unique = true)
    private String email;

    //@NotEmpty(message = "Pelo menos um número de telefone deve ser informado")
    @ElementCollection
    @CollectionTable(name = "phones_numbers")
    private Set<String> phonesNumber = new HashSet<>();

    @Valid
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @Valid
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    //@NotNull(message = "O usuário é obrigatório")
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private User user;

    public Person() {}

    public Person(Integer id, String name, String mathersName, TipoGender gender, String cpf, String rg, String email) {
        this.id = id;
        this.name = name;
        this.mathersName = mathersName;
        this.gender = gender.getCode();
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

    public TipoGender getGender() {
        return TipoGender.toEnum(gender);
    }

    public void setGender(TipoGender gender) {
        this.gender = gender.getCode();
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        if(addresses != null) {
            addresses.forEach(address -> address.setPerson(this));
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        if (courses != null) {
            courses.forEach(course -> course.setStudent(this));
        }
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getName(), person.getName()) && Objects.equals(getMathersName(), person.getMathersName()) && Objects.equals(getGender(), person.getGender()) && Objects.equals(getCpf(), person.getCpf()) && Objects.equals(getRg(), person.getRg()) && Objects.equals(getEmail(), person.getEmail()) && Objects.equals(getPhonesNumber(), person.getPhonesNumber()) && Objects.equals(getAddresses(), person.getAddresses()) && Objects.equals(getCourses(), person.getCourses()) && Objects.equals(getUser(), person.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMathersName(), getGender(), getCpf(), getRg(), getEmail(), getPhonesNumber(), getAddresses(), getCourses(), getUser());
    }
}
