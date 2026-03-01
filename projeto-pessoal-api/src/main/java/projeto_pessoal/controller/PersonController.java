package projeto_pessoal.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import projeto_pessoal.controller.docs.PersonControllerDocs;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projeto_pessoal.dto.PersonDTO;
import projeto_pessoal.service.PersonService;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api/v1/persons")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonService _personService;

    @Override
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDTO> entityList = _personService.findAll();

        return ResponseEntity.ok().body(entityList);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable @NotNull @Positive Integer id) {
        PersonDTO entity = _personService.findById(id);
        
        return ResponseEntity.ok().body(entity);
    }

    @Override
    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody @Valid @NotNull PersonDTO person) {
        PersonDTO entity = _personService.create(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @Override
    @PutMapping
    public ResponseEntity<PersonDTO> update(@RequestBody @Valid @NotNull PersonDTO person) {
        PersonDTO entity = _personService.update(person);

        return ResponseEntity.ok(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") @NotNull @Positive Integer id) {
        _personService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
