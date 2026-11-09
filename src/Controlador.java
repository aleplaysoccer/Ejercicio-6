public class Controlador {

    private final CatalogoIntegraciones catalogo;
    private final VistaConsola vista;

    public Controlador(CatalogoIntegraciones catalogo, VistaConsola vista) {
        if (catalogo == null || vista == null) {
            throw new IllegalArgumentException("El catálogo y la vista son obligatorios.");
        }
        this.catalogo = catalogo;
        this.vista = vista;
    }

    public void iniciar() {
        int opcion;

        do {
            vista.mostrarMenu();
            opcion = vista.leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1:
                    listarIntegraciones();
                    break;
                case 2:
                    buscarIntegracion();
                    break;
                case 3:
                    ordenarIntegraciones();
                    break;
                case 4:
                    cobrar();
                    break;
                case 5:
                    reintegrar();
                    break;
                case 6:
                    generarDocumento();
                    break;
                case 7:
                    vista.mostrar("Programa finalizado.");
                    break;
                default:
                    vista.mostrar("Opción no válida.");
            }
        } while (opcion != 7);
    }

    private void listarIntegraciones() {
        vista.mostrar("Integraciones registradas:");
        vista.mostrarIntegraciones(catalogo.getIntegraciones());
    }

    private void buscarIntegracion() {
        int id = vista.leerEntero("ID de la integración: ");
        Integracion integracion = catalogo.buscarPorId(id);

        if (integracion == null) {
            vista.mostrar("No se encontró la integración.");
            return;
        }

        vista.mostrar(integracion.toString());
        if (!integracion.getHistorial().isEmpty()) {
            vista.mostrar("Historial de operaciones:");
            vista.mostrar(integracion.getHistorial());
        }
    }

    private void ordenarIntegraciones() {
        catalogo.ordenarPorLatencia();
        vista.mostrar("Catálogo ordenado de menor a mayor latencia.");
        listarIntegraciones();
    }

    private void cobrar() {
        int id = vista.leerEntero("ID de la integración: ");
        Integracion integracion = catalogo.buscarPorId(id);

        if (integracion == null) {
            vista.mostrar("No se encontró la integración.");
            return;
        }

        double monto = vista.leerMonto("Monto a cobrar (Q): ");
        if (catalogo.procesar(integracion, monto)) {
            vista.mostrar("Cobro simulado realizado correctamente.");
        } else {
            vista.mostrar("No se realizó el cobro. Verifica la conexión y si el proveedor admite cobros.");
        }
    }

    private void reintegrar() {
        int id = vista.leerEntero("ID de la integración: ");
        Integracion integracion = catalogo.buscarPorId(id);

        if (integracion == null) {
            vista.mostrar("No se encontró la integración.");
            return;
        }

        double monto = vista.leerMonto("Monto a reintegrar (Q): ");
        if (catalogo.reintegrar(integracion, monto)) {
            vista.mostrar("Reintegro simulado realizado correctamente.");
        } else {
            vista.mostrar("No se realizó el reintegro. Verifica la conexión y si el proveedor lo admite.");
        }
    }

    private void generarDocumento() {
        int id = vista.leerEntero("ID de la integración: ");
        Integracion integracion = catalogo.buscarPorId(id);

        if (integracion == null) {
            vista.mostrar("No se encontró la integración.");
            return;
        }

        String datos = vista.leerTexto("Datos para la factura: ");
        String documento = catalogo.procesar(integracion, datos);

        if (documento.isEmpty()) {
            vista.mostrar("No se generó el documento. Verifica los datos, la conexión y si el proveedor lo admite.");
        } else {
            vista.mostrar(documento);
        }
    }
}