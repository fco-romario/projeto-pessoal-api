package projeto_pessoal.dto;

import jakarta.persistence.*;
import projeto_pessoal.domain.Address;
import projeto_pessoal.domain.Course;
import projeto_pessoal.enums.TipoGender;

import java.io.Serializable;
import java.util.*;

public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String mathersName;
    private Integer gender;
    private String cpf;
    private String rg;
    private String email;
    private Set<String> phonesNumber = new HashSet<>();
    private List<AddressDTO> addresses = new ArrayList<>();
    private List<CourseDTO> courses = new ArrayList<>();

    public PersonDTO() {}

    public PersonDTO(Integer id, String name, String mathersName, TipoGender gender, String cpf, String rg, String email) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getName(), personDTO.getName()) && Objects.equals(getMathersName(), personDTO.getMathersName()) && getGender() == personDTO.getGender() && Objects.equals(getCpf(), personDTO.getCpf()) && Objects.equals(getRg(), personDTO.getRg()) && Objects.equals(getEmail(), personDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMathersName(), getGender(), getCpf(), getRg(), getEmail());
    }
}
