package sistema;
import Models.DetalleSolicitud;
import enums.Estado;
import enums.Rol;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class SolicitudDeCompra {
    Usuario usuario;
    Estado estado;
    private String id;
    private String numeroSolicitud;
    private List<DetalleSolicitud> detalleSolicitud;
    private GregorianCalendar fechaSolicitud;

    public SolicitudDeCompra() {
        detalleSolicitud = new ArrayList<>();
    }

    public SolicitudDeCompra(GregorianCalendar fechaSolicitud, Estado estado, String id) {
        this.fechaSolicitud=fechaSolicitud;
        this.estado = estado;
        this.id = id;
        detalleSolicitud = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public GregorianCalendar getFechaSolicitud() {
        return fechaSolicitud;
    }
    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }
    public void setFechaSolicitud(GregorianCalendar fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void addUsuario(String nombre, String apellido, String id, String email, String telefono, Departamento departamento, Rol rol) {
        this.usuario = new Usuario(nombre, apellido, id, email, telefono, departamento, rol);
    }

    public double calcularPrecio() {
        double total= 0.0;
        for (DetalleSolicitud dS : detalleSolicitud) {
            total += dS.calcularTotal();
        }
        return total;
    }

    public void aprobarEstado(Usuario evaluador, boolean aprobar) {
        boolean jefe= evaluador.getRol()==Rol.JEFE_DE_DEPARTAMENTO;
        boolean mismoDepartamento= evaluador.getDepartamento()==usuario.getDepartamento();
        if (evaluador.getRol() == Rol.JEFE_DE_DEPARTAMENTO && evaluador.getDepartamento() == usuario.getDepartamento()) {
            if (aprobar) {
                this.estado = Estado.APROBADO;
            } else {
                this.estado = Estado.RECHAZADO;
            }
            System.out.println("Estado de la solicitud: "+ (aprobar ? "aprobada" : "rechazada") );
        }else{
            System.out.println("No tiene permiso para aprobar el solicitud");
        }
    }


    @Override
    public String toString() {
        return "SolicitudDeCompra{" +
                "Usuario = " + usuario +
                ", Estado = " + estado +
                ", id = '" + id + '\'' +
                ", numeroSolicitud = '" + numeroSolicitud + '\'' +
                ", detalleSolicitud =" + detalleSolicitud +
                ", fechaSolicitud= " + fechaSolicitud +
                '}';
    }
}