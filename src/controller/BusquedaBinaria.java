package controller;

import sistema.Proveedor;
import sistema.SolicitudDeCompra;

import java.util.List;

public class BusquedaBinaria {

    public int buscarProveedorID(List<Proveedor> proveedores, String id) {

        for (int i = 1; i < proveedores.size(); i++) {
            Proveedor proveedorActual = proveedores.get(i);
            int j = i - 1;

            while (j >= 0 && proveedores.get(j).getId().compareToIgnoreCase(proveedorActual.getId()) > 0) {
                proveedores.set(j + 1, proveedores.get(j));
                j--;
            }
            proveedores.set(j + 1, proveedorActual);
        }

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


    public int buscarSolicitudID(List<SolicitudDeCompra> solicitudDeCompras, String id) {

        for (int i = 1; i < solicitudDeCompras.size(); i++) {
            SolicitudDeCompra pasajero = solicitudDeCompras.get(i);
            int j = i - 1;

            while (j >= 0 && solicitudDeCompras.get(j).getId().compareToIgnoreCase(pasajero.getId()) > 0) {
                solicitudDeCompras.set(j + 1, solicitudDeCompras.get(j));
                j--;
            }
            solicitudDeCompras.set(j + 1, pasajero);
        }

        int bajo = 0;
        int alto = (solicitudDeCompras.size() - 1);

        while(bajo <= alto){

            int central = (bajo + alto)/2;
            SolicitudDeCompra solicitudCentral = solicitudDeCompras.get(central);

            int comparacion = solicitudCentral.getId().compareToIgnoreCase(id);

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
