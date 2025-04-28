import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Models.*;
import sistema.Proveedor;
import sistema.Usuario;
import sistema.SolicitudDeCompra;
import views.ShowConsole;
import controller.BusquedaBinaria;
import java.util.GregorianCalendar;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Proveedor> proveedores = new ArrayList<>();
    static List<SolicitudDeCompra> solicitudes = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();
    static int contadorDeSolicitudes = 1;
    static List<Producto> productos = new ArrayList<>();
    static ShowConsole showConsole = new ShowConsole(productos);
    static BusquedaBinaria binaria = new BusquedaBinaria();

    public static void main(String[] args) {

        ShowConsole showConsole = new ShowConsole(productos);

        boolean continuar = true;

        while (continuar) {
            int opcion = showConsole.showMenu();
            switch (opcion) {
                case 1: // agrega usuario a la lista de usuarios
                    Usuario usuario = showConsole.registrarUsuario();
                    usuarios.add(usuario);
                    break;
                case 2: //Al ingresar un proveedor, se agrega a la lista de proveedores
                    Proveedor proveedor = showConsole.registrarProveedor();
                    proveedores.add(proveedor);
                    break;
                case 3:
                    showConsole.registrarProducto();
                    break;
                case 4:
                    registrarSolicitudCompra();
                    break;
                case 5: //Muestra los usuarios ingresados
                    if (usuarios.isEmpty()) {
                        System.out.println("No existen usuarios registrados");
                    } else {
                        for (Usuario u : usuarios) {
                            System.out.println(u);
                        }
                    }
                    break;
                case 6: //Muestra la lista de proveedores
                    if (proveedores.isEmpty()) {
                        System.out.println("No existen proveedores registrados");
                    } else {
                        for (Proveedor pro : proveedores) {
                            System.out.println(pro);
                        }
                    }
                    break;
                case 7:
                    if (productos.isEmpty()) {
                        System.out.println("No existen productos registrados");
                    } else {
                        for (Producto producto : productos) {
                            System.out.println(producto);
                        }
                    }
                    break;
                case 8:
                    if (solicitudes.isEmpty()) {
                        System.out.println("No existe solicitud de compra registrada");
                    } else {
                        for (SolicitudDeCompra sc : solicitudes) {
                            System.out.println(sc);
                        }
                    }
                    break;
                case 9:
                    buscarProveedorID();
                    break;
                case 10:
                    buscarProductoNombre();
                    break;
                case 11:
                    buscarSolicitudID();
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


    public static void buscarProveedorID(){
        if (proveedores.isEmpty()) {
            System.out.println("No existen proveedores registrados");
        } else {
            String idBuscado = showConsole.iputIDProveedor();
            int index = binaria.buscarProveedorID(proveedores, idBuscado);

            if (index == 0) {
                System.out.println("Proveedor encontrado");
                System.out.println(proveedores.get(index));
            } else {
                System.out.println("Proveedor no registrado");
            }
        }
    }

    public static void buscarProductoNombre(){
        if (productos.isEmpty()) {
            System.out.println("No existen productos registrados");
        } else {
            String nombreBuscado = showConsole.iputNombreProducto();
            int index = binaria.buscarProductoNombre(productos, nombreBuscado);

            if (index == 0) {
                System.out.println("Producto encontrado");
                System.out.println(productos.get(index));
            } else {
                System.out.println("Producto no registrado");
            }
        }
    }

    public static void buscarSolicitudID(){
        if (solicitudes.isEmpty()) {
            System.out.println("No existen solicitudes de compra");
        } else {
            String idBuscado = showConsole.iputIDSolicitud();
            int index = binaria.buscarSolicitudID(solicitudes, idBuscado);

            if (index == 0) {
                System.out.println("Solicitud encontrada");
                System.out.println(solicitudes.get(index));
            } else {
                System.out.println("Solicitud no registrada");
            }
        }
    }

    //4
    public static void registrarSolicitudCompra() {
        ShowConsole showConsole = new ShowConsole(productos);
        String nombreSolicitante= showConsole.pedirNombreSolicitante();
        Usuario usuario = null;

        for(Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombreSolicitante)) {
                usuario = u;
                break;
            }
        }
        if (usuario == null) {
            System.out.println("No existen usuarios registrados");
            return;
        }

        //crea un nuevo número de solicitud
        String numeroSolicitud = "Solicitud Compra" + String.format("%03d", contadorDeSolicitudes);//convierte a un numero de minimo 3 digitos y pone 0's adelante
        contadorDeSolicitudes++; //sube el contador para la proxima solicitud

        //crea la nueva solicitud
        SolicitudDeCompra nuevaSolicitud = new SolicitudDeCompra();
        nuevaSolicitud.setNumeroSolicitud(numeroSolicitud);
        nuevaSolicitud.setUsuario(usuario);
        nuevaSolicitud.setEstado(enums.Estado.SOLICITADA);
        nuevaSolicitud.setFechaSolicitud(new GregorianCalendar());

        //agregar productos
        boolean seguirAgregando = true;
        while (seguirAgregando) {
            System.out.println("Ingrese el nombre del producto que desea agregar:");
            String nombreProducto = scanner.nextLine();

            //busca el producto en la lista
            Producto productoSeleccionado = null;
            for (Producto p : productos) {
                if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                    productoSeleccionado = p;
                    break;
                }
            }

            if (productoSeleccionado == null) {
                System.out.println("No se encontró el producto");
            } else {
                int cantidad=showConsole.pedirCantidadProducto();

                //crea un detalle de solicitud y agregarlo
                DetalleSolicitud detalle = new DetalleSolicitud(productoSeleccionado, cantidad, "Justificación no disponible");
                nuevaSolicitud.agregarDetalle(detalle);
                System.out.println("Producto agregado correctamente a la solicitud.");
            }

            seguirAgregando = showConsole.deseaAgregarProducto();
        }

        solicitudes.add(nuevaSolicitud);

        System.out.println("Solicitud registrada con éxito. numero de solicitud: " + numeroSolicitud);
    }


    public static void calcularTotalSolicitud() {
        ShowConsole showConsole = new ShowConsole(productos);
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
        ShowConsole showConsole = new ShowConsole(productos);
        String numero = showConsole.pedirNumeroSolicitud(); //pedimos numero de solicitud

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

        boolean aprobar = showConsole.pedirDecisionAprobacion(); //se aprueba o no

        solicitudEncontrada.aprobarEstado(evaluador, aprobar); //se llama al metodo de SolicitudDeCompra
    }

}