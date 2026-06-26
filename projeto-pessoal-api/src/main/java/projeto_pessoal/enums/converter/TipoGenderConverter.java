//package projeto_pessoal.enums.converter;
//
//
//
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//import projeto_pessoal.enums.TipoGender;
//
//@Converter(autoApply = true) // Aplica automaticamente em todas as entidades que usam esse Enum
//public class TipoGenderConverter implements AttributeConverter<TipoGender, Integer> {
//
//    @Override
//    public Integer convertToDatabaseColumn(TipoGender attribute) {
//        return (attribute == null) ? null : attribute.getCode();
//    }
//
//    @Override
//    public TipoGender convertToEntityAttribute(Integer dbData) {
//        return TipoGender.toEnum(dbData);
//    }
//}
