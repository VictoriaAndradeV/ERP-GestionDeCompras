package sistema;
import java.util.ArrayList;
import java.util.List;

//hereda atributos de Contacto
public class Proveedor extends Contacto {
    private List<String> productos;
    private String ruc;
    private String direccion;


    public Proveedor() {
        productos = new ArrayList<String>();
    }

    public Proveedor(String nombre, String apellido, String id, String email, String telefono) {
        super(nombre, apellido, id, email, telefono);
        productos = new ArrayList<String>();
        this.ruc = ruc;
        this.direccion = direccion;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
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

    public void addProductos(String producto){
        productos.add(producto);
    }
}
