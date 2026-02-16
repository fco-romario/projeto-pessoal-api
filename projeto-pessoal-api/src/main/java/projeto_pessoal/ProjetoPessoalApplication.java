package projeto_pessoal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import projeto_pessoal.domain.Address;
import projeto_pessoal.domain.Course;
import projeto_pessoal.domain.Person;
import projeto_pessoal.enums.TipoCategory;
import projeto_pessoal.enums.TipoGender;
import projeto_pessoal.enums.TipoStatus;
import projeto_pessoal.repository.AddressRepository;
import projeto_pessoal.repository.CourseRepository;
import projeto_pessoal.repository.PersonRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ProjetoPessoalApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository _personRepository;
    @Autowired
    private AddressRepository _addressRepository;
    @Autowired
    private CourseRepository _courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPessoalApplication.class, args);
        generateHashedPassword();
	}

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person(null, "Romário Alves de Lima", "Maria alves", TipoGender.MALE, "11122233344", "99988877744", "test@gmail.com");
        person.getPhonesNumber().addAll(Arrays.asList("85900001111", "85900002222"));

        Address a1 = new Address(null, "41180083", "Rua da Lagoa", "Saboeiro", "15b", "Proximo a farmácia", person);
        Address a2 = new Address(null, "49096267", "Rua N", "Jabotiana", "1000b", "Proximo a padaria", person);

        person.getAddresses().addAll(Arrays.asList(a1, a2));
        Course c1 = new Course(null,"Curso de Angular para iniciantes", "https://www.youtube.com", TipoCategory.FRONT_END, TipoStatus.ANDAMENTO, person);
        Course c2 = new Course(null,"Curso de Java para iniciantes", "https://www.youtube.com", TipoCategory.BACK_END, TipoStatus.CONCLUIDO, person);

        person.getCourses().addAll(Arrays.asList(c1, c2));

        _personRepository.save(person);
        _addressRepository.saveAll(Arrays.asList(a1, a2));
        _courseRepository.saveAll(Arrays.asList(c1, c2));
    }

    private static void generateHashedPassword() {

        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
                "", 8, 185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        var pass1 = passwordEncoder.encode("admin123");
        var pass2 = passwordEncoder.encode("admin234");

        System.out.println(pass1);
        System.out.println(pass2);
    }
}
