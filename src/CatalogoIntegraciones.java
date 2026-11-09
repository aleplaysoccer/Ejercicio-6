import java.util.ArrayList;
import java.util.Collections;

public class CatalogoIntegraciones {

    private final ArrayList<Integracion> integraciones;

    public CatalogoIntegraciones() {
        integraciones = new ArrayList<>();
        cargarInicial();
    }

    private void cargarInicial() {
        agregar(new IntegracionPago(1, "Tarjetas Uno", true, 120,
                "modo=pruebas; moneda=GTQ", "tarjeta"));
        agregar(new IntegracionPago(2, "Tarjetas Dos", false, 180,
                "modo=pruebas; moneda=GTQ", "tarjeta"));
        agregar(new IntegracionPago(3, "Transferencias Uno", true, 95,
                "modo=pruebas; moneda=GTQ", "transferencia"));
        agregar(new IntegracionPago(4, "Billetera Uno", true, 60,
                "modo=pruebas; moneda=GTQ", "billetera digital"));

        agregar(new IntegracionEnvio(5, "Envíos Centro", true, 45,
                "modo=pruebas; zona=central"));
        agregar(new IntegracionEnvio(6, "Envíos Norte", true, 80,
                "modo=pruebas; zona=norte"));
        agregar(new IntegracionEnvio(7, "Envíos Sur", false, 220,
                "modo=pruebas; zona=sur"));

        agregar(new IntegracionFacturacion(8, "Certificador Uno", true, 150,
                "modo=pruebas; serie=A"));
        agregar(new IntegracionFacturacion(9, "Certificador Dos", true, 110,
                "modo=pruebas; serie=B"));
        agregar(new IntegracionFacturacion(10, "Certificador Tres", false, 200,
                "modo=pruebas; serie=C"));
    }

    public boolean agregar(Integracion integracion) {
        if (integracion == null || buscarPorId(integracion.getId()) != null) {
            return false;
        }

        integraciones.add(integracion);
        return true;
    }

    public Integracion buscarPorId(int id) {
        for (Integracion integracion : integraciones) {
            if (integracion.getId() == id) {
                return integracion;
            }
        }
        return null;
    }

    public Iterable<Integracion> getIntegraciones() {
        return Collections.unmodifiableList(integraciones);
    }

    public void ordenarPorLatencia() {
        Collections.sort(integraciones);
    }

    public boolean procesar(Transaccional proveedor, double monto) {
        if (proveedor == null) {
            return false;
        }
        return proveedor.cobrar(monto);
    }

    public String procesar(Documentable proveedor, String datos) {
        if (proveedor == null) {
            return "";
        }
        return proveedor.generarDocumento(datos);
    }

    public boolean reintegrar(Transaccional proveedor, double monto) {
        if (proveedor == null) {
            return false;
        }
        return proveedor.reintegrar(monto);
    }
}