package Models;
import Enums.UnidadDeMedida;

public class ProductoTecnologico extends Producto {

    private int garantiaEnMeses;

    public ProductoTecnologico(String id, String nombre, String descripcion, double precioUnitario, UnidadDeMedida unidad, int garantiaEnMeses) {
        this.garantiaEnMeses = garantiaEnMeses;

    }

    public ProductoTecnologico(String id, String nombre, String descripcion, double precioUnitario, UnidadDeMedida unidad) {
        super(id, nombre, descripcion, precioUnitario, unidad);
    }

    public int getGarantiaEnMeses() {
        return garantiaEnMeses;
    }

    @Override
    public double calcularPrecio() {
        return  precioUnitario * garantiaEnMeses;
    }

    @Override
    public String toString() {
        return super.toString() + "Garantia en meses: " + garantiaEnMeses + "\n Precio total: $"+calcularPrecio();
    }
}
