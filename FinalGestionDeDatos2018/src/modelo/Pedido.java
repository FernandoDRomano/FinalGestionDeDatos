
package modelo;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
class Pedido {
    
    private int idPedido;
    private String fecha;
    private double total;
    private String estado;
    private Empleado empleado;
    private Proveedor proveedor;
    private ArrayList<LineaPedido> linea;


    public Pedido() {
        linea = new ArrayList<LineaPedido>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public ArrayList<LineaPedido> getLinea() {
        return linea;
    }

    public void setLinea(ArrayList<LineaPedido> linea) {
        this.linea = linea;
    }
    
    
    
    
    
}
