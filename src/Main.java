public class Main {

    public static void main(String[] args) {
        CatalogoIntegraciones catalogo = new CatalogoIntegraciones();
        VistaConsola vista = new VistaConsola();
        Controlador controlador = new Controlador(catalogo, vista);
        controlador.iniciar();
    }
}