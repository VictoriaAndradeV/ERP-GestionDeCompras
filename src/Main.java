import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import Enums.UnidadDeMedida;
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
    static List<Producto> productos = new ArrayList<>();
    static List<SolicitudDeCompra> solicitudes = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();
    static int contadorDeSolicitudes = 1;

    public static void main(String[] args) {

        ShowConsole showConsole = new ShowConsole();
        BusquedaBinaria binaria = new BusquedaBinaria();

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
                case 3:
                    registrarProducto();
                    break;
                case 4:
                    registrarSolicitudCompra();
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
                case 9:
                    if(proveedores.isEmpty()) {
                        System.out.println("No existen proveedores registrados");
                    } else{
                        String idBuscado = showConsole.iputIDProveedor();
                        int index = binaria.buscarProveedorID(proveedores, idBuscado);

                        if(index == -1){
                            System.out.println("Proveedor encontrado");
                            System.out.println(proveedores.get(index));
                        }else{
                            System.out.println("Proveedor no registrado");
                        }
                    }
                    break;
                case 10:
                    break;
                case 11:
                    if(solicitudes.isEmpty()) {
                        System.out.println("No existen solicitudes de compra");
                    } else{
                        String idBuscado = showConsole.iputIDSolicitud();
                        int index = binaria.buscarSolicitudID(solicitudes, idBuscado);

                        if(index == -1){
                            System.out.println("Proveedor encontrado");
                            System.out.println(solicitudes.get(index));
                        }else{
                            System.out.println("Solicitud no registrada");
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
    //4
    public static void registrarSolicitudCompra() {
        ShowConsole showConsole = new ShowConsole();
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
    //--    nuevaSolicitud.setFechaSolicitud(new GregorianCalendar());

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
//3
    public static void registrarProducto() {
        System.out.println("Seleccione el tipo de producto:");
        System.out.println("1. Producto Comestible");
        System.out.println("2. Producto de Limpieza");
        System.out.println("3. Producto Tecnológico");
        int tipo = Integer.parseInt(scanner.nextLine());

        //datos generales
        System.out.println("Ingrese el ID del producto:");
        String id = scanner.nextLine();

        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese el precio unitario:");
        double precioUnitario = Double.parseDouble(scanner.nextLine());

        //opciones de unidad de medida
        System.out.println("Seleccione la unidad de medida:");
        for (UnidadDeMedida unidad : UnidadDeMedida.values()) { //se recorre enums
            System.out.println("- " + unidad);                  // y se imprime
        }
        String unidadIngresada = scanner.nextLine().toUpperCase();//convertimos a mayus ;ara que no haya errores
        UnidadDeMedida unidad = UnidadDeMedida.valueOf(unidadIngresada);//se convierte el txt en UnidadMedida

        Producto nuevoProducto = null;

        //pedimos datos especificos
        if (tipo == 1) {
            System.out.println("Ingrese el peso en kg o gramos:");
            double peso = Double.parseDouble(scanner.nextLine());

            System.out.println("Ingrese la fecha de caducidad (AÑO-MES-DIA):");
            LocalDate fechaCaducidad = LocalDate.parse(scanner.nextLine());

            System.out.println("Ingrese la fecha de elaboración (AÑO-MES-DIA):");
            LocalDate fechaElaboracion = LocalDate.parse(scanner.nextLine());

             nuevoProducto= new ProductoComestible(id, nombre, descripcion, precioUnitario, unidad, peso, fechaCaducidad, fechaElaboracion);

        } else if (tipo == 2) {
            System.out.println("Ingrese el volumen en litros:");
            double volumen = Double.parseDouble(scanner.nextLine());

            nuevoProducto= new ProductoLimpieza(id, nombre, descripcion, precioUnitario, unidad, volumen);

        } else if (tipo == 3) {
            System.out.println("Ingrese la garantía en meses:");
            int garantiaMeses = Integer.parseInt(scanner.nextLine());

            nuevoProducto = new ProductoTecnologico(id, nombre, descripcion, precioUnitario, unidad, garantiaMeses);
        } else {
            System.out.println("Opción invalida.");
            return;
        }

        //guarda en la lista
        productos.add(nuevoProducto);
        System.out.println("Producto registrado correctamente.");
    }

}