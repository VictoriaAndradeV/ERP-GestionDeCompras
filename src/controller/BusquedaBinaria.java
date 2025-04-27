package controller;

import sistema.Proveedor;
import java.util.List;

public class BusquedaBinaria {

    public int buscarProveedorID(List<Proveedor> proveedores, String id) {
        int bajo = 0;
        int alto = (proveedores.size() - 1);

        while(bajo <= alto){

            int central = (bajo + alto)/2;
            Proveedor proveedorCentral = proveedores.get(central);

            int comparacion = proveedorCentral.getId().compareToIgnoreCase(id);

            if(comparacion == 0){ //si es ==0 significa que las comparaciones entre ambos nombres coinciden
                return central;
            }
            if(comparacion < 0){ //si alfabeticamente se encuentra antes, va a la derecha
                bajo = central + 1;
            }else {
                alto = central - 1;
            }
        }
        return -1; //no se encontro el id buscado
    }

}
