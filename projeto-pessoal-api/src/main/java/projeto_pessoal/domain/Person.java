package projeto_pessoal.domain;

import jakarta.persistence.*;
import projeto_pessoal.enums.TipoGender;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "pessoas")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String mathersName;

    @Column(nullable = false)
    private Integer gender;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length = 11, nullable = false)
    private String rg;

    @Column(length = 150, nullable = false)
    private String email;

    @ElementCollection
    @CollectionTable(name = "telefones")
    private Set<String> phonesNumber = new HashSet<>();

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Course> courses = new ArrayList<>();

    public Person() {}

    public Person(Integer id, String name, String mathersName, TipoGender gender, String cpf, String rg, String email) {
        this.id = id;
        this.name = name;
        this.mathersName = mathersName;
        this.gender = gender.getCode();
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
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
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getName(), person.getName()) && Objects.equals(getMathersName(), person.getMathersName()) && getGender() == person.getGender() && Objects.equals(getCpf(), person.getCpf()) && Objects.equals(getRg(), person.getRg()) && Objects.equals(getEmail(), person.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMathersName(), getGender(), getCpf(), getRg(), getEmail());
    }
}
