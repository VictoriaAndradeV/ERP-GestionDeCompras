import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sistema.Proveedor;
import sistema.Usuario;
import Models.Producto;
import sistema.SolicitudDeCompra;
import views.ShowConsole;

public class Main {
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
                case 15:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;
            }
        }


    }



}