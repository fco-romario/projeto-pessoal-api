package projeto_pessoal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        if (_personRepository.existsByEmail(person.getEmail())) {
            throw new ObjectAlreadyExistsException("O E-mail " + person.getEmail() + " já está em uso.");
        }

        Person entity = _personRepository.save(person);
        return entity;
    }

    @Transactional
    public Person update(Person person) {
        if(!podeSalvarOEmail(person)) {
            throw new ObjectAlreadyExistsException("O E-mail " + person.getEmail() + " já está em uso.");
        }

        Person entity = findById(person.getId());

        entity.setName(person.getName());
        entity.setMathersName(person.getMathersName());
        entity.setGender(person.getGender());
        entity.setCpf(person.getCpf());
        entity.setRg(person.getRg());
        entity.setEmail(person.getEmail());

        entity.getPhonesNumber().clear();
        if(person.getPhonesNumber() != null) {
            entity.getPhonesNumber().addAll(person.getPhonesNumber());
        }

        entity.getAddresses().clear();
        if(person.getAddresses() != null) {
            person.getAddresses().forEach(address -> address.setPerson(person));
            entity.getAddresses().addAll(person.getAddresses());
        }

        entity.getCourses().clear();
        if(person.getCourses() != null) {
            person.getCourses().forEach(course -> course.setStudent(person));
            entity.getCourses().addAll(person.getCourses());
        }

        return _personRepository.save(entity);
    }

    private boolean podeSalvarOEmail(Person person) {
        Person entity = _personRepository.findByEmail(person.getEmail());

        return entity == null || entity.getId().equals(person.getId());
    }
}
