package projeto_pessoal.service.security;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projeto_pessoal.domain.Person;
import projeto_pessoal.domain.security.Permission;
import projeto_pessoal.domain.security.User;
import projeto_pessoal.dto.security.AccountCredentialsDTO;
import projeto_pessoal.dto.security.RegisterDTO;
import projeto_pessoal.dto.security.TokenDTO;
import projeto_pessoal.exception.ObjectAlreadyExistsException;
import projeto_pessoal.exception.RequiredObjectIsNullException;
import projeto_pessoal.repository.PersonRepository;
import projeto_pessoal.repository.security.UserRepository;
import projeto_pessoal.security.jwt.JwtTokenProvider;


@Service
public class AuthService {

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    //todo usar injeção pelo contrutor
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PersonRepository personRepository;

    public TokenDTO signIn(AccountCredentialsDTO credentials) {
        // 1. O "Segurança" checa a senha. Se estiver errada, para aqui.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );

        // 2. Busca os dados para o Token (Roles, etc)
        var user = repository.findByUsername(credentials.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        // 3. Gera o token e retorna o objeto puro
        return tokenProvider.createAccessToken(
                credentials.getUsername(),
                user.getRoles()
        );
    }

    @Transactional
    public AccountCredentialsDTO create(RegisterDTO registerDTO) {
        if (registerDTO == null) throw new RequiredObjectIsNullException();

        if (repository.findByUsername(registerDTO.getUsername()).isPresent())
            throw new ObjectAlreadyExistsException("User already exists!");

        logger.info("Creating a new Person");
        var person = new Person();
        person.setName(registerDTO.getName());
        person.setEmail(registerDTO.getEmail());
        person.setCpf(registerDTO.getCpf());
        //todo verificar se ao criar precisa de mais dados

        person = personRepository.save(person);

        logger.info("Creating a new User");
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.getPermissions().add(new Permission(1L, "ADMIN"));//mock
        user.setPerson(person);

        user = repository.save(user);

        return new AccountCredentialsDTO(user.getUsername(), user.getPassword());
    }
    
//    public AccountCredentialsDTO create(AccountCredentialsDTO user) {
//        if(user == null) throw new RequiredObjectIsNullException();
//
//        if (repository.findByUsername(user.getUsername()).isPresent())
//            throw new ObjectAlreadyExistsException("User already exists!");
//
//
//        logger.info("Creating a new User");
//        var entity = new User();
//        entity.setUsername(user.getUsername());
//        entity.setPassword(generateHashedPassword(user.getPassword()));
//        entity.setAccountNonExpired(true);
//        entity.setAccountNonLocked(true);
//        entity.setCredentialsNonExpired(true);
//        entity.setEnabled(true);
//
//        //return parseObject(repository.save(entity), AccountCredentialsDTO.class);
//        entity =  repository.save(entity);
//        return new AccountCredentialsDTO(entity.getUsername(),entity.getPassword());
//    }

//    private String generateHashedPassword(String password) {
//
//        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
//                "", 8, 185000,
//                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("pbkdf2", pbkdf2Encoder);
//        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//
//        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
//        return passwordEncoder.encode(password);
//    }

    public TokenDTO refreshToken(String username, String refreshToken) {
        var user =  repository.findByUsername(username);
        TokenDTO tokenDTO;
        if(user != null) {
            tokenDTO = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username "+ username + " not found!");
        }
        return tokenDTO;
    }
}