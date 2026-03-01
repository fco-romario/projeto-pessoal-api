package projeto_pessoal.unittests.service;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import projeto_pessoal.domain.Person;
import projeto_pessoal.dto.PersonDTO;
import projeto_pessoal.enums.TipoGender;
import projeto_pessoal.repository.PersonRepository;
import projeto_pessoal.service.PersonService;
import projeto_pessoal.unittests.mocks.MockPerson;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    @Spy // O Spy cria uma instância real do ModelMapper e a injeta no Service
    private ModelMapper mapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        //MockitoAnnotations.openMocks(this); // Faz o mesmo papel do @ExtendWith(MockitoExtension.class)
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        int index = 1;
        Person person = input.mockEntity(index);
        person.setId(index);

        when(repository.findById(index)).thenReturn(Optional.of(person));

        var result = service.findById(index);

        assertNotNull(result);

        assertEquals(index, result.getId());
        assertEquals("Nome teste" + index, result.getName());
        assertEquals("Nome da mãe teste" + index, result.getMathersName());
        assertEquals(TipoGender.MALE, result.getGender());
        assertEquals("11122233344" + index, result.getCpf());
        assertEquals("99988877744" + index, result.getRg());
        assertEquals("teste@gmail.com" + index, result.getEmail());
        assertThat(result.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + index, "8590000222" + index);

        verify(repository, times(1)).findById(index);
    }

    @Test
    void create() {
        int index = 1;
        Person person = input.mockEntity(index);
        Person persisted = input.mockEntity(index);
        //person.setId(null);

        PersonDTO dto = input.mockDTO(index);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);

        assertEquals(index, result.getId());
        assertEquals("Nome teste" + index, result.getName());
        assertEquals("Nome da mãe teste" + index, result.getMathersName());
        assertEquals(TipoGender.MALE, result.getGender());
        assertEquals("11122233344" + index, result.getCpf());
        assertEquals("99988877744" + index, result.getRg());
        assertEquals("teste@gmail.com" + index, result.getEmail());
        assertThat(result.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + index, "8590000222" + index);

        verify(repository, times(1)).save(person);
    }

    @Test
    void update() {
        int index = 1;
        Person person = input.mockEntity(index);
        Person updated = input.mockEntity(index);

        PersonDTO dto = input.mockDTO(index);

        when(repository.findById(index)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(updated);

        var result = service.update(dto);

        assertEquals(index, result.getId());
        assertEquals("Nome teste" + index, result.getName());
        assertEquals("Nome da mãe teste" + index, result.getMathersName());
        assertEquals(TipoGender.MALE, result.getGender());
        assertEquals("11122233344" + index, result.getCpf());
        assertEquals("99988877744" + index, result.getRg());
        assertEquals("teste@gmail.com" + index, result.getEmail());
        assertThat(result.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + index, "8590000222" + index);

        verify(repository, times(1)).save(person);
        verify(repository, times(1)).findById(index);
    }

    @Test
    void remove() {
        var index = 1;
        Person person = input.mockEntity(index);

        when(repository.findById(index)).thenReturn(Optional.of(person));

        service.remove(person.getId());

        verify(repository, times(1)).findById(person.getId());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }
}