import java.util.Scanner;

public class Main {

    private Scanner scanner;

    public static void main(String[] args) {


    }

    //Menú de opciones, retorna un valor tipo int, al seleccionar una opción
    public int showMenu(){
        System.out.println("--------- Sistema de Gestión de Compras ERP --------- ");
        System.out.println("1. Registrar proveedor");
        System.out.println("2. Registrar producto");
        System.out.println("3. Registrar solicitud de compra");
        System.out.println("4. Listar proveedores");
        System.out.println("5. Listar productos");
        System.out.println("6. Listar solicitudes de compra");
        System.out.println("7. Buscar proveedor por ID");
        System.out.println("8. Buscar producto por nombre");
        System.out.println("9. Buscar solicitud por número");
        System.out.println("10. Aprobar / Rechazar solicitud de compra");
        System.out.println("11. Calcular total de una solicitud");
        System.out.println("12. Salir");
        int opcion = validarOpcion(scanner, 1, 15); //metodo que valida la entrada de numeros en el rango 1-5
        return opcion;                                     //valida el ingreso de numeros, no permite ingresar letras o caracteres especiales
    }

    public int validarOpcion(Scanner scanner, int min, int max){
        int num;

        do {
            System.out.println("Ingrese una opción: ");

            if(scanner.hasNextInt()){ //validar ingreso de numeros
                num = scanner.nextInt();
                scanner.nextLine(); //limpia el buffer de entrada del Scanner

                if((num < min) || (num > max)){ //condición cuando se ingresa un valor fuera de los mostrados en el menú
                    System.out.println("Ingrese una opción válida desde -> " +min+" hasta " +max);
                }else{
                    return num;
                }
            } else{
                System.out.println("Ingrese únicamente números, NO letras NI caracteres especiales");
                scanner.nextLine(); //limpiar token invalido ingresado, para evitar que scanner se trabe
            }
        } while (true); //se cumple infinitamente hasta que se ingrese una opcion valida

    }

}