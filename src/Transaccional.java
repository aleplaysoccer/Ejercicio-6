public interface Transaccional {

    default boolean cobrar(double monto) {
        return false;
    }

    default boolean reintegrar(double monto) {
        return false;
    }
}