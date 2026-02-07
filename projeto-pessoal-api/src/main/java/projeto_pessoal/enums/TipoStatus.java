package projeto_pessoal.enums;

public enum TipoStatus {
    ANDAMENTO (1, "ANDAMENTO"),
    CONCLUIDO (2, "CONCLUÍDO");

    private final int code;
    private final String description;

    TipoStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TipoStatus toEnum(Integer code) {
        if(code == null) return null;

        for(TipoStatus x: TipoStatus.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
