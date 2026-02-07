package projeto_pessoal.enums;

public enum TipoCategory {
    FRONT_END(1, "Front-End"),
    BACK_END (2, "Back-End"),
    FULL_STACK (3, "Full-Stack");

    private final int code;
    private final String description;

    TipoCategory(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TipoCategory toEnum(Integer code) {
        if(code == null) return null;

        for (TipoCategory x: TipoCategory.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
