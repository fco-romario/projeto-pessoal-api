package projeto_pessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_pessoal.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
