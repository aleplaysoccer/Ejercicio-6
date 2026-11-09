public class IntegracionPago extends Integracion {

    private final String medioPago;

    public IntegracionPago(int id, String nombre, boolean conectada,
                           double latencia, String configuracion,
                           String medioPago) {
        super(id, nombre, conectada, latencia, configuracion);

        if (medioPago == null || medioPago.trim().isEmpty()) {
            throw new IllegalArgumentException("El medio de pago no puede estar vacío.");
        }

        this.medioPago = medioPago.trim();
    }

    public String getMedioPago() {
        return medioPago;
    }

    @Override
    public boolean cobrar(double monto) {
        if (!Double.isFinite(monto) || monto <= 0) {
            registrarEvento("Cobro rechazado: monto inválido.");
            return false;
        }
        if (!isConectada()) {
            registrarEvento("Cobro no realizado: integración desconectada.");
            return false;
        }

        registrarEvento("Cobro realizado: Q" + monto + " mediante " + medioPago + ".");
        return true;
    }

    @Override
    public boolean reintegrar(double monto) {
        if (!Double.isFinite(monto) || monto <= 0) {
            registrarEvento("Reintegro rechazado: monto inválido.");
            return false;
        }
        if (!isConectada()) {
            registrarEvento("Reintegro no realizado: integración desconectada.");
            return false;
        }

        registrarEvento("Reintegro realizado: Q" + monto + " mediante " + medioPago + ".");
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", medio de pago: " + medioPago;
    }
}