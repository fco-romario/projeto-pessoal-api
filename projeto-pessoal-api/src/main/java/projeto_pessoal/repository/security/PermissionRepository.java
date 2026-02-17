package projeto_pessoal.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto_pessoal.domain.security.Permission;
import projeto_pessoal.domain.security.User;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
