package views;

import java.util.Scanner;
import sistema.Contacto;
import sistema.Departamento;
import sistema.Usuario;
import Enums.Rol;

public class ShowConsole {

    private Scanner scanner;

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

    public Contacto registrarContacto(){
        System.out.println("--------- Registrar Contacto --------- ");
        System.out.println("Nombre");
        String nombre = validarIngresoLetras(scanner);
        System.out.println("Apellido");
        String apellido = validarIngresoLetras(scanner);
        System.out.println("ID");
        String id = scanner.nextLine();
        System.out.println("Email");
        String email = validarIngresoLetras(scanner);
        System.out.println("Telefono");
        String telefono = validarIngresoLetras(scanner);
        return new Contacto(nombre, apellido, id, email, telefono);
    }

    public Usuario registrarUsuario(){

        Contacto contacto = registrarContacto();
        System.out.println("--------- Registrar Usuario --------- ");
        System.out.println("Rol");
        Rol roles = ingresoRol(scanner);
        System.out.println("Departamento: ");
        Departamento departamento = ingresoDepartamento(scanner);
        return new Usuario(
                contacto.getNombre(),
                contacto.getApellido(),
                contacto.getId(),
                contacto.getEmail(),
                contacto.getTelefono(),
                departamento,
                roles
        );
    }
    /*
    public Proveedor registrarProveedor(){
        Contacto contacto = registrarContacto();
        System.out.println("--------- Registrar Proveedor --------- ");
        System.out.println("Rol");
    }*/



    public Departamento ingresoDepartamento(Scanner scanner){
        System.out.println("--------- Ingresar Departamento --------- ");
        System.out.println("Nombre");
        String nombre = validarIngresoLetras(scanner);
        System.out.println("ID");
        String id = scanner.nextLine();
        System.out.println("Número de empleados: ");
        int empleados = validarIngresoNumeros(scanner);
        return new Departamento(nombre, id, empleados);
    }

    public Rol ingresoRol(Scanner scanner){
        System.out.println("Roles disponibles:");

        for (Rol rol : Rol.values()) { //imprime los roles ingresados en enums.Rol
            System.out.println("- " + rol.name());
        }

        Rol rolAgregado = null;
        while (rolAgregado == null) {
            System.out.println("Ingrese el rol: ");
            String ingreso = scanner.nextLine().toUpperCase();

            for (Rol rol : Rol.values()) {
                if (rol.name().equals(ingreso)) { //si el rol ingresado, coincide con los existentes, se retorna el rol agregado
                    rolAgregado = rol;
                    break;
                }
            }
            if (rolAgregado == null) {
                System.out.println("Rol inválido. Intente nuevamente.");
            }
        }

        System.out.println("SUPERVISOR - JEFE_DE_DEPARTAMENTO");
        return rolAgregado;
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

    public String validarIngresoLetras(Scanner scanner){
        String ingreso;

        do {
            System.out.println("--> ");
            ingreso = scanner.nextLine().trim(); //acepta el ingreso de un string con espacios

            if(ingreso.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")){ //valida el ingreso de un string valido, con letras de todo el alfabeto
                return ingreso;                                 //valida tildes, Ñ - ñ
            } else {
                System.out.println("Ingrese unicamente letras, no numeros, no caracteres especiales");
            }
        } while (true);//se cumple infinitamente hasta que se ingrese una cadena de caracteres válida
    }

    public String validarEmail(Scanner scanner){
        String email;

        do{
            System.out.println("-->");
            email = scanner.nextLine().trim();

            if(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
                return email;
            } else {
                System.out.println("Ingrese un email valido");
            }
        } while (true);
    }

    public String validarIngresoTelefono(Scanner scanner){
        String numeros;

        do {
            System.out.println("--> ");

            if(scanner.hasNextLine()){ //valida el ingreso de numeros
                numeros = scanner.nextLine();

                if (numeros.matches("\\d{7,10}")) {
                    return numeros;
                } else {
                    System.out.println("Número inválido. Ingrese solo números, de 7 a 10 dígitos.");
                }
            } else{
                System.out.println("Ingrese unicamente números");
                scanner.nextLine(); //limpiar token invalido ingresado, para evitar que scanner se trabe
            }
        } while (true);
    }

    public int validarIngresoNumeros(Scanner scanner){

        do {
            System.out.println("--> ");

            if(scanner.hasNextInt()){ //valida el ingreso de numeros
                int num = scanner.nextInt();

                if((num <= 0) || (num > 10)){ //ingresa al if si la edad ingresada esta fuera del rango
                    System.out.println("El departamento puede tener de 1 - 10 empleados. Ingrese nuevamente");
                    scanner.nextLine();
                }else{
                    return num;
                }
            } else{
                System.out.println("Ingrese unicamente numeros, no letras ni caracteres especiales");
                scanner.nextLine(); //limpiar token invalido ingresado, para evitar que scanner se trabe
            }
        } while (true);
    }
}
