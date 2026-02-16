package projeto_pessoal.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projeto_pessoal.repository.security.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository _repository;

    public UserService(UserRepository repository) {
        this._repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return _repository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(//todo testar se precisa criar um handler para essa exception
                                "Username " + username + " not found!"
                        ));
    }
}
