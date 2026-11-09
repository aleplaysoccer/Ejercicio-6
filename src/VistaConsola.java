import java.util.Scanner;

public class VistaConsola {

    private final Scanner scanner;

    public VistaConsola() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        mostrar("\nCatálogo de integraciones");
        mostrar("1. Listar integraciones");
        mostrar("2. Buscar integración por ID");
        mostrar("3. Ordenar por latencia");
        mostrar("4. Simular cobro");
        mostrar("5. Simular reintegro");
        mostrar("6. Generar factura simulada");
        mostrar("7. Salir");
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                mostrar("Ingresa un número entero válido.");
            }
        }
    }

    public double leerMonto(String mensaje) {
        while (true) {
            try {
                double monto = Double.parseDouble(leerTexto(mensaje));
                if (Double.isFinite(monto) && monto > 0) {
                    return monto;
                }
                mostrar("El monto debe ser finito y mayor que cero.");
            } catch (NumberFormatException e) {
                mostrar("Ingresa un monto válido usando punto decimal.");
            }
        }
    }

    public void mostrar(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarIntegraciones(Iterable<Integracion> integraciones) {
        if (integraciones == null) {
            mostrar("No hay integraciones para mostrar.");
            return;
        }

        boolean hayIntegraciones = false;
        for (Integracion integracion : integraciones) {
            mostrar(integracion.toString());
            hayIntegraciones = true;
        }

        if (!hayIntegraciones) {
            mostrar("No hay integraciones para mostrar.");
        }
    }
}