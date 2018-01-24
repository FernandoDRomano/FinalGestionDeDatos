package modelo;

/**
 *
 * @author Fernando
 */
public class LineaPedido {
    
    private int idLinea;
    private int cantidad;
    private double precio;
    private Producto producto;
    private Pedido pedido;

    public LineaPedido(int idLinea, int cantidad, double precio, Producto producto, Pedido pedido) {
        this.idLinea = idLinea;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto = producto;
        this.pedido = pedido;
    }

    public LineaPedido() {
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
}
