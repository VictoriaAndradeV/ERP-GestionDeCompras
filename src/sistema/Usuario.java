package sistema;
import enums.Rol;

public class Usuario extends Contacto{
    Departamento departamento;
    private Rol rol;


    public Usuario(String nombre, String apellido, String id, String email, String telefono, Departamento departamento, Rol rol) {
        super(nombre, apellido, id, email, telefono);
        this.departamento = departamento;
        this.rol = rol;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void addDepartamento(String nombre, String id, int numeroEmpleados) {
        this.departamento = new Departamento(nombre, id, numeroEmpleados);
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return super.toString() + "\nUsuario -->" +
                " Departamento = " + departamento +
                ", ROL = " + rol
                ;
    }
}
