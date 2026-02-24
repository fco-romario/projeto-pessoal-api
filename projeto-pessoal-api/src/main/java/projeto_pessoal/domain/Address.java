package projeto_pessoal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address extends Audit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}",
            message = "O CEP deve conter exatamente 8 números, sem hifens ou espaços")
    @Column(length = 8, nullable = false)
    private String cep;

    @NotBlank(message = "O logradouro é obrigatório")
    @Size(min = 3, max = 150, message = "O logradouro deve ter entre 3 e 150 caracteres")
    @Column(length = 150, nullable = false)
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório")
    @Size(min = 2, max = 100, message = "O bairro deve ter entre 2 e 100 caracteres")
    @Column(length = 150, nullable = false)
    private String bairro;

    @NotBlank(message = "O número é obrigatório")
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres")
    @Column(length = 30, nullable = false)
    private String numero;

    @Size(max = 150, message = "O complemento deve ter no máximo 100 caracteres")
    @Column(length = 150, nullable = false)
    private String complemento;

    @ManyToOne()
    @JoinColumn(name = "person_fk")
    private  Person person;

    public Address() {}

    public Address(Integer id, String cep, String logradouro, String bairro, String numero, String complemento, Person person) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId()) && Objects.equals(getCep(), address.getCep()) && Objects.equals(getLogradouro(), address.getLogradouro()) && Objects.equals(getBairro(), address.getBairro()) && Objects.equals(getNumero(), address.getNumero()) && Objects.equals(getComplemento(), address.getComplemento()) && Objects.equals(getPerson(), address.getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCep(), getLogradouro(), getBairro(), getNumero(), getComplemento(), getPerson());
    }
}
