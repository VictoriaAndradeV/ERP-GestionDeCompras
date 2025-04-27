import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sistema.Proveedor;
import sistema.Usuario;
import Models.Producto;
import sistema.SolicitudDeCompra;
import views.ShowConsole;

public class Main {
    static ShowConsole console = new ShowConsole();
    static Scanner scanner = new Scanner(System.in);
    static List<Proveedor> proveedores = new ArrayList<>();
    static List<Producto> productos = new ArrayList<>();
    static List<SolicitudDeCompra> solicitudes = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        ShowConsole showConsole = new ShowConsole();
        boolean continuar = true;
        while (continuar) {
            int opcion = showConsole.showMenu();
            switch (opcion) {
                case 1:
                    Usuario usuario = showConsole.registrarUsuario();
                    usuarios.add(usuario);
                    break;
                case 2:
                    Proveedor proveedor = showConsole.registrarProveedor();
                    proveedores.add(proveedor);
                    break;
                case 5:
                    if(usuarios.isEmpty()) {
                        System.out.println("No existen usuarios registrados");
                    } else{
                        for(Usuario u : usuarios) {
                            System.out.println(u);
                        }
                    }
                    break;
                case 6:
                    if(proveedores.isEmpty()) {
                        System.out.println("No existen proveedores registrados");
                    } else {
                        for(Proveedor pro : proveedores) {
                            System.out.println(pro);
                        }
                    }
                    break;
                case 12:
                    aprobarRechazarSolicitud();
                    break;
                case 13:
                    calcularTotalSolicitud();
                    break;
                case 14:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;
            }
        }




    }

    public static void calcularTotalSolicitud() {
        ShowConsole showConsole = new ShowConsole();
        SolicitudDeCompra solicitudEncontrada = null;
        String numero = showConsole.pedirNumeroSolicitudCalcular();

        SolicitudDeCompra soicitudEcontrada= null;
        for(SolicitudDeCompra s : solicitudes) {
            if (s.getNumeroSolicitud().equalsIgnoreCase(numero)) {
                soicitudEcontrada = s;
                break;
            }
        }

        if (soicitudEcontrada == null) {
            showConsole.mostrarSolicitudNoEncontrada();
            return;
        }

        double total = solicitudEncontrada.calcularPrecio();
        showConsole.mostrarSolicitud(total);
    }

    public static void aprobarRechazarSolicitud() {
        ShowConsole showConsole = new ShowConsole();
        String numero = showConsole.pedirNumeroSolicitud(); // Pedir número de solicitud

        SolicitudDeCompra solicitudEncontrada = null;
        for (SolicitudDeCompra solicitud : solicitudes) {
            if (solicitud.getNumeroSolicitud().equalsIgnoreCase(numero)) {
                solicitudEncontrada = solicitud;
                break;
            }
        }

        if (solicitudEncontrada == null) {
            showConsole.mostrarSolicitudNoEncontrada();
            return;
        }

        System.out.println("Ingrese los datos del jefe evaluador:");
        Usuario evaluador = showConsole.registrarUsuario();

        boolean aprobar = showConsole.pedirDecisionAprobacion(); //pedir si aprueba o no

        solicitudEncontrada.aprobarEstado(evaluador, aprobar); //se llama al metodo de SolicitudDeCompra
    }
}