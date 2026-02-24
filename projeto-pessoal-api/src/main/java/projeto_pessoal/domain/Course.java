package projeto_pessoal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import projeto_pessoal.enums.TipoCategory;
import projeto_pessoal.enums.TipoStatus;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course extends Audit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome do curso é obrigatório")
    @Size(min = 5, max = 150, message = "O nome do curso deve ter entre 5 e 150 caracteres")
    @Column(length = 100, nullable = false)
    private String name;

    @NotBlank(message = "A URL é obrigatória")
    @org.hibernate.validator.constraints.URL(message = "URL inválida")
    @Column(length = 200, nullable = false)
    private String url;

    @NotNull(message = "A categoria é obrigatória")
    //todo criar validator personalizado
    @Column(nullable = false)
    private Integer category;

    @NotNull(message = "O status é obrigatório")// Exemplo: 1-ANDAMENTO, 2-CONCLUÍDO
    //todo criar validator personalizado
    @Column(nullable = false)
    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "person_fk")
    private Person student;

    public Course() {}

    public Course(Integer id, String name, String url, TipoCategory category, TipoStatus status, Person student) {
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

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId()) && Objects.equals(getName(), course.getName()) && Objects.equals(getUrl(), course.getUrl()) && getCategory() == course.getCategory() && getStatus() == course.getStatus() && Objects.equals(getStudent(), course.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUrl(), getCategory(), getStatus(), getStudent());
    }
}
