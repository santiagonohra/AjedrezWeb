package usa.ajedrez.fichas;

public enum TipoFicha {
    REY("rey"), REINA("r"), TORRE("t"), CABALLO("c"), ALFIL("a"), PEON("p");

    private String valor;

    TipoFicha(String valor) {
        this.valor = valor;

    }

    @Override
    public String toString() {
        return this.valor;
    }
    public static TipoFicha fromString(String valor){
        for (TipoFicha ficha : TipoFicha.values()) {
            if (ficha.valor.equalsIgnoreCase(valor)){
                return ficha;
            }
        }
        return null;
    }
}
