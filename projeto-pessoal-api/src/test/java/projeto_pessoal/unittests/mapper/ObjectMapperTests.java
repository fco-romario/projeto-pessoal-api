package projeto_pessoal.unittests.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projeto_pessoal.domain.Person;
import projeto_pessoal.dto.PersonDTO;
import projeto_pessoal.enums.TipoGender;
import projeto_pessoal.unittests.mocks.MockPerson;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ObjectMapperTests {
    MockPerson inputObject;

    @Autowired
    private ModelMapper _mapper;

    @BeforeEach
    public void setup() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToDTOTest() {
        int index = 1;
        PersonDTO output = _mapper.map(inputObject.mockEntity(index), PersonDTO.class);

        assertEquals(index, output.getId());
        assertEquals("Nome teste" + index, output.getName());
        assertEquals("Nome da mãe teste" + index, output.getMathersName());
        assertEquals(TipoGender.MALE, output.getGender());
        assertEquals("11122233344" + index, output.getCpf());
        assertEquals("99988877744" + index, output.getRg());
        assertEquals("teste@gmail.com" + index, output.getEmail());
        assertThat(output.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + index, "8590000222" + index);
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<PersonDTO> outputList = inputObject.mockEntityList()
                .stream()
                .map(entity -> _mapper.map(entity, PersonDTO.class))
                .toList();

        PersonDTO outputOne =  outputList.get(1);
        int indexOne = 1;
        assertEquals(indexOne, outputOne.getId());
        assertEquals("Nome teste" + indexOne, outputOne.getName());
        assertEquals("Nome da mãe teste" + indexOne, outputOne.getMathersName());
        assertEquals(TipoGender.MALE, outputOne.getGender());
        assertEquals("11122233344" + indexOne, outputOne.getCpf());
        assertEquals("99988877744" + indexOne, outputOne.getRg());
        assertEquals("teste@gmail.com" + indexOne, outputOne.getEmail());
        assertThat(outputOne.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + indexOne, "8590000222" + indexOne);

        PersonDTO outputFour =  outputList.get(4);
        int indexFour = 4;
        assertEquals(indexFour, outputFour.getId());
        assertEquals("Nome teste" + indexFour, outputFour.getName());
        assertEquals("Nome da mãe teste" + indexFour, outputFour.getMathersName());
        assertEquals(TipoGender.MALE, outputFour.getGender());
        assertEquals("11122233344" + indexFour, outputFour.getCpf());
        assertEquals("99988877744" + indexFour, outputFour.getRg());
        assertEquals("teste@gmail.com" + indexFour, outputFour.getEmail());
        assertThat(outputFour.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + indexFour, "8590000222" + indexFour);

        PersonDTO outputSeven =  outputList.get(7);
        int indexSeven = 7;
        assertEquals(indexSeven, outputSeven.getId());
        assertEquals("Nome teste" + indexSeven, outputSeven.getName());
        assertEquals("Nome da mãe teste" + indexSeven, outputSeven.getMathersName());
        assertEquals(TipoGender.MALE, outputSeven.getGender());
        assertEquals("11122233344" + indexSeven, outputSeven.getCpf());
        assertEquals("99988877744" + indexSeven, outputSeven.getRg());
        assertEquals("teste@gmail.com" + indexSeven, outputSeven.getEmail());
        assertThat(outputSeven.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + indexSeven, "8590000222" + indexSeven);
    }

    @Test
    public void parseDTOToEntityTest() {
        int index = 1;
        Person output = _mapper.map(inputObject.mockDTO(index), Person.class);

        assertEquals(index, output.getId());
        assertEquals("Nome teste" + index, output.getName());
        assertEquals("Nome da mãe teste" + index, output.getMathersName());
        assertEquals(TipoGender.MALE, output.getGender());
        assertEquals("11122233344" + index, output.getCpf());
        assertEquals("99988877744" + index, output.getRg());
        assertEquals("teste@gmail.com" + index, output.getEmail());
        assertThat(output.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + index, "8590000222" + index);
    }

    @Test
    public void parseDTOListToEntityListTest() {
        List<Person> outputList = inputObject.mockDTOList()
                .stream()
                .map(entity -> _mapper.map(entity, Person.class))
                .toList();

        Person outputOne =  outputList.get(1);
        int indexOne = 1;
        assertEquals(indexOne, outputOne.getId());
        assertEquals("Nome teste" + indexOne, outputOne.getName());
        assertEquals("Nome da mãe teste" + indexOne, outputOne.getMathersName());
        assertEquals(TipoGender.MALE, outputOne.getGender());
        assertEquals("11122233344" + indexOne, outputOne.getCpf());
        assertEquals("99988877744" + indexOne, outputOne.getRg());
        assertEquals("teste@gmail.com" + indexOne, outputOne.getEmail());
        assertThat(outputOne.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + indexOne, "8590000222" + indexOne);

        Person outputFour =  outputList.get(4);
        int indexFour = 4;
        assertEquals(indexFour, outputFour.getId());
        assertEquals("Nome teste" + indexFour, outputFour.getName());
        assertEquals("Nome da mãe teste" + indexFour, outputFour.getMathersName());
        assertEquals(TipoGender.MALE, outputFour.getGender());
        assertEquals("11122233344" + indexFour, outputFour.getCpf());
        assertEquals("99988877744" + indexFour, outputFour.getRg());
        assertEquals("teste@gmail.com" + indexFour, outputFour.getEmail());
        assertThat(outputFour.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + indexFour, "8590000222" + indexFour);

        Person outputSeven =  outputList.get(7);
        int indexSeven = 7;
        assertEquals(indexSeven, outputSeven.getId());
        assertEquals("Nome teste" + indexSeven, outputSeven.getName());
        assertEquals("Nome da mãe teste" + indexSeven, outputSeven.getMathersName());
        assertEquals(TipoGender.MALE, outputSeven.getGender());
        assertEquals("11122233344" + indexSeven, outputSeven.getCpf());
        assertEquals("99988877744" + indexSeven, outputSeven.getRg());
        assertEquals("teste@gmail.com" + indexSeven, outputSeven.getEmail());
        assertThat(outputSeven.getPhonesNumber())
                .hasSize(2)
                .containsExactlyInAnyOrder("8590000111" + indexSeven, "8590000222" + indexSeven);
    }
}
