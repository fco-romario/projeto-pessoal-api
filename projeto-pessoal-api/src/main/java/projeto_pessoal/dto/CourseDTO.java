package projeto_pessoal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import projeto_pessoal.domain.Person;
import projeto_pessoal.enums.TipoCategory;
import projeto_pessoal.enums.TipoStatus;

import java.io.Serializable;
import java.util.Objects;

public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String url;
    private Integer category;
    private Integer status;

    @JsonIgnore
    private PersonDTO student;

    public CourseDTO() {}

    public CourseDTO(Integer id, String name, String url, TipoCategory category, TipoStatus status, PersonDTO student) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.category = category.getCode();
        this.status = status.getCode();
        this.student = student;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TipoCategory getCategory() {
        return TipoCategory.toEnum(category);
    }

    public void setCategory(TipoCategory category) {
        this.category = category.getCode();
    }

    public TipoStatus getStatus() {
        return TipoStatus.toEnum(status);
    }

    public void setStatus(TipoStatus status) {
        this.status = status.getCode();
    }

    public PersonDTO getStudent() {
        return student;
    }

    public void setStudent(PersonDTO student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourseDTO course = (CourseDTO) o;
        return Objects.equals(getId(), course.getId()) && Objects.equals(getName(), course.getName()) && Objects.equals(getUrl(), course.getUrl()) && getCategory() == course.getCategory() && getStatus() == course.getStatus() && Objects.equals(getStudent(), course.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUrl(), getCategory(), getStatus(), getStudent());
    }
}
