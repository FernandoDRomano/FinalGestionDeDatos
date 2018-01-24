package modelo;

/**
 *
 * @author Fernando
 */
public class Concepto {
    
    private int idConcepto;
    private String codigo;
    private String descripcion;
    private String tipoConcepto;
    private int porcentaje;
    private double monto;
    private char fijo;

    public Concepto(int idConcepto, String codigo, String descripcion, String tipoConcepto, int porcentaje, double monto, char fijo) {
        this.idConcepto = idConcepto;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipoConcepto = tipoConcepto;
        this.porcentaje = porcentaje;
        this.monto = monto;
        this.fijo = fijo;
    }

    public Concepto() {
    }

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public char getFijo() {
        return fijo;
    }

    public void setFijo(char fijo) {
        this.fijo = fijo;
    }
    
    
}
