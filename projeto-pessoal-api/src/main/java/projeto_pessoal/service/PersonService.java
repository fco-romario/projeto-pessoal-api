package projeto_pessoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto_pessoal.domain.Person;
import projeto_pessoal.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository _personRepository;

    public List<Person> findAll() {
        return _personRepository.findAll();
    }
}
