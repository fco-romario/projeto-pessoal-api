package projeto_pessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_pessoal.domain.Person;
import projeto_pessoal.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/clientes")
public class PersonController {

    @Autowired
    private PersonService _personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        List<Person> entityList = _personService.findAll();

        return ResponseEntity.ok().body(entityList);
    }
}
