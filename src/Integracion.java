public abstract class Integracion
        implements Transaccional, Documentable, Auditable,
        Comparable<Integracion> {

    private final int id;
    private final String nombre;
    private boolean conectada;
    private double latencia;
    private String configuracion;
    private String historial;

    protected Integracion(int id, String nombre, boolean conectada,
                          double latencia, String configuracion) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!Double.isFinite(latencia) || latencia < 0) {
            throw new IllegalArgumentException("La latencia debe ser finita y no negativa.");
        }
        if (configuracion == null || configuracion.trim().isEmpty()) {
            throw new IllegalArgumentException("La configuración no puede estar vacía.");
        }

        this.id = id;
        this.nombre = nombre.trim();
        this.conectada = conectada;
        this.latencia = latencia;
        this.configuracion = configuracion.trim();
        this.historial = "";
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isConectada() {
        return conectada;
    }

    public double getLatencia() {
        return latencia;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public String getHistorial() {
        return historial;
    }

    public void setConectada(boolean conectada) {
        this.conectada = conectada;
        registrarEvento("Conexión: " + (conectada ? "conectada" : "desconectada"));
    }

    public void setLatencia(double latencia) {
        if (!Double.isFinite(latencia) || latencia < 0) {
            throw new IllegalArgumentException("La latencia debe ser finita y no negativa.");
        }
        this.latencia = latencia;
        registrarEvento("Latencia actualizada: " + latencia + " ms");
    }

    public void setConfiguracion(String configuracion) {
        if (configuracion == null || configuracion.trim().isEmpty()) {
            throw new IllegalArgumentException("La configuración no puede estar vacía.");
        }
        this.configuracion = configuracion.trim();
        registrarEvento("Configuración actualizada.");
    }

    @Override
    public void registrarEvento(String mensaje) {
        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío.");
        }
        historial += mensaje.trim() + System.lineSeparator();
    }

    @Override
    public int compareTo(Integracion otra) {
        return Double.compare(latencia, otra.latencia);
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (otro == null || getClass() != otro.getClass()) {
            return false;
        }
        Integracion integracion = (Integracion) otro;
        return id == integracion.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", nombre: " + nombre
                + ", conexión: " + (conectada ? "Conectada" : "Desconectada")
                + ", latencia: " + latencia + " ms"
                + ", configuración: " + configuracion;
    }
}