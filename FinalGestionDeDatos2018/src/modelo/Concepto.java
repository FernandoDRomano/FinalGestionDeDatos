package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando
 */
public class Concepto {
    
    private int idConcepto;
    private String descripcion;
    private String tipoConcepto;
    private double porcentaje;
    private double monto;
    private String fijo;

    public Concepto() {
    }

    public int getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
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

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getFijo() {
        return fijo;
    }

    public void setFijo(String fijo) {
        this.fijo = fijo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    //METODOS PARA LAS TRANSACCIONES A LA BASE DE DATOS
    
    public void grabarConcepto(){
        try {
            Conexion conexion = new Conexion();
            String query = "insert into concepto (descripcion, tipoConcepto, porcentaje, monto, fijo) values (?,?,?,?,?);";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.setString(1, this.getDescripcion());
            st.setString(2, this.getTipoConcepto());
            st.setDouble(3, this.getPorcentaje());
            st.setDouble(4, this.getMonto());
            st.setString(5, this.getFijo());
            st.execute();
            System.out.println("SE GRABO EL CONCEPTO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void eliminarConcepto(){
        try {
            Conexion conexion = new Conexion();
            String query = "delete from concepto where idconcepto = " + this.getIdConcepto()+ ";";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.execute();
            System.out.println("SE ELIMINO EL CONCEPTO DE LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void editarConcepto(){
        try {
            Conexion conexion = new Conexion();
            String query = "update concepto set descripcion = '" + this.getDescripcion()+ "' , tipoConcepto = '" + this.getTipoConcepto() + "' , fijo = '" + this.getFijo() + "' , porcentaje = '" + this.getPorcentaje() + "' , monto = '" + this.getMonto() + "' where idconcepto = '" + this.getIdConcepto() + "' ;";
            System.out.println(query);
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            st.executeUpdate();
            System.out.println("SE EDITO EL CONCEPTO EN LA BASE DE DATOS");
            st.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ResultSet listarConceptos(){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from concepto;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public ResultSet buscarConcepto(String cadena){
        ResultSet resultado = null;
         try {
            Conexion conexion = new Conexion();
            String query = "select * from concepto where descripcion like '" + cadena + "%' or idconcepto like'" + cadena + "%' ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            resultado = st.executeQuery();
            conexion.desconectar();           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
         return resultado;
    }
    
    public boolean tieneLiquidaciones() {
        boolean bandera = false;
        ResultSet r = null;
        int resultado = 0;
        try {
            Conexion conexion = new Conexion();
            String query = "select count(*) as resultado from linea_liquidacion where linea_liquidacion.concepto_idconcepto  = " + this.getIdConcepto()+ " ;";
            PreparedStatement st = conexion.getConnection().prepareStatement(query);
            r = st.executeQuery();
            conexion.desconectar();
            
            //VERIFICO QUE NO TENGA LINEAS DE LIQUIDACION ASOCIADAS AL CONCEPTO
            while (r.next()) {                
                resultado = Integer.valueOf(r.getString("resultado"));
            }
            
            if (resultado > 0) {
                bandera = true;
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return bandera;
    }

    @Override
    public String toString() {
        return "Concepto{" + "idConcepto=" + idConcepto +  ", descripcion=" + descripcion + ", tipoConcepto=" + tipoConcepto + ", porcentaje=" + porcentaje + ", monto=" + monto + ", fijo=" + fijo + '}';
    }
    
    
    
    
}
