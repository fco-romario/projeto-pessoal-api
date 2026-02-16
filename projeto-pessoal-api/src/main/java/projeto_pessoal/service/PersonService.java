package projeto_pessoal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projeto_pessoal.domain.Address;
import projeto_pessoal.domain.Course;
import projeto_pessoal.domain.Person;
import projeto_pessoal.dto.PersonDTO;
import projeto_pessoal.exception.ObjectAlreadyExistsException;
import projeto_pessoal.exception.ResourceNotFoundException;
import projeto_pessoal.repository.PersonRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository _personRepository;

    @Autowired
    private ModelMapper _mapper;

    public List<PersonDTO> findAll() {
        return _personRepository.findAll()
                .stream()
                .map(entity -> _mapper.map(entity, PersonDTO.class))
                .toList();
    }

    public PersonDTO findById(Integer id) {
        Person entity = getPersonById(id);
        return _mapper.map(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO personDTO) {
        if (_personRepository.existsByEmail(personDTO.getEmail())) {
            throw new ObjectAlreadyExistsException("O E-mail " + personDTO.getEmail() + " já está em uso.");
        }

        Person entity = _personRepository.save(_mapper.map(personDTO, Person.class));
        return _mapper.map(entity, PersonDTO.class);
    }

    @Transactional
    public PersonDTO update(PersonDTO personDTO) {
        if(!podeSalvarOEmail(personDTO)) {
            throw new ObjectAlreadyExistsException("O E-mail " + personDTO.getEmail() + " já está em uso.");
        }

        Person entity = getPersonById(personDTO.getId());

        entity.setName(personDTO.getName());
        entity.setMathersName(personDTO.getMathersName());
        entity.setGender(personDTO.getGender());
        entity.setCpf(personDTO.getCpf());
        entity.setRg(personDTO.getRg());
        entity.setEmail(personDTO.getEmail());

        if(personDTO.getPhonesNumber() != null) {
            entity.getPhonesNumber().clear();
            entity.getPhonesNumber().addAll(personDTO.getPhonesNumber());
        }

        if(personDTO.getAddresses() != null) {
            //person.getAddresses().forEach(address -> address.setPerson(person));
            entity.getAddresses().clear();
            entity.getAddresses().addAll(
                    personDTO.getAddresses()
                            .stream()
                            .map(addresDTO -> _mapper.map(addresDTO, Address.class))
                            .toList()
            );
        }

        if(personDTO.getCourses() != null) {
            entity.getCourses().clear();
            //person.getCourses().forEach(course -> course.setStudent(person));
            entity.getCourses().addAll(
                    personDTO.getCourses()
                            .stream()
                            .map(courseDTO -> _mapper.map(courseDTO, Course.class))
                            .toList()
            );
        }

        return _mapper.map(_personRepository.save(entity), PersonDTO.class);
    }

    private boolean podeSalvarOEmail(PersonDTO person) {
        Person entity = _personRepository.findByEmail(person.getEmail());
        return entity == null || entity.getId().equals(person.getId());
    }

    public void remove(Integer id) {
        Person entity = getPersonById(id);
        _personRepository.delete(entity);
    }

    private Person getPersonById(Integer id) {
        return _personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Objeto não encontrado de id: " + id + ", tipo: " + Person.class.getSimpleName()));
    }
}
