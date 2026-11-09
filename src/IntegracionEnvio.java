public class IntegracionEnvio extends Integracion {

    public IntegracionEnvio(int id, String nombre, boolean conectada,
                            double latencia, String configuracion) {
        super(id, nombre, conectada, latencia, configuracion);
    }

    public String rastrear(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            registrarEvento("Rastreo rechazado: código vacío.");
            return "El código de rastreo no puede estar vacío.";
        }
        if (!isConectada()) {
            registrarEvento("Rastreo no realizado: integración desconectada.");
            return "No se puede rastrear: el proveedor está desconectado.";
        }

        String resultado = "Paquete " + codigo.trim()
                + ": en tránsito (simulación de " + getNombre() + ").";
        registrarEvento(resultado);
        return resultado;
    }
}