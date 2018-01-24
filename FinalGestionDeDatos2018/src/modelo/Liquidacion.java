package modelo;

import java.util.Date;

/**
 *
 * @author Fernando
 */
class Liquidacion {
    
    private int idLiquidacion;
    private Date fecha;
    private double haberesRemunerativos;
    private double haberesNoRemunerativos;
    private double descuentos;
    private double sueldoNeto;
    private Empleado empleado;

    public int getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(int idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getHaberesRemunerativos() {
        return haberesRemunerativos;
    }

    public void setHaberesRemunerativos(double haberesRemunerativos) {
        this.haberesRemunerativos = haberesRemunerativos;
    }

    public double getHaberesNoRemunerativos() {
        return haberesNoRemunerativos;
    }

    public void setHaberesNoRemunerativos(double haberesNoRemunerativos) {
        this.haberesNoRemunerativos = haberesNoRemunerativos;
    }

    public double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
    
}
