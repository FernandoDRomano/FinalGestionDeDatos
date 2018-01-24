package modelo;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
class Venta {

    private int idVenta;
    private Date fecha;
    private double total;
    private Cliente cliente;
    private Empleado empleado;
    private ArrayList<LineaVenta> linea;

    public Venta(int idVenta, Date fecha, double total, Cliente cliente, Empleado empleado) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
        this.empleado = empleado;
        linea = new ArrayList<LineaVenta>();
    }

    public Venta() {
        linea = new ArrayList<LineaVenta>();
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public ArrayList<LineaVenta> getLinea() {
        return linea;
    }

    public void setLinea(ArrayList<LineaVenta> linea) {
        this.linea = linea;
    }
    
    
}
