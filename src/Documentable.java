public interface Documentable {

    default String generarDocumento(String datos) {
        return "";
    }
}