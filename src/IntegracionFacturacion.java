public class IntegracionFacturacion extends Integracion {

    public IntegracionFacturacion(int id, String nombre, boolean conectada,
                                  double latencia, String configuracion) {
        super(id, nombre, conectada, latencia, configuracion);
    }

    @Override
    public String generarDocumento(String datos) {
        if (datos == null || datos.trim().isEmpty()) {
            registrarEvento("Documento rechazado: datos vacíos.");
            return "";
        }
        if (!isConectada()) {
            registrarEvento("Documento no generado: integración desconectada.");
            return "";
        }

        String datosXml = datos.trim()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");

        String proveedorXml = getNombre()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");

        String documento = "<Factura simulada=\"true\">\n"
                + "  <Proveedor>" + proveedorXml + "</Proveedor>\n"
                + "  <Datos>" + datosXml + "</Datos>\n"
                + "</Factura>";

        registrarEvento("Documento de facturación simulado generado.");
        return documento;
    }
}