package projeto_pessoal.unittests.mocks;

import org.springframework.security.core.parameters.P;
import projeto_pessoal.domain.Person;
import projeto_pessoal.dto.PersonDTO;
import projeto_pessoal.enums.TipoGender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockPerson {

//    public Person mockEntity() {
//        return mockEntity(1);
//    }
//
//    public PersonDTO mockDTO() {
//        return mockDTO(1);
//    }

    public List<Person> mockEntityList() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            people.add(mockEntity(i));
        }
        return people;
    }

    public Person mockEntity(Integer i) {
        Person person = new Person();
        person.setId(i);
        person.setName("Nome teste" + i);
        person.setMathersName("Nome da mãe teste" + i);
        person.setGender(TipoGender.MALE);
        person.setCpf("11122233344" + i);
        person.setRg("99988877744" + i);
        person.setEmail("teste@gmail.com" + i);
        person.getPhonesNumber().addAll(Arrays.asList("8590000111" + i, "8590000222" + i));

        return person;
    }

    public List<PersonDTO> mockDTOList() {
        List<PersonDTO> people = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            people.add(mockDTO(i));
        }
        return people;
    }

    public PersonDTO mockDTO(Integer i) {
        PersonDTO person = new PersonDTO();
        person.setId(i);
        person.setName("Nome teste" + i);
        person.setMathersName("Nome da mãe teste" + i);
        person.setGender(TipoGender.MALE);
        person.setCpf("11122233344" + i);
        person.setRg("99988877744" + i);
        person.setEmail("teste@gmail.com" + i);
        person.getPhonesNumber().addAll(Arrays.asList("8590000111" + i, "8590000222" + i));

        return person;
    }
}
