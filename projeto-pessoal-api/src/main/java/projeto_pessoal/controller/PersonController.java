package projeto_pessoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_pessoal.dto.PersonDTO;
import projeto_pessoal.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/persons")
public class PersonController {

    @Autowired
    private PersonService _personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDTO> entityList = _personService.findAll();

        return ResponseEntity.ok().body(entityList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Integer id) {
        PersonDTO entity = _personService.findById(id);
        
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
        PersonDTO entity = _personService.create(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
        PersonDTO entity = _personService.update(person);

        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Integer id) {
        _personService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
