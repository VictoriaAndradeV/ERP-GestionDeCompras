package sistema;

import java.util.ArrayList;

public class Proveedor extends Contacto {
    private List<Productos> productos;
    private String ruc;
    private String direccion;


    public Proveedor() {
        productos = new ArrayList<>();
    }
    public Proveedor(String nombre, String apellido, String id, String email, String telefono) {
        super(nombre, apellido, id, email, telefono);
        productos = new ArrayList<>();
        this.ruc = ruc;
        this.direccion = direccion;
    }

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public void addProductos(String producto){
        productos.add(producto);
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
