package projeto_pessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_pessoal.domain.Person;
import projeto_pessoal.repository.PersonRepository;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/clientes")
public class PersonController {

    @Autowired
    private PersonRepository _personRepository;

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        List<Person> entityList = _personRepository.findAll();

        if(entityList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(entityList);
    }
}
