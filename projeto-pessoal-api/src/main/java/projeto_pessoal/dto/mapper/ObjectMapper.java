package projeto_pessoal.dto.mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapper {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
