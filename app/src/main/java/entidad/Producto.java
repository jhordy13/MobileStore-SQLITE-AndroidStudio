package entidad;

/**
 * Created by Jhordy on 13/01/2017.
 */

public class Producto {

    private long productoid;
    private String nombre;
    private String precio;

    public Producto() {
    }

    public Producto(long productoid, String nombre, String precio) {
        this.productoid = productoid;
        this.nombre = nombre;
        this.precio = precio;
    }

    public long getProductoid() {
        return productoid;
    }

    public void setProductoid(long productoid) {
        this.productoid = productoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
