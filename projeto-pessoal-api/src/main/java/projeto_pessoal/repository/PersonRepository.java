package projeto_pessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_pessoal.domain.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsByEmail(String email);

    Person findByEmail(String email);
}
