package sistema;
import Models.DetalleSolicitud;
import enums.Estado;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SolicitudDeCompra {
    Usuario usuario;
    Estado estado;
    private GregorianCalendar fechaSolicitud;

    public SolicitudDeCompra() {
        detalleSocilicitud = new ArrayList<>();
    }

    public SolicitudDeCompra(GregorianCalendar fechaSolicitud, Estado estado) {
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        detalleSocilicitud = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
