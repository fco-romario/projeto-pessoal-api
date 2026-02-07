package projeto_pessoal.enums;

public enum TipoGender {
    MALE(1, "MASCULINO"),
    FEMALE(2, "FEMININO");

    private final int code;
    private final String description;

    TipoGender(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TipoGender toEnum(Integer code) {
        if(code == null) return null;

        for (TipoGender x: TipoGender.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
