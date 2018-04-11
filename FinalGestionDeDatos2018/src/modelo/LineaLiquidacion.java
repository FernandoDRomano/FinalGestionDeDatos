package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class LineaLiquidacion {
    
    private int idLinea;
    private int cantidad;
    private double monto;
    private Liquidacion liquidacion;
    private Concepto concepto; 

    public LineaLiquidacion() {
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Liquidacion getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
        this.liquidacion = liquidacion;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }
    
    public void grabarLineaLiquidacion(){
        try {            
            Conexion conexion = new Conexion();
            
            String query = "INSERT INTO linea_liquidacion (cantidad, monto, liquidacion_idliquidacion, concepto_idconcepto) values (?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setInt(1, this.getCantidad());
            st.setDouble(2, this.getMonto());
            st.setInt(3, this.getLiquidacion().getIdLiquidacion());
            st.setInt(4, this.getConcepto().getIdConcepto());
            st.execute();
            System.out.println("SE GRABO LA LINEA DE LIQUIDACION EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "LineaLiquidacion{" + "idLinea=" + idLinea + ", cantidad=" + cantidad + ", monto=" + monto + ", liquidacion=" + liquidacion + ", concepto id=" + concepto.getIdConcepto() + ", concepto=" + concepto.getDescripcion() + '}' + "\n";
    }
    
}
