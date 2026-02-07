package projeto_pessoal.domain;

import projeto_pessoal.enums.TipoGender;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person {
    private Integer id;
    private String name;
    private String mathersName;
    private TipoGender gender;
    private String cpf;
    private String rg;
    private String email;
    private Set<String> phonesNumber = new HashSet<>();
    private List<Address> addresses = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
}
