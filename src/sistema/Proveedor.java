package sistema;
import java.util.ArrayList;
import java.util.List;

//hereda atributos de Contacto
public class Proveedor extends Contacto {
    private String ruc;
    private String direccion;

    public Proveedor(String nombre, String apellido, String id, String email, String telefono, String ruc, String direccion) {
        super(nombre, apellido, id, email, telefono);
        this.ruc = ruc;
        this.direccion = direccion;
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

    @Override
    public String toString() {
        return super.toString() + "\nProveedor -->" +
                ", ruc = '" + ruc + '\'' +
                ", Direccion = '" + direccion + '\''
                ;
    }

}
