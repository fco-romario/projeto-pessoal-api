package projeto_pessoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto_pessoal.domain.Person;
import projeto_pessoal.exception.ObjectAlreadyExistsException;
import projeto_pessoal.exception.ResourceNotFoundException;
import projeto_pessoal.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository _personRepository;

    public List<Person> findAll() {
        return _personRepository.findAll();
    }

    public Person findById(Integer id) {
        Person entity = _personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Objeto não encontrado de id: " + id + ", tipo: " + Person.class.getSimpleName()));
        return entity;
    }

    public Person create(Person person) {
        if(_personRepository.existsByEmail(person.getEmail())) {
            throw new ObjectAlreadyExistsException("O E-mail " + person.getEmail() + " já está em uso.");
        }

        Person entity = _personRepository.save(person);
        return entity;
    }
}
