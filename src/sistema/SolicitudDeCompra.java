package sistema;
import Models.DetalleSolicitud;
import enums.Estado;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SolicitudDeCompra {
    Usuario usuario;
    Estado estado;
    
    private List<DetalleSolicitud> detalleSolicitud;
    private GregorianCalendar fechaSolicitud


    public SolicitudDeCompra() {
        detalleSolicitud = new ArrayList<>();
    }

    public SolicitudDeCompra(GregorianCalendar fechaSolicitud, Estado estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        detalleSolicitud = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }


    public void addUsuario(String nombre, String apellido, String id, String email, String telefono ) {
        this.usuario = new Usuario(nombre, apellido, id, email, telefono);
    }

    public double calcularPrecio() {
        double total= 0.0;
        for (DetalleSolicitud dS : detalleSolicitud) {
            total += calcularPrecio();
        }
        return total;
    }

}