package sistema;
import enums.Rol;

public class Usuario extends Contacto{
    private Departamento departamento;
    private Rol rol;


    public Usuario(String nombre, String apellido, String id, String email, String telefono, Departamento departamento, Rol rol) {
        super(nombre, apellido, id, email, telefono);
        this.departamento = departamento;
        this.rol = rol;
    }

    public Departamento getDepartamento() {
        return departamento;
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
                "\n Departamento = " + departamento +
                ", ROL = " + rol
                ;
    }
}
